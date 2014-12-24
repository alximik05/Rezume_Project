package model;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by alximik on 24/12/14.
 */
public class OrganizationSection {

    private final String type;
    private final String organizationName;
    private final Map<Date, String> timeAndValue;

    public OrganizationSection(String type, String organizationName, Map<Date, String> timeAndValue) {
        this.type = type;
        this.organizationName = organizationName;
        this.timeAndValue = timeAndValue;
    }
}
