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
    protected void doWrite(Resume r, OutputStream out) throws IOException {

    }


    @Override
    protected Resume doRead(File file, DataInputStream reader) throws IOException {
        return null;
    }

//    @Override
//    protected void write(File ctx, Resume r) {
//        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(ctx)))
//        {
//            ;
//        } catch (IOException e) {
//            throw new WebAppException("Couldn't write file " + ctx.getAbsolutePath(), r, e);
//        }
//
//    }
//
//    @Override
//    protected Resume read(File file) {
//        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
//
//                return (Resume) objectInputStream.readObject();
//
//        } catch (IOException e) {
//            throw new WebAppException("Couldn't read file " + file.getAbsolutePath(), e);
//        }
//        catch (ClassNotFoundException e) {
//            throw new WebAppException("Error read resume ", e);
//        }
//    }
}
