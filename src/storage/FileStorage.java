package storage;

import model_ideal.Resume;

import java.io.*;
import java.util.*;

/**
 * Created by alximik on 27/01/15.
 */
public abstract class FileStorage extends AbstractStorage<File> {

    private File dir;

    public FileStorage(String path) {
        this.dir = new File(path);
        if (!dir.isDirectory() || !dir.canWrite()) {
            throw new IllegalArgumentException("'" + path + "' is not directory");
        }
    }

    @Override
    protected void doClear() {
        File[] files = dir.listFiles();
        if (files == null) return;
        for (File file : files) {
            doDelete(file);

        }
    }


    @Override
    protected File getContext(String fileName) {
        return new File(dir, fileName);
    }

    @Override
    protected boolean exist(File ctx) {
        return ctx.exists();
    }

    @Override
    protected void doSave(File ctx, Resume r) {
        try {
            ctx.createNewFile();
        } catch (IOException e) {
            throw new WebAppException("Couldn't create file " + ctx.getAbsolutePath(), r, e);
        }
        write(ctx, r);
    }

     protected void write(File ctx, Resume r) {
         try
         {
             doWrite(new FileOutputStream(ctx),r);
         }
         catch (IOException e) {
             throw new WebAppException("Couldn't write file", r, e);
         }
     }

    protected Resume read(File file)
    {
        try {
            return doRead(new FileInputStream(file));
        }
        catch (IOException e) {
            throw new WebAppException("Couldn't read file ", e);
        }
    }
    protected abstract void doWrite(OutputStream out, Resume r) throws IOException;
    protected abstract Resume doRead(InputStream reader) throws IOException;

    @Override
    protected void doUpdate(File ctx, Resume r) {
        write(ctx,r);
    }

    @Override
    protected Resume doLoad(File ctx) {
        return read(ctx);
    }

    @Override
    protected void doDelete(File ctx) {
        if (!ctx.delete()) {
            throw new WebAppException("File " + ctx.getAbsolutePath() + " can't  be deleted");
        }
    }

    @Override
    protected List<Resume> doGetAll() {
        File[] files = dir.listFiles();
        if (files == null) {
            return Collections.emptyList();
        }
        List<Resume> list = new ArrayList<>(files.length);
        for (File file : files) {
            list.add(read(file));
        }
        return list;

    }

    @Override
    public int size() {
        String[] list = dir.list();
        if(list == null) throw new WebAppException("Couldn't read directory " + dir.getAbsolutePath());
        return list.length;
    }
}
