package model_ideal;

/**
 * Created by alximik on 26/12/14.
 */
public class Contact {
    private final  ContactType type;

    public String getContent() {
        return content;
    }

    private final  String content;

    public Contact(ContactType type, String content) {
        this.type = type;
        this.content = content;
    }


}
