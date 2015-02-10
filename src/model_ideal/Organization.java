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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        if (link != null ? !link.equals(that.link) : that.link != null) return false;
        if (periods != null ? !periods.equals(that.periods) : that.periods != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = link != null ? link.hashCode() : 0;
        result = 31 * result + (periods != null ? periods.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "link=" + link +
                ", periods=" + periods +
                '}';
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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            OrganizationPeriod period = (OrganizationPeriod) o;

            if (content != null ? !content.equals(period.content) : period.content != null) return false;
            if (endDate != null ? !endDate.equals(period.endDate) : period.endDate != null) return false;
            if (positios != null ? !positios.equals(period.positios) : period.positios != null) return false;
            if (startDate != null ? !startDate.equals(period.startDate) : period.startDate != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = startDate != null ? startDate.hashCode() : 0;
            result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
            result = 31 * result + (positios != null ? positios.hashCode() : 0);
            result = 31 * result + (content != null ? content.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "OrganizationPeriod{" +
                    "startDate=" + startDate +
                    ", endDate=" + endDate +
                    ", positios='" + positios + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }

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

