package storage;
import model_ideal.ContactType;
import model_ideal.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by alximik on 13/01/15.
 */
public abstract class AbstractStorageTest {

    protected static IStorage storage;
    protected static Resume R1, R2, R3;

    static
    {
        R1 = new Resume("Имя1", "локация1");
        R1.addContact(ContactType.MOBILE,"111");
        R1.addContact(ContactType.MAIL, "aaa@mail.ru");

        R2 = new Resume("Имя2", "локация2");
        R2.addContact(ContactType.MOBILE, "222");
        R2.addContact(ContactType.SKYPE, "lalala");

        R3 = new Resume("Имя3", null);
    }

    @Before
    public void before() {
        storage.clear();
        storage.save(R1);
        storage.save(R2);
        storage.save(R3);
    }

    @Test
    public void testClear() throws Exception {

        storage.clear();
        Assert.assertEquals(0, storage.size());

    }

    @Test
    public void testSave()  {
        Assert.assertEquals(3, storage.size());

        Assert.assertEquals(true,(storage.load(R1.getUuid()).equals(R1)));
        Assert.assertEquals(true,(storage.load(R2.getUuid()).equals(R2)));
        Assert.assertEquals(true,(storage.load(R3.getUuid()).equals(R3)));

    }

    @Test(expected = WebAppException.class)
    public void testSaveException() throws Exception {
        storage.save(R3);
    }

    @Test
    public void testUpdate() {

        // НЕ будет работать тк equals проверяет почти по всем полям
        R1.setFullName("Новое имя");
        storage.update(R1);
        Assert.assertEquals("Новое имя", (storage.load(R1.getUuid()).getFullName()));
    }

    @Test(expected = WebAppException.class)
    public void testUpdateException() throws Exception {
        Resume resumeBad = new Resume("Bad Resume", "Bad location");
        storage.update(resumeBad);
    }
    @Test
    public void testLoad() {

        Resume r = storage.load(R1.getUuid());
        Assert.assertEquals(true,(r.equals(R1)));
        Resume r2 = storage.load(R2.getUuid());
        Assert.assertEquals(true,(r2.equals(R2)));
        Resume r3 = storage.load(R3.getUuid());
        Assert.assertEquals(true,(r3.equals(R3)));

    }
    @Test(expected = WebAppException.class)
    public void testLoadException() throws Exception{
        Resume resumeBad = new Resume("Bad Resume", "Bad location");
        storage.load(resumeBad.getUuid());
    }

    @Test
    public void testDelete()  {

        Assert.assertEquals(3, storage.size());

        storage.delete(R2.getUuid());
        storage.delete(R3.getUuid());
        storage.delete(R1.getUuid());
        Assert.assertEquals(0, storage.size());
    }

    @Test(expected = WebAppException.class)
    public void testDeleteException() throws Exception {
        storage.delete(R3.getUuid());
        storage.delete(R3.getUuid());
    }

    @Test
    public void testGetAllSorted() throws Exception {

    }

    @Test
    public void testSize() throws Exception {
        Assert.assertEquals(3, storage.size());
    }

}

