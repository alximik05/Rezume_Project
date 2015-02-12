package storage;

import model_ideal.*;

import java.io.*;
import java.time.LocalDate;
import java.time.Month;
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
                        writeCollection(outputStream, ((OrganizationSection) section).getValues(),new ElementWriter<Organization>() {
                            @Override
                            public void write(Organization organization) throws IOException {
                                writeOrganization(outputStream, organization);
                            }
                        });
                        break;
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

                        resume.addOrganizationSection(sectionType, readList(inputStream,new ElementReader<Organization>() {
                            @Override
                            public Organization read() throws IOException {
                                return readOrganization(inputStream);
                            }
                        }));
                        break;
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

    private void writeOrganization(DataOutputStream out, Organization org) throws IOException {
        writeString(out, org.getLink().getName());
        writeString(out, org.getLink().getUrl());

        out.writeInt(org.getPeriods().size());

        for (Organization.OrganizationPeriod period : org.getPeriods()) {
            out.writeInt(period.getStartDate().getYear());
                out.writeInt(period.getStartDate().getMonthValue());
                out.writeInt(period.getEndDate().getYear());
                out.writeInt(period.getEndDate().getMonthValue());

                writeString(out, period.getPositios());
                writeString(out, period.getContent());
        }
//        writeCollection(out, org.getPeriods(), new ElementWriter<Organization.OrganizationPeriod>() {
//            @Override
//            public void write(Organization.OrganizationPeriod organizationPeriod) throws IOException {
//                out.writeInt(organizationPeriod.getStartDate().getYear());
//                out.writeInt(organizationPeriod.getStartDate().getMonthValue());
//                out.writeInt(organizationPeriod.getEndDate().getYear());
//                out.writeInt(organizationPeriod.getEndDate().getMonthValue());
//
//                writeString(out, organizationPeriod.getPositios());
//                writeString(out, organizationPeriod.getContent());
//            }
//        });
    }

    private Organization readOrganization(DataInputStream in) throws IOException {
        Organization org = new Organization();
        Link link = new Link(readString(in), readString(in));
        org.setLink(link);

        int periodSize = in.readInt();
        for (int i = 0; i < periodSize; i++) {
            Organization.OrganizationPeriod period = new Organization.OrganizationPeriod();
            period.setStartDate(LocalDate.of(in.readInt(), Month.of(in.readInt()), 1));
            period.setEndDate(LocalDate.of(in.readInt(), Month.of(in.readInt()), 1));
            period.setPositios(readString(in));
            period.setContent(readString(in));

            org.addPeriod(period);
        }
        return org;

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

    private <T> List<T> readList(DataInputStream dis, ElementReader<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(reader.read());
        }
        return list;
    }
}
