package model_ideal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alximik on 26/12/14.
 */
public class OrganizationSection extends Section {

    private List<Organization> values;

    public OrganizationSection() {
    }

    public OrganizationSection(Organization... values) {
        this.values = new LinkedList<>(Arrays.asList(values));
    }

    public OrganizationSection(List<Organization> values) {

    }

//    public OrganizationSection(List<Organization> organizations) {
//    }

    @Override
    public String toString() {
        return values.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganizationSection that = (OrganizationSection) o;

        if (values != null ? !values.equals(that.values) : that.values != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return values != null ? values.hashCode() : 0;
    }

    public List<Organization> getValues() {
        return values;
    }
}
