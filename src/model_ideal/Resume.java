package model_ideal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.*;

/**
 * Created by alximik on 26/12/14.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Resume implements Serializable{
    static final long serialVersionUID = 1l;
    private String uuid;
    private String fullName;


    private String location;
    private String homePage;
    private Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
    private Map<SectionType, Section> sections = new EnumMap<>(SectionType.class);

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

    @Override
    public String toString() {
        return "Resume{" +
                "uuid='" + uuid + '\'' +
                ", fullName='" + fullName + '\'' +
                ", location='" + location + '\'' +
                ", homePage='" + homePage + '\'' +
                ", contacts=" + contacts +
                ", sections=" + sections +
                '}';
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }




    public void addContact(ContactType contactType, String value ) {
        contacts.put(contactType, value);
    }

    public void addSection(SectionType type, Section section) {
        sections.put(type, section);
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void addObjective(String value) {
        addSection(SectionType.OBJECTIVE, new TextSection(value));
    }

    public void addMultiTextSection(SectionType type ,String... value) {
        addSection(type, new MultiTextSection(value));
    }

    public void addOrganizationSection(SectionType type, List<Organization> organizations) {
        addSection(type,new OrganizationSection(organizations));
    }

    public void addOrganizationSection(SectionType type, Organization... organizations) {
        addSection(type,new OrganizationSection(organizations));
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

    public Section getSections(SectionType sectionType) {
        return  sections.get(sectionType);
    }

    public Map<SectionType, Section> getSections() {
        return sections;
    }

    @Override
    public int hashCode() {
        return uuid.hashCode();
    }


}
