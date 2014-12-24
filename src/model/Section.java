package model;

import java.util.List;

/**
 * Created by alximik on 24/12/14.
 */
public class Section {
    private final String type;
    private final List<String> value;

    public Section(String type, List<String> value) {
        this.type = type;
        this.value = value;
    }
}
