package storage;


public class JsonFileStorageTest extends AbstractStorageTest {
    static {
        storage = new JsonFileStorage("./file_storage");
    }
}