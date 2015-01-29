package storage;

/**
 * Created by alximik on 29/01/15.
 */
public class DataStreamFileStorageTest extends AbstractStorageTest {
    static {
        storage = new DataStreamFileStorage("./file_storage");
    }
}
