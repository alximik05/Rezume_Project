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
            outputStream = new DataOutputStream();
            if (r.getFullName() != null) {
                outputStream.writeUTF(r.getFullName());
            }
            if (r.getLocation() != null) {
                outputStream.writeUTF(r.getLocation());
            }
            if (r.getHomePage() != null) {
                outputStream.writeUTF(r.getHomePage());
            }
            if (!r.getContacts().isEmpty()) {
                Map<ContactType, String> contactsSize = r.getContacts();
                outputStream.writeInt(contactsSize.size());
                for (Map.Entry<ContactType, String> entry : contactsSize.entrySet()) {
                    outputStream.writeInt(entry.getKey().ordinal());
                    outputStream.writeUTF(entry.getValue());
                }
            }
        }

    }

    @Override
    protected Resume doRead(File file, DataInputStream inputStream) throws IOException {

            Resume resume;
            String uuid = file.getName();
            String fullName = inputStream.readUTF();
            String location = inputStream.readUTF();
            resume = new Resume(uuid, fullName, location);
            resume.setHomePage(inputStream.readUTF());
            int contactSize = inputStream.readInt();
            for (int i = 0; i < contactSize; i++) {
                resume.addContact(ContactType.VALUES[inputStream.readInt()], inputStream.readUTF());
            }
            return resume;
    }

}

