package model_ideal;

import java.util.Date;

/**
 * Created by alximik on 26/12/14.
 */
public class Period {
    private final Date startDate;
    private final Date endDate;
    private final String postios;
    private final String content;

    public Period(Date startDate, Date endDate, String postios, String content) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.postios = postios;
        this.content = content;
    }
}
