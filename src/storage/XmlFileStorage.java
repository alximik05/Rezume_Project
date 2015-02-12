package storage;
import model_ideal.*;
import util.XmlParser;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by alximik on 04/02/15.
 */
public class XmlFileStorage extends FileStorage {
    private XmlParser xmlParser;

    public XmlFileStorage(String path) {
        super(path);
        xmlParser = new XmlParser(Resume.class, Organization.class, Link.class,
                OrganizationSection.class, TextSection.class, Organization.OrganizationPeriod.class);
    }

    @Override
    protected void doWrite(OutputStream out, Resume r) throws IOException {
    try(Writer writer = new OutputStreamWriter(out, StandardCharsets.UTF_8))
        {
         xmlParser.marshall(r,writer);
        }
    }

    @Override
    protected Resume doRead(InputStream reader) throws IOException {

        try (Reader reader1 = new InputStreamReader(reader, StandardCharsets.UTF_8)) {
             return xmlParser.unmarshall(reader1);
        }

    }

}
