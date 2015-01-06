package model_ideal;

import java.util.List;

/**
 * Created by alximik on 26/12/14.
 */
public class OrganiztionSection extends Section {

    private List<Organization> values;

    public OrganiztionSection(SectionType type, List<Organization> values) {
        super(type);
        this.values = values;
    }
}
