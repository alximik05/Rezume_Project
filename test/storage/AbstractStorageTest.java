package storage;

import model_ideal.Contact;
import model_ideal.ContactType;
import model_ideal.Resume;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by alximik on 13/01/15.
 */
public abstract class AbstractStorageTest {

    protected static Resume R1,R2, R3;
    protected static boolean isEquals(Resume resume1, Resume resume2) {
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
}
