package storage;
import model_ideal.ContactType;
import model_ideal.Resume;
import model_ideal.SectionType;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

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

        R1.addObjective("Objective - позиция");
        R1.addMultiTextSection(SectionType.ACHIEVEMENT, "Достижение1", "Достижение2");
        R1.addMultiTextSection(SectionType.QUALIFICATIONS, "Квалификация1", "Квалификация2");

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
        assertEquals(0, storage.size());

    }

    @Test
    public void testSave()  {
        assertEquals(3, storage.size());

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
        assertEquals("Новое имя", (storage.load(R1.getUuid()).getFullName()));
    }

    @Test(expected = WebAppException.class)
    public void testUpdateException() throws Exception {
        Resume resumeBad = new Resume("Bad Resume", "Bad location");
        storage.update(resumeBad);
    }
    @Test
    public void testLoad() {

        assertEquals(R1, storage.load(R1.getUuid()));
        assertEquals(R2, storage.load(R2.getUuid()));
        assertEquals(R3, storage.load(R3.getUuid()));

    }
    @Test(expected = WebAppException.class)
    public void testLoadException() throws Exception{
        Resume resumeBad = new Resume("Bad Resume", "Bad location");
        storage.load(resumeBad.getUuid());
    }

    @Test
    public void testDelete()  {

        assertEquals(3, storage.size());

        storage.delete(R2.getUuid());
        storage.delete(R3.getUuid());
        storage.delete(R1.getUuid());
        assertEquals(0, storage.size());
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
        assertEquals(3, storage.size());
    }

}

