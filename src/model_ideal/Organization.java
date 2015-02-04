package model_ideal;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;

/**
 * Created by alximik on 26/12/14.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Organization {

    private  Link organization;
    private  List<OrganizationPeriod> periods;

    public Organization(Link organization, List<OrganizationPeriod> periods) {
        this.organization = organization;
        this.periods = periods;
    }

    public Link getOrganization() {
        return organization;
    }

    public List<OrganizationPeriod> getPeriods() {
        return periods;
    }

    public Organization() {
    }

    public static class OrganizationPeriod implements Serializable {

        static final long serialVersionUID = 1l;

        public static final LocalDate NOW = LocalDate.of(3000, 1, 1);
        private LocalDate startDate;
        private  LocalDate endDate;
        private  String positios;

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

        private  String content;



        public OrganizationPeriod(LocalDate startDate, LocalDate endDate, String positios, String content) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.positios = positios;
            this.content = content;
        }

        public OrganizationPeriod(int startYear, Month startMonth, int endYear, Month endMonth, String positios, String content) {

            this(LocalDate.of(startYear, startMonth, 1), LocalDate.of(startYear, startMonth, 1), positios, content);
        }
    }
}

