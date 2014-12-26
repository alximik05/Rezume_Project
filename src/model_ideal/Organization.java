package model_ideal;

import java.util.List;

/**
 * Created by alximik on 26/12/14.
 */
public class Organization {

    private final Link organization;
    private final List<Period> periods;

    public Organization(Link organization, List<Period> periods) {
        this.organization = organization;
        this.periods = periods;
    }
}
