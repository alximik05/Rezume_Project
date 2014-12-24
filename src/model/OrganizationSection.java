package model;

import java.util.Date;
import java.util.List;


/**
 * Created by alximik on 24/12/14.
 */
public class OrganizationSection extends Section {


    private final List<String> organizationName;
    private final List<Date> start;
    private final List<Date> finish;


    public OrganizationSection(String type, List<String> value, List<String> organizationName, List<Date> start, List<Date> finish) {
        super(type, value);
        this.organizationName = organizationName;
        this.start = start;
        this.finish = finish;
    }
}
