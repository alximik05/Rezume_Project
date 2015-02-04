package storage;

import model_ideal.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by alximik on 29/01/15.
 */

public class DataStreamFileStorage extends FileStorage {
    private static final String DEFAULT = "default";


    public DataStreamFileStorage(String path) {
        super(path);
    }

    @Override
    protected void doWrite(OutputStream out, Resume r) throws IOException {
        try (DataOutputStream outputStream = new DataOutputStream(out)) {
            writeString(outputStream, r.getUuid());
            writeString(outputStream, r.getFullName());
            writeString(outputStream, r.getHomePage());
            writeString(outputStream, r.getLocation());

            Map<ContactType, String> contacts = r.getContacts();

            writeCollection(outputStream,contacts.entrySet(), contactTypeStringEntry -> {
                outputStream.writeInt(contactTypeStringEntry.getKey().ordinal());
                writeString(outputStream, contactTypeStringEntry.getValue());
            });

//            writeCollection(outputStream,contacts.entrySet(),new ElementWriter<Map.Entry<ContactType, String>>() {
//                @Override
//                public void write(Map.Entry<ContactType, String> contactTypeStringEntry) throws IOException {
//                    outputStream.writeInt(contactTypeStringEntry.getKey().ordinal());
//                    writeString(outputStream, contactTypeStringEntry.getValue());
//                }
//            });
//

            Map<SectionType, Section> sections = r.getSections();
            outputStream.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                SectionType type = entry.getKey();
                Section section = entry.getValue();
                writeString(outputStream, type.name());
                switch (type) {
                    case OBJECTIVE:
                        writeString(outputStream, ((TextSection) section).getValue());
                        break;
                    case ACHIEVEMENT:

                    case QUALIFICATIONS:
                        writeCollection(outputStream, ((MultiTextSection) section).getValues(), s -> writeString(outputStream,s));
                        break;
//                        writeCollection(outputStream, ((MultyTextSection) section).getValues(), new ElementWriter<String>() {
//                            @Override
//                            public void write(String s) throws IOException {
//                                writeString(outputStream,s);
//                            }
//                        });
                    case EXPERIENCE :


                    case EDUCATION :
                        writeCollection(outputStream,((OrganizationSection) section).getValues(), new ElementWriter<Organization>() {
                        @Override
                        public void write(Organization organization) throws IOException {
                            writeString(outputStream, organization.getOrganization().toString());
                            writeCollection(outputStream,organization.getPeriods(),new ElementWriter<Organization.OrganizationPeriod>() {
                                @Override
                                public void write(Organization.OrganizationPeriod organizationPeriod) throws IOException {
                                    writeString(outputStream,organizationPeriod.getStartDate().toString());
                                    writeString(outputStream, organizationPeriod.getEndDate().toString());
                                    writeString(outputStream, organizationPeriod.getPositios());
                                    writeString(outputStream, organizationPeriod.getContent());
                                }
                            });
                        }
                    } );
                }
            }
        }
    }

    @Override
    protected Resume doRead(InputStream reader) throws IOException {
        Resume resume = new Resume();
        try (DataInputStream inputStream = new DataInputStream(reader)) {
            resume.setUuid(readString(inputStream));
            resume.setFullName(readString(inputStream));
            resume.setHomePage(readString(inputStream));
            resume.setLocation(readString(inputStream));
            int contactSize = inputStream.readInt();
            for (int i = 0; i < contactSize; i++) {
                resume.addContact(ContactType.VALUES[inputStream.readInt()], inputStream.readUTF());
            }
            final int sectionSize = inputStream.readInt();
            for (int i = 0; i < sectionSize; i++) {
                SectionType sectionType = SectionType.valueOf(readString(inputStream));

                switch (sectionType) {
                    case OBJECTIVE:
                        resume.addObjective(readString(inputStream));
                        break;
                    case ACHIEVEMENT:

                    case QUALIFICATIONS:
                        resume.addSection(sectionType,new MultiTextSection(readList(inputStream, () -> readString(inputStream))));
                        break;
                    case EXPERIENCE:

                    case EDUCATION:
                        resume.addSection(sectionType,new OrganizationSection(readList(inputStream, new ElementReader() {
                            @Override
                            public Object read() throws IOException {
                                return
                            }
                        })));

                }
            }

        }

        return resume;
    }


    private void writeString(DataOutputStream out, String str) throws IOException {
        out.writeUTF(str == null ? DEFAULT : str);
    }

    private String readString(DataInputStream in) throws IOException {
        String str = in.readUTF();
        return str.equals(DEFAULT) ? null : str;
    }

    private interface ElementWriter<T> {
        void write(T t) throws IOException;
    }

    private interface ElementReader<T> {
       T read() throws IOException;
    }

    private <T> void writeCollection(DataOutputStream out, Collection<T> collection, ElementWriter<T> writer) throws IOException {
        out.writeInt(collection.size());

        for (T item : collection){
            writer.write(item);
        }

    }


    private <T> List<T> readList(DataInputStream in, ElementReader reader) throws IOException {
        int size = in.readInt();
        List<T> list = new ArrayList<>(size);

        for (int i = 0; i < size; i++) {
            list.add((T) reader.read());

        }
        return list;
    }
}
