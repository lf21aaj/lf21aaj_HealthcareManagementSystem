import java.io.IOException;
import java.util.List;

public interface Repository<Type, ID>
{
    List<Type> findAll();
    Type findById(ID id);
    void add (Type entity);
    void update(Type entity);
    boolean deleteById(ID id);
    void saveAll() throws IOException;
}
