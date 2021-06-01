import java.util.List;

public interface Repository {

    public void save(Entity e);

    public Entity get(String id);

    public Entity find(String id);

    public List<Entity> findByValue(String value);

    public List<Entity> list();

    public List<Entity> searchAny(String[] keywords);

    public List<Entity> searchAll(String[] keywords);

}