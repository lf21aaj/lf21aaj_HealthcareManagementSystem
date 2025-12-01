import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class CliniciansRepository implements Repository<Clinician, String>
{
    private final List<Clinician> data = new ArrayList<>();
    private final Path path;

    public CliniciansRepository(Path csvPath) throws  IOException
    {
        this.path = csvPath;
        data.addAll(DataLoader.loadClinicians());
    }

    @Override
    public List<Clinician> findAll()
    {
        return Collections.unmodifiableList(data);
    }

    @Override
    public Clinician findById(String id)
    {
        return data.stream().filter(c -> c.getClinicianId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public void add(Clinician entity) {

    }

    @Override
    public void update(Clinician c)
    {
        for (int i = 0; i < data.size(); i++)
        {
            if (data.get(i).getClinicianId().equals(c.getClinicianId()))
            {
                data.set(i, c);
                return;
            }
        }
    }

    @Override
    public boolean deleteById(String id)
    {
        return data.removeIf(c -> c.getClinicianId().equals(id));
    }

    @Override
    public void saveAll() throws IOException
    {
        String header = String.join(",",
                "clinician_id","first_name","last_name","title","speciality","gmc_number",
                "phone_number","email","workplace_id","workplace_type","employment_status","start_date");

        List<String> lines = new ArrayList<>();
        lines.add(header);

        for (Clinician c : data)
        {
            lines.add(String.join(",",
            c.getClinicianId(), c.getFirstName(), c.getLastName(), c.getTitle(),
            c.getSpeciality(), c.getGmcNumber(), c.getPhoneNumber(), c.getEmail(),
            c.getWorkplaceId(), c.getWorkplaceType(), c.getEmploymentStatus(), c.getStartDate()
            ));
        }

        CSVUtil.writeAll(path, lines);
    }
}

