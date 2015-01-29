package model_ideal;

import java.io.Serializable;
import java.util.*;

/**
 * Created by alximik on 26/12/14.
 */
public class Resume implements Serializable{

    private String uuid;
    private String fullName;


    private String location;
    private String homePage;
    private Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private Map<SectionType, String> sections = new EnumMap<>(SectionType.class);

    public void setLocation(String location) {
        this.location = location;
    }

    public void setHomePage(String homePage) {
        this.homePage = homePage;
    }

    public static final Resume EMPTY;
    static {
        EMPTY = new Resume();
    }

    public Resume(String uuid, String fullName, String location) {
        this.uuid = uuid;
        this.fullName = fullName;
        this.location = location;
    }


    public Resume() {
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

    public void addSection(SectionType section, String value) {
        sections.put(section, value);
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

    public Map<ContactType, String> getContacts() {
        return contacts;
    }

    public String getContact(ContactType contactType) {
        return contacts.get(contactType);
    }

    public String getSections(SectionType sectionType) {
        return  sections.get(sectionType);
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }

}
