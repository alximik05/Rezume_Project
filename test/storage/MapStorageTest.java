package storage;

import org.junit.BeforeClass;


public class MapStorageTest extends AbstractStorageTest{

    @BeforeClass
    public static void beforeClass() {
        storage = new MapStorage();
    }
}