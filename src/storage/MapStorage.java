package storage;

import model_ideal.Resume;

import java.util.*;

/**
 * Created by alximik on 13/01/15.
 */
public class MapStorage extends AbstractStorage<String> {

    private Map<String, Resume> mapStorage = new HashMap<String, Resume>();


    @Override
    protected void doClear() {
        mapStorage.clear();
    }

    @Override
    protected String getContext(String uuid) {
        return uuid;
    }

    @Override
    protected boolean exist(String ctx) {
        return mapStorage.containsKey(ctx);
    }

    @Override
    protected void doSave(Resume r) {
        mapStorage.put(r.getUuid(), r);
    }

    @Override
    protected void doUpdate(String ctx, Resume r) {
        mapStorage.put(ctx, r);
    }

    @Override
    protected Resume doLoad(String ctx, String uuid) {
        return mapStorage.get(ctx);
    }

    @Override
    protected void doDelete(String ctx, String uuid) {
        mapStorage.remove(ctx);
    }

    @Override
    protected List<Resume> doGetAll() {
        return new ArrayList<>(mapStorage.values());
    }

    @Override
    public int size() {
        return mapStorage.size();
    }
}
    /*
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
*/
