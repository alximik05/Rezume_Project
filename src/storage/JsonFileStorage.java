package storage;

import com.google.gson.Gson;
import model_ideal.Resume;
import util.JsonParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by alximik on 04/02/15.
 */
public class JsonFileStorage extends FileStorage {

    public JsonFileStorage(String path) {
        super(path);
    }

    @Override
    protected void doWrite(OutputStream out, Resume r) throws IOException {
        try (Writer writer = new OutputStreamWriter(out, StandardCharsets.UTF_8)) {
            JsonParser.write(r,writer);

        }
    }

    @Override
    protected Resume doRead(InputStream reader) throws IOException {
        try (Reader reader1 = new InputStreamReader(reader,StandardCharsets.UTF_8)) {
            return JsonParser.read(reader1, Resume.class);
        }
    }

    @Override
    public boolean isSectonSupported() {
        return false;
    }
}
