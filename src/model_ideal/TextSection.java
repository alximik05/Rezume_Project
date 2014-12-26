package model_ideal;

import java.util.List;

/**
 * Created by alximik on 26/12/14.
 */
public class TextSection extends Section {
   private final List<String> content;

    public TextSection(String type, List<String> content) {
        super(type);
        this.content = content;
    }
}
