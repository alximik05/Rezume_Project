package storage;


import model_ideal.Resume;


import java.util.*;
import java.util.logging.Logger;

/**
 * Created by alximik on 04/01/15.
 */
public class ArrayStorage extends AbstractStorage<Integer> {
    private static final int LIMIT = 100;
    private Resume[] array = new Resume[LIMIT];


    @Override
    protected void doClear() {
        Arrays.fill(array, null);
    }

    @Override
    protected Integer getContext(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (array[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    protected boolean exist(Integer ctx) {
        return ctx != -1;
    }

    @Override
    protected void doSave(Integer ctx,  Resume r) {
        array[size()] = r;
    }

    @Override
    protected void doUpdate(Integer ctx, Resume r) {
        array[ctx] = r;
    }

    @Override
    protected Resume doLoad(Integer ctx) {
        return array[ctx];
    }

    @Override
    protected void doDelete(Integer ctx) {
        int numMoved = size() - ctx - 1;
        if (numMoved > 0)
            System.arraycopy(array, ctx + 1, array, ctx,
                    numMoved);
        array[size() - 1] = null;
    }

    @Override
    protected List<Resume> doGetAll() {
        return Arrays.asList(array);
    }

    @Override
    public int size() {
        int idx = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                idx++;
            }
        }
        return idx;
    }
}
    /*
    @Override
    protected void doClear() {
        Arrays.fill(array, null);
    }

    @Override
    protected void doSave(Resume resume) {
        array[doSize()] = resume;
    }

    @Override
    protected void doUpdate(Resume resume) {
        array[getIndex(resume.getUuid())] = resume;
    }

    @Override
    protected Resume doLoad(String uuid) {
        return array[getIndex(uuid)];
    }

    @Override
    protected void doDelete(String uuid) {
        int idx = -1;
        for (int i = 0; i < doSize(); i++) {
            if (array[i].getUuid().equals(uuid)) {
                idx = i;
                break;
            }
        }
        int numMoved = doSize() - idx - 1;
        if (numMoved > 0)
            System.arraycopy(array, idx + 1, array, idx, numMoved);
    }


    @Override
    protected int doSize() {
        int idx = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                idx++;
            }
        }
        return idx;
    }

    @Override
    public boolean isContain(String uuid) {
        for (int i = 0; i < doSize(); i++) {
            if (uuid.equals(array[i].getUuid())) {
                return true;
            }
        }
        return false;
    }

    public int getIndex(String uuid) {
        for (int i = 0; i < size(); i++) {
            if (array[i].getUuid().equals(uuid)) {
                return i;
            }
        }
        return -1;
    }

}













    /*
    @Override
    public void save(Resume resume) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                if (array[i].equals(resume)) {

                    throw new IllegalArgumentException("Резюме уже созднано");
                }
            }
        }
        array[size()] = resume;
    }

    @Override
    public void update(Resume resume) {
        int idx = 1;
        for (int i = 0; i < array.length; i++) {
            idx++;
            if (array[i].equals(resume)) {
                array[i] = resume;
                break;
            }
        }
        if (idx > LIMIT) {
            throw new IllegalArgumentException("Резюме не найдно");
        }
    }

    @Override
    public Resume load(String uuid) {
        for (int i = 0; i < array.length; i++) {
            if (uuid.equals(array[i].getUuid())) {
                return array[i];
            }
        }
        throw new IllegalArgumentException("Резюме с данным UUID не найдно");
    }

    @Override
    public void delete(String uuid) {
        int idx = 0;
        for (int i = 0; i < array.length; i++) {
            idx++;
            if (uuid.equals(array[i].getUuid())) {
                for (int j = i; j < size(); j++) {
                    array[j] = array[j + 1];
                }
                break;
            }
        }
        if (idx > LIMIT) {
            throw new IllegalArgumentException("Резюме с данным UUID не найдно");
        }
    }

    @Override
    public Collection<Resume> getAllSorted() {
        List<Resume> list = Arrays.asList(array);
        Collections.sort(list);
        return list;
    }

    @Override
    public int size() {
        int idx = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                idx++;
            }
        }
        return idx;
    }

    @Override
    public int getIndex(String uuid) {
        for (int i = 0; i < LIMIT; i++) {
            if (array[i] != null) {
                if (array[i].getUuid().equals(uuid)) {
                    return i;
                }
            }
        }
        return -1;
    }
    */