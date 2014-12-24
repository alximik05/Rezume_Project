package model;

import java.util.List;

/**
 * Created by alximik on 24/12/14.
 */
public enum SectionType {

    OBJECTIVE {
        String objective;
    },
    ACHIEVEMENT{
       Section achievment;
    },
    QUALIFICATIONS{
        Section qualification;
    },
    EXPERIENCE{
        DoubleSection experience;
    },
    EDUCATION{
        DoubleSection education;
    }

}
