package storage;

import model_ideal.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class ArrayStorageTest {

    private  static Resume R1,R2, R3;

    private ArrayStorage storage = new ArrayStorage();

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
//        Assert.assertEquals(null, storage.load(R1.getUuid()));
//        Assert.assertEquals(null, storage.load(R2.getUuid()));
//        Assert.assertEquals(null, storage.load(R3.getUuid()));
    }

    @Test
    public void testSave() throws Exception {
        Assert.assertEquals(3, storage.size());

        Assert.assertEquals("Имя1", storage.load(R1.getUuid()).getFullName());
        Assert.assertEquals("локация1", storage.load(R1.getUuid()).getLocation());

        Assert.assertEquals("Имя2", storage.load(R2.getUuid()).getFullName());
        Assert.assertEquals("локация2", storage.load(R2.getUuid()).getLocation());

        Assert.assertEquals("Имя3", storage.load(R3.getUuid()).getFullName());
        Assert.assertEquals(null, storage.load(R3.getUuid()).getLocation());

        Assert.assertEquals("111", storage.load(R1.getUuid()).getContacts().get(0).getContent());
        Assert.assertEquals("aaa@mail.ru", storage.load(R1.getUuid()).getContacts().get(1).getContent());


    }

    @Test
    public void testUpdate() throws Exception {

        Resume testUpdateRezume = new Resume(R1.getUuid(),
                "Обновленное имя", "Обновленная локация");
        storage.update(testUpdateRezume);
        Assert.assertEquals("Обновленное имя",storage.load(R1.getUuid()).getFullName());
    }

    @Test
    public void testLoad() throws Exception {

        Resume r = storage.load(R1.getUuid());
        Assert.assertEquals("Имя1", r.getFullName());

        Resume r2 = storage.load(R2.getUuid());
        Assert.assertEquals("Имя2", r2.getFullName());

        Resume r3 = storage.load(R3.getUuid());
        Assert.assertEquals(null, r3.getLocation());
    }

    @Test
    public void testDelete() throws Exception {
        storage.delete(R2.getUuid());
        Assert.assertEquals(2, storage.size());

        storage.delete(R1.getUuid());
        Assert.assertEquals(1, storage.size());


        //    Assert.assertEquals(null, storage.load(R1.getUuid()));
    }

    @Test
    public void testGetAllSorted() throws Exception {


    }

    @Test
    public void testSize() throws Exception {

        Assert.assertEquals(3, storage.size());
    }
}