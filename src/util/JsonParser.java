package util;
import com.google.gson.Gson;

import java.io.Reader;
import java.io.Writer;

/**
 * Created by alximik on 04/02/15.
 */
public class JsonParser {
    private static Gson GSON = new Gson();

    public static <T> T read(Reader reader, Class<T> clazz) {
        return GSON.fromJson(reader, clazz);
    }

    public static <T> void write(T object, Writer writer) {
        GSON.toJson(object, writer);
    }
}

