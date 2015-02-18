package web;

import model_ideal.ContactType;
import model_ideal.Resume;

/**
 * Created by alximik on 16/02/15.
 */
public class HtmlUtil {
    public static String getContact(Resume r, ContactType type) {
        String contact = r.getContact(type);
        return contact == null ? "&nbsp" : type.toHtml(contact);
    }
}
