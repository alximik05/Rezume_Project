package storage;

public class XmlFileStorageTest extends AbstractStorageTest {
    static {
        storage = new XmlFileStorage("./file_storage");

        // TODO продебажить цепочку load

    }
}