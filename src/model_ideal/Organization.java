package model_ideal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alximik on 26/12/14.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Organization implements Serializable {

    private  Link link;
    private  List<OrganizationPeriod> periods;

    public Organization(Link link, List<OrganizationPeriod> periods) {
        this.link = link;
        this.periods = periods;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public Organization(Link organization, OrganizationPeriod... organizationsPeriod) {
        this(organization, new LinkedList<>(Arrays.asList(organizationsPeriod)));

    }



    public Organization(String nameOrganiztion, String siteOrganization, OrganizationPeriod... periods) {
        this.link = new Link(nameOrganiztion, siteOrganization);
        this.periods = new LinkedList<>(Arrays.asList(periods));
    }

    public Link getLink() {
        return link;
    }

    public List<OrganizationPeriod> getPeriods() {
        return periods;
    }

    public Organization() {
    }


    public void addPeriod(OrganizationPeriod period) {
        periods.add(period);
    }

    public static class OrganizationPeriod implements Serializable {

        static final long serialVersionUID = 1l;

        public static final LocalDate NOW = LocalDate.of(3000, 1, 1);
        private LocalDate startDate;
        private  LocalDate endDate;



        public void setStartDate(LocalDate startDate) {
            this.startDate = startDate;
        }

        public void setEndDate(LocalDate endDate) {
            this.endDate = endDate;
        }

        public void setPositios(String positios) {
            this.positios = positios;
        }

        public void setContent(String content) {
            this.content = content;
        }

        private  String positios;
        private  String content;

        public OrganizationPeriod() {

        }


        public LocalDate getStartDate() {
            return startDate;
        }

        public LocalDate getEndDate() {
            return endDate;
        }

        public String getPositios() {
            return positios;
        }

        public String getContent() {
            return content;
        }






        public OrganizationPeriod(LocalDate startDate, LocalDate endDate, String positios, String content) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.positios = positios;
            this.content = content;
        }

        public OrganizationPeriod(int startYear, Month startMonth, int endYear, Month endMonth, String positios, String content) {

            this(LocalDate.of(startYear, startMonth, 1), LocalDate.of(endYear, endMonth, 1), positios, content);
        }
    }
}

