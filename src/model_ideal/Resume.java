package model_ideal;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Created by alximik on 26/12/14.
 */
public class Resume implements Comparable<Resume> {
    private String uuid;

    private String fullName;
    private String location;
    private String homePage;

    private List<Contact> contacts = new LinkedList<Contact>();
    private List<Section> sections = new LinkedList<Section>();


    public Resume(String uuid, String fullName, String location) {
        this.uuid = uuid;
        this.fullName = fullName;
        this.location = location;
    }

    public Resume(String fullName, String location) {
        this(UUID.randomUUID().toString(), fullName, location);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Resume resume = (Resume) o;

        if (!uuid.equals(resume.uuid)) return false;

        return true;
    }


    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void addSection(Section section) {
        sections.add(section);
    }


    public String getUuid() {
        return uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public String getLocation() {
        return location;
    }

    public String getHomePage() {
        return homePage;
    }

    public List<Contact> getContacts() {
        return contacts;
    }

    public List<Section> getSections() {
        return sections;
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

    @Override
    public int compareTo(Resume o) {
        return fullName.compareTo(o.getFullName());
    }
}
