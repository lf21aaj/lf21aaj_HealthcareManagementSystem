import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AppointmentsRepository implements Repository<Appointment, String>
{
    private final List<Appointment> data = new ArrayList<>();
    private final Path path;

    public AppointmentsRepository(Path csvPath) throws IOException
    {
        this.path = csvPath;
        data.addAll(DataLoader.loadAppointments());
    }

    @Override
    public List<Appointment> findAll()
    {
        return Collections.unmodifiableList(data);
    }

    @Override
    public Appointment findById(String id)
    {
        for (Appointment a : data)
        {
            if (a.getAppointmentId().equals(id))
            {
                return a;
            }
        }
        return null;
    }

    @Override
    public void add(Appointment a)
    {
        data.add(a);
    }

    @Override
    public void update(Appointment a)
    {
        for (int i = 0; i < data.size(); i++)
        {
            if (data.get(i).getAppointmentId().equals(a.getAppointmentId()))
            {
                data.set(i, a);
                return;
            }
        }
    }

    @Override
    public boolean deleteById(String id)
    {
        return data.removeIf(a -> a.getAppointmentId().equals(id));
    }

    @Override
    public void saveAll() throws IOException {
        // header must exactly match appointments.csv
        String header = String.join(",",
                "appointment_id",
                "patient_id",
                "clinician_id",
                "facility_id",
                "appointment_date",
                "appointment_time",
                "duration_minutes",
                "appointment_type",
                "status",
                "reason_for_visit",
                "notes",
                "created_date",
                "last_modified"
        );

        List<String> lines = new ArrayList<>();
        lines.add(header);

        for (Appointment a : data) {
            lines.add(String.join(",",
                    a.getAppointmentId(), a.getPatientId(), a.getClinicianId(), a.getFacilityId(),
                    a.getAppointmentDate(), a.getAppointmentTime(), String.valueOf(a.getDurationMinutes()),
                    a.getAppointmentType(), a.getStatus(), a.getReasonForVisit(), a.getNotes(), a.getCreatedDate(), a.getLastModified()
            ));
        }

        CSVUtil.writeAll(path, lines);
    }
}
