package storage;

import model_ideal.Contact;
import model_ideal.ContactType;
import model_ideal.Resume;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by alximik on 13/01/15.
 */
public class AbstractStorageTest {

    protected static IStorage storage;
    protected static Resume R1, R2, R3;


    @BeforeClass
    public static void beforeClass() {
        R1 = new Resume("Имя1", "локация1");
        R1.addContact(new Contact(ContactType.MOBILE, "111"));
        R1.addContact(new Contact(ContactType.MAIL, "aaa@mail.ru"));

        R2 = new Resume("Имя2", "локация2");
        R2.addContact(new Contact(ContactType.MOBILE, "222"));
        R2.addContact(new Contact(ContactType.SKYPE, "lalala"));

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
    public void testSave() throws Exception {
        Assert.assertEquals(3, storage.size());

        Assert.assertEquals(true,(storage.load(R1.getUuid()).equals(R1)));
        Assert.assertEquals(true,(storage.load(R2.getUuid()).equals(R2)));
        Assert.assertEquals(true,(storage.load(R3.getUuid()).equals(R3)));

        storage.save(R3);
    }

    @Test
    public void testUpdate() throws Exception {

        Resume testUpdateRezume = new Resume(R1.getUuid(),"Обновленное имя", "Обновленная локация");
        storage.update(testUpdateRezume);
        Assert.assertEquals(true,(storage.load(R1.getUuid()).equals(R1)));

        Resume resumeBad = new Resume("Bad Resume", "Bad location");
        storage.update(resumeBad);
    }

    @Test
    public void testLoad() throws Exception {

        Resume r = storage.load(R1.getUuid());
        Assert.assertEquals(true,(r.equals(R1)));
        Resume r2 = storage.load(R2.getUuid());
        Assert.assertEquals(true,(r2.equals(R2)));
        Resume r3 = storage.load(R3.getUuid());
        Assert.assertEquals(true,(r3.equals(R3)));

        Resume resumeBad = new Resume("Bad Resume", "Bad location");
        storage.load(resumeBad.getUuid());
    }

    @Test
    public void testDelete() throws Exception {

        Assert.assertEquals(3, storage.size());

        storage.delete(R2.getUuid());
        storage.delete(R3.getUuid());
        storage.delete(R1.getUuid());
        Assert.assertEquals(0, storage.size());

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

