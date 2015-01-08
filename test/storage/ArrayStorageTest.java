package storage;

import model_ideal.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;



public class ArrayStorageTest {

    private  static Resume R1,R2, R3;

    private ArrayStorage storage = new ArrayStorage();

    private static boolean isEquals(Resume resume1, Resume resume2) {
        return  (resume1.equals(resume2) && resume1.getFullName().equals(resume2.getFullName()));

    }

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

        Assert.assertEquals(true,isEquals(storage.load(R1.getUuid()),R1));
        Assert.assertEquals(true,isEquals(storage.load(R2.getUuid()),R2));
        Assert.assertEquals(true,isEquals(storage.load(R3.getUuid()),R3));

    }

    @Test
    public void testUpdate() throws Exception {

        Resume testUpdateRezume = new Resume(R1.getUuid(),
                "Обновленное имя", "Обновленная локация");
        storage.update(testUpdateRezume);
        Assert.assertEquals(true, isEquals(testUpdateRezume, storage.load(R1.getUuid())));
    }

    @Test
    public void testLoad() throws Exception {

        Resume r = storage.load(R1.getUuid());
        Assert.assertEquals(true,isEquals(r,R1));
        Resume r2 = storage.load(R2.getUuid());
        Assert.assertEquals(true,isEquals(r2,R2));
        Resume r3 = storage.load(R3.getUuid());
        Assert.assertEquals(true,isEquals(r3,R3));

//        Resume r2 = storage.load(R2.getUuid());
//        Assert.assertEquals("Имя2", r2.getFullName());
//
//        Resume r3 = storage.load(R3.getUuid());
//        Assert.assertEquals(null, r3.getLocation());
    }

    @Test
    public void testDelete() throws Exception {


        Assert.assertEquals(3, storage.size());

        storage.delete(R2.getUuid());
        Assert.assertEquals(2, storage.size());

        storage.delete(R3.getUuid());
        Assert.assertEquals(1, storage.size());

        storage.delete(R1.getUuid());
        Assert.assertEquals(0, storage.size());


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