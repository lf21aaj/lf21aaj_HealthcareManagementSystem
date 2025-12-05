import java.io.IOException;
import java.util.List;

public class AppointmentsController
{
    private final AppointmentsRepository repo;

    public AppointmentsController(AppointmentsRepository repo)
    {
        this.repo = repo;
    }

    public List<Appointment> all()
    {
        return repo.findAll();
    }

    public Appointment findById(String id)
    {
        return repo.findById(id);
    }

    public void add(Appointment a)
    {
        repo.add(a);
    }

    public void update(Appointment a)
    {
        repo.update(a);
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
