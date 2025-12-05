import java.io.IOException;
import java.util.List;

public class PrescriptionsController
{
    private final PrescriptionsRepository repo;

    public PrescriptionsController(PrescriptionsRepository repo)
    {
        this.repo = repo;
    }

    public List<Prescription> all()
    {
        return repo.findAll();
    }

    public Prescription findById(String id)
    {
        return repo.findById(id);
    }

    public void add(Prescription prescription)
    {
        repo.add(prescription);
    }

    public void update(Prescription prescription)
    {
        repo.update(prescription);
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
