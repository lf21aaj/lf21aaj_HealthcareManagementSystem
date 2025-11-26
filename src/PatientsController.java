import java.io.IOException;
import java.util.List;

public class PatientsController
{
    private final PatientsRepository repo;

    public PatientsController(PatientsRepository repo)
    {
        this.repo = repo;
    }

    public List<Patient> all()
    {
        return repo.findAll();
    }

    public Patient findById(String id)
    {
        return repo.findById(id);
    }

    public void add(Patient p)
    {
        repo.add(p);
    }

    public void update(Patient p)
    {
        repo.update(p);
    }

    public boolean delete(String id)
    {
        return repo.deleteById(id);
    }

    public void save() throws IOException
    {
        repo.saveAll();
    }
}
