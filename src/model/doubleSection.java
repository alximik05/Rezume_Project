package model;

import java.util.Date;
import java.util.List;

/**
 * Created by alximik on 24/12/14.
 */
public class DoubleSection   {

    private final String type;
    private final List<String> names;
    private final List<Date> dates;

    public DoubleSection(String type, List<String> names, List<Date> dates, List<String> values) {
        this.type = type;
        this.names = names;
        this.dates = dates;
        this.values = values;
    }

    private final List<String> values;

}
