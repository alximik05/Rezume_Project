package model_ideal;

/**
 * Created by alximik on 04/01/15.
 */
public enum ContactType {
    PHONE("Телефон"),
    MOBILE("Моб. телефон"),
    HOME_PHONE("Дом. телефон"),
    SKYPE("Скайп"),
    MAIL("Почта"),
    ICQ("ICQ");

    private String title;

    public String getTitle() {
        return title;
    }

    ContactType(String title) {
        this.title = title;
    }
}
