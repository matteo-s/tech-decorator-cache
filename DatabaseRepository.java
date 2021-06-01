import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class DatabaseRepository implements Repository {

    private final HashMap<String, Entity> database;

    public DatabaseRepository() {
        this.database = new HashMap<>();

    }

    @Override
    public Entity get(String id) {
        System.out.println("DB: get for id " + String.valueOf(id));
        Entity e = database.get(id);
        if (e == null) {
            throw new NoSuchEntityException();
        }

        System.out.println("DB: get for id result " + String.valueOf(e));
        return e;

    }

    @Override
    public Entity find(String id) {
        System.out.println("DB: find for id " + String.valueOf(id));
        Entity e = database.get(id);

        System.out.println("DB: find for id result " + String.valueOf(e));
        return e;
    }

    @Override
    public List<Entity> findByValue(String value) {
        System.out.println("DB: find for value " + String.valueOf(value));

        //
        List<Entity> list = database.values().stream().filter(e -> e.getValue().equals(value))
                .collect(Collectors.toList());

        System.out.println("DB: find for value result " + String.valueOf(list));
        return list;
    }

    @Override
    public void save(Entity e) {
        if (e.getId() == null) {
            e.setId(UUID.randomUUID().toString());
        }

        System.out.println("DB: save for " + String.valueOf(e.getId()));
        database.put(e.getId(), e);

    }

    @Override
    public List<Entity> list() {
        System.out.println("DB: list all");
        return new ArrayList<>(database.values());
    }

    @Override
    public List<Entity> searchAny(String[] keywords) {
        System.out.println("DB: search any for keywords " + Arrays.toString(keywords));

        //
        Set<Entity> list = new HashSet<>();
        for (String keyword : keywords) {
            list.addAll(database.values().stream()
                    .filter(e -> e.getValue().contains(keyword))
                    .collect(Collectors.toList()));
        }

        System.out.println("DB: search any for keywords result " + String.valueOf(list));
        return new ArrayList<>(list);
    }

    @Override
    public List<Entity> searchAll(String[] keywords) {
        System.out.println("DB: search all for keywords " + Arrays.toString(keywords));

        //
        List<List<Entity>> list = new ArrayList<>();
        for (String keyword : keywords) {
            list.add(database.values().stream()
                    .filter(e -> e.getValue().contains(keyword))
                    .collect(Collectors.toList()));
        }

        Set<Entity> res = new HashSet<>();
        res.addAll(list.iterator().next());

        for (List<Entity> l : list) {
            res.retainAll(l);
        }

        System.out.println("DB: search all for keywords result " + String.valueOf(res));
        return new ArrayList<>(res);
    }

}