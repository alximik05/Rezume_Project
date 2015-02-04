package model_ideal;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alximik on 26/12/14.
 */
public class MultiTextSection extends Section {
   private  List<String> content;

    public MultiTextSection(String... values) {
        this(new LinkedList<>(Arrays.asList(values)));
    }

    public MultiTextSection(List<String> values) {
        this.content = values;
    }

    public List<String> getValues() {
        return content;
    }

    @Override
    public String toString() {
        return content.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MultiTextSection that = (MultiTextSection) o;

        if (content != null ? !content.equals(that.content) : that.content != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return content != null ? content.hashCode() : 0;
    }

    public MultiTextSection() {

    }
}
