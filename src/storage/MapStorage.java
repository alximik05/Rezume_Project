package storage;

import model_ideal.Resume;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by alximik on 13/01/15.
 */
public class MapStorage extends AbstractStorage {

    private Map<String, Resume> mapStorage = new HashMap<String, Resume>();

    @Override
    protected void doClear() {
        mapStorage.clear();
    }

    @Override
    protected void doSave(Resume resume) {
        mapStorage.put(resume.getUuid(), resume);
    }

    @Override
    protected void doUpdate(Resume resume) {
        doSave(resume);
    }

    @Override
    protected Resume doLoad(String uuid) {
        return mapStorage.get(uuid);
    }

    @Override
    protected void doDelete(String uuid) {
        mapStorage.remove(uuid);
    }

    @Override
    protected Collection<Resume> doGetAllSorted() {
        return null;
    }

    @Override
    protected int doSize() {
        return mapStorage.size();
    }

    @Override
    public boolean isContain(String uuid) {
        return mapStorage.containsKey(uuid);
    }
}
