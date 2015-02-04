package storage;

import model_ideal.Resume;

import java.io.*;

/**
 * Created by alximik on 29/01/15.
 */
public class SerializeStorage extends FileStorage {
    public SerializeStorage(String path) {
        super(path);
    }

    @Override
    protected void doWrite(OutputStream out, Resume r) throws IOException {
        try(ObjectOutputStream outputStream = new ObjectOutputStream(out))
        {
            outputStream.writeObject(r);
        }
    }

    @Override
    protected Resume doRead(InputStream reader) throws IOException {
        try (ObjectInputStream inputStream = new ObjectInputStream(reader)) {
            return (Resume) inputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new WebAppException("Error read resume ", e);
        }
    }
}
