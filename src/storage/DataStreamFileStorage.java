package storage;

import model_ideal.ContactType;
import model_ideal.Resume;

import java.io.*;
import java.util.Map;

/**
 * Created by alximik on 29/01/15.
 */
public class DataStreamFileStorage extends FileStorage {
    public DataStreamFileStorage(String path) {
        super(path);
    }

    @Override
    protected void doWrite(Resume r, OutputStream outputStream ) throws IOException {
        {
            DataOutputStream out = new DataOutputStream(outputStream);
            if (r.getFullName() != null) {
                out.writeUTF(r.getFullName());
            }
            if (r.getLocation() != null) {
                out.writeUTF(r.getLocation());
            }
            if (r.getHomePage() != null) {
                out.writeUTF(r.getHomePage());
            }
            if (!r.getContacts().isEmpty()) {
                Map<ContactType, String> contactsSize = r.getContacts();
                out.writeInt(contactsSize.size());
                for (Map.Entry<ContactType, String> entry : contactsSize.entrySet()) {
                    out.writeInt(entry.getKey().ordinal());
                    out.writeUTF(entry.getValue());
                }
            }
        }

    }

    @Override
    protected Resume doRead(File file, InputStream inputStream) throws IOException {

        DataInputStream input = new DataInputStream(inputStream);
        Resume resume;
        String uuid = file.getName();
        String fullName = input.readUTF();
        String location = input.readUTF();
        resume = new Resume(uuid, fullName, location);
        resume.setHomePage(input.readUTF());
        int contactSize = input.readInt();
        for (int i = 0; i < contactSize; i++) {
            resume.addContact(ContactType.VALUES[input.readInt()], input.readUTF());
        }
        return resume;
    }

}

