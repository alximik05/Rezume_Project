package model_ideal;

/**
 * Created by alximik on 24/12/14.
 */
public enum SectionType {
    OBJECTIVE("Позиция"),
    ACHIEVEMENT("Достижения"),
    QUALIFICATIONS("Квалификация"),
    EXPERIENCE("Опыт работы"),
    EDUCATION("Образование");

    private String title;

    public String getTitle() {
        return title;
    }

    SectionType(String title) {
        this.title = title;
    }
}
