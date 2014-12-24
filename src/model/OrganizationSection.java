package model;

import java.util.Date;
import java.util.List;


/**
 * Created by alximik on 24/12/14.
 */
public class OrganizationSection {

    private final String type;
    private final List<String> organizationName;
    private final List<Date> start;
    private final List<Date> finish;
    private final List<String> value;

    public OrganizationSection(String type, List<String> organizationName, List<Date> start, List<Date> finish, List<String> value) {
        this.type = type;
        this.organizationName = organizationName;
        this.start = start;
        this.finish = finish;
        this.value = value;
    }
}
