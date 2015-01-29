package model_ideal;

import java.util.Date;
import java.util.List;

/**
 * Created by alximik on 26/12/14.
 */
public class Organization {

    private  Link organization;
    private  List<OrganizationPeriod> periods;

    public Organization(Link organization, List<OrganizationPeriod> periods) {
        this.organization = organization;
        this.periods = periods;
    }

    public Organization() {
    }

    public static class OrganizationPeriod {
        private Date startDate;
        private  Date endDate;
        private  String postios;
        private  String content;

        public OrganizationPeriod(Date startDate, Date endDate, String postios, String content) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.postios = postios;
            this.content = content;
        }
    }
}

