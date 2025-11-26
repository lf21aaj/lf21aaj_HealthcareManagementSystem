import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class PatientsRepository implements Repository<Patient, String>
{
    private final List<Patient> data = new ArrayList<>();
    private final Path path;

    public PatientsRepository(Path csvPath) throws IOException
    {
        this.path = csvPath;
        data.addAll(DataLoader.loadPatients());
    }

    @Override
    public List<Patient> findAll()
    {
        return Collections.unmodifiableList(data);
    }

    @Override
    public Patient findById(String id)
    {
        for (Patient p : data) if (p.getPatientId().equals(id)) return p;
        return null;
    }

    @Override
    public void add(Patient p)
    {
        data.add(p);
    }

    @Override
    public void update(Patient p)
    {
        for (int i = 0; i < data.size(); i++)
        {
            if (data.get(i).getPatientId().equals(p.getPatientId()))
            {
                data.set(i, p);
                return;
            }
        }
    }

    @Override
    public boolean deleteById(String id)
    {
        return data.removeIf(p -> p.getPatientId().equals(id));
    }

    @Override
    public void saveAll() throws IOException
    {
        String header = String.join(",",
                "patient_id","first_name","last_name","date_of_birth","nhs_number","gender",
                "phone_number","email","address","postcode","emergency_contact_name","emergency_contact_phone",
                "registration_date","gp_surgery_id");

        List<String> lines = new ArrayList<>();
        lines.add(header);
        for (Patient p : data)
        {
            lines.add(String.join(",",
                    p.getPatientId(), p.getFirstName(), p.getLastName(), p.getDateOfBirth(), p.getNhsNumber(), p.getGender(),
                    p.getPhoneNumber(), p.getEmail(), p.getAddress(), p.getPostcode(), p.getEmergencyContactName(), p.getEmergencyContactPhone(),
                    p.getRegistrationDate(), p.getGpSurgeryId()));
        }
        CSVUtil.writeAll(path, lines);
    }
}
