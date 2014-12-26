package model_ideal;

import java.util.List;

/**
 * Created by alximik on 26/12/14.
 */
public class OrganiztionSection extends Section {
    private final List<Organization> organizations;

    public OrganiztionSection(String type, List<Organization> organizations) {
        super(type);
        this.organizations = organizations;
    }
}
