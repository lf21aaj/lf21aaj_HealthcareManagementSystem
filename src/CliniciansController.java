import java.io.IOException;
import java.util.List;

public class CliniciansController
{
    private final CliniciansRepository repo;

    public CliniciansController(CliniciansRepository repo)
    {
        this.repo = repo;
    }

    public List<Clinician> all() { return repo.findAll(); }

    public Clinician findById(String id) { return repo.findById(id); }

    public void add(Clinician c) { repo.add(c); }

    public void update(Clinician c) { repo.update(c); }

    public boolean delete(String id) { return repo.deleteById(id); }

    public void save() throws IOException { repo.saveAll(); }
}
