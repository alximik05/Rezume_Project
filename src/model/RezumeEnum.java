package model;

import java.util.List;

/**
 * Created by alximik on 24/12/14.
 */
public enum RezumeEnum {

    OBJECTIVE {
        String objective;
    },
    ACHIEVEMENT{
        List<Section> achievmentList;
    },
    QUALIFICATIONS{
        List<Section> qualificationList;
    },
    EXPERIENCE{
        List<DoubleSection> experienceList;
    },
    EDUCATION{
        List<DoubleSection> educationList;
    }

}
