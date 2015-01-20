package model_ideal;

import java.util.*;

/**
 * Created by alximik on 26/12/14.
 */
public class Resume {
    private String uuid;


    private String fullName;
    private String location;
    private String homePage;

    //  private List<Contact> contacts = new LinkedList<Contact>();
    private Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);

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
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final Resume other = (Resume) obj;
        return Objects.equals(this.uuid, other.uuid) && Objects.equals(this.fullName, other.fullName) && Objects.equals(this.location, other.location) && Objects.equals(this.homePage, other.homePage) && Objects.equals(this.contacts, other.contacts) && Objects.equals(this.sections, other.sections);
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }


    public void addContact(ContactType contactType, String value ) {
        contacts.put(contactType, value);
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

    public String getContacts(ContactType contactType) {
        return contacts.get(contactType);
    }

    public List<Section> getSections() {
        return sections;
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

}
