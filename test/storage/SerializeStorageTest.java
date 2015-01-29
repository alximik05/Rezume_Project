package storage;

/**
 * Created by alximik on 29/01/15.
 */
public class SerializeStorageTest extends  AbstractStorageTest {
    static {
        storage = new SerializeStorage("./file_storage");
    }
}
