package storage;

import model_ideal.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;



public class ArrayStorageTest extends AbstractStorageTest {

    private ArrayStorage storage = new ArrayStorage();

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

        Assert.assertEquals(true,isEquals(storage.load(R1.getUuid()),R1));
        Assert.assertEquals(true,isEquals(storage.load(R2.getUuid()),R2));
        Assert.assertEquals(true,isEquals(storage.load(R3.getUuid()),R3));

        storage.save(R3);
    }

    @Test
    public void testUpdate() throws Exception {

        Resume testUpdateRezume = new Resume(R1.getUuid(),
                "Обновленное имя", "Обновленная локация");
        storage.update(testUpdateRezume);
        Assert.assertEquals(true, isEquals(testUpdateRezume, storage.load(R1.getUuid())));

        Resume resumeBad = new Resume("Bad Resume", "Bad location");

        storage.update(resumeBad);

    }

    @Test
    public void testLoad() throws Exception {

        Resume r = storage.load(R1.getUuid());
        Assert.assertEquals(true,isEquals(r,R1));
        Resume r2 = storage.load(R2.getUuid());
        Assert.assertEquals(true,isEquals(r2,R2));
        Resume r3 = storage.load(R3.getUuid());
        Assert.assertEquals(true,isEquals(r3,R3));


        Resume resumeBad = new Resume("Bad Resume", "Bad location");

        storage.load(resumeBad.getUuid());
    }

    @Test
    public void testDelete() throws Exception {


        Assert.assertEquals(3, storage.size());

        storage.delete(R2.getUuid());
        Assert.assertEquals(2, storage.size());

//        storage.delete(R3.getUuid());
//        Assert.assertEquals(1, storage.size());
//
//        storage.delete(R1.getUuid());
//        Assert.assertEquals(0, storage.size());

    }

    @Test
    public void testGetAllSorted() throws Exception {


    }

    @Test
    public void testSize() throws Exception {

        Assert.assertEquals(3, storage.size());
    }
}