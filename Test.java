import java.util.Arrays;

public class Test {

    public static Repository repo;
    public static DatabaseRepository db;

    public static void main(String[] args) {

        // create repo
        db = new DatabaseRepository();
        repo = new CacheRepository(db);

        // init repo
        init();

        // test
        testFind();
        testSearch();

        test();

    }

    public static void testFind() {
        System.out.println("\n BYVALUE \n");
        repo.findByValue("value1").forEach(e -> {
            System.out.println("found element " + String.valueOf(e));
        });

    }

    public static void testSearch() {
        String[] keywords1 = { "and" };

        System.out.println("\n SEARCH ANY" + Arrays.toString(keywords1));
        repo.searchAny(keywords1).forEach(e -> {
            System.out.println("found element " + String.valueOf(e));
        });

        String[] keywords2 = { "and", "test" };

        System.out.println("\n SEARCH ANY" + Arrays.toString(keywords2));
        repo.searchAny(keywords2).forEach(e -> {
            System.out.println("found element " + String.valueOf(e));
        });

        System.out.println("\n SEARCH ALL" + Arrays.toString(keywords2));
        repo.searchAll(keywords2).forEach(e -> {
            System.out.println("found element " + String.valueOf(e));
        });

    }

    public static void test() {
        System.out.println("\n CHECK CACHE\n");

        // list
        repo.list().forEach(e -> {
            System.out.println("list element " + String.valueOf(e));
        });

        System.out.println("\n TEST \n");

        // get 1 cold
        Entity e1c = repo.get("one");
        System.out.println("res one cold " + String.valueOf(e1c) + "\n");

        // get 2 cold
        Entity e2c = repo.get("two");
        System.out.println("res two cold " + String.valueOf(e2c) + "\n");

        // find 3 cold
        Entity e3c = repo.find("three");
        System.out.println("res three cold " + String.valueOf(e3c) + "\n");

        // get 1 hot
        Entity e1h = repo.get("one");
        System.out.println("res one hot " + String.valueOf(e1h) + "\n");

        // update 1
        Entity e1u = new Entity("one", "new value1");
        repo.save(e1u);

        System.out.println("");

        // get 1 update from db
        Entity e1dup = db.get("one");
        System.out.println("res one db " + String.valueOf(e1dup) + "\n");

        // get 1 update from cache
        Entity e1up = repo.get("one");
        System.out.println("res one updated " + String.valueOf(e1up) + "\n");

        // get 1 hot
        Entity e1hup = repo.get("one");
        System.out.println("res one hot " + String.valueOf(e1hup) + "\n");

        // get 2 hot
        Entity e2h = repo.get("two");
        System.out.println("res two hot " + String.valueOf(e2h) + "\n");

        // get 3 cold
        Entity e3cc = repo.get("three");
        System.out.println("res three cold " + String.valueOf(e3cc) + "\n");

    }

    public static void init() {
        System.out.println("\n INIT \n");

        db.save(new Entity("one", "value1"));
        db.save(new Entity("two", "value2 and test"));
        db.save(new Entity("three", "value3 and"));

        db.save(new Entity("onebis", "value1"));

    }

}
