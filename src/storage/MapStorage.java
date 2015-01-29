package storage;

import model_ideal.Resume;

import java.util.*;

/**
 * Created by alximik on 13/01/15.
 */
public class MapStorage extends AbstractStorage<String> {

    private Map<String, Resume> mapStorage = new HashMap<>();


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
    protected void doSave(String uuid, Resume r) {
        mapStorage.put(uuid, r);
    }

    @Override
    protected void doUpdate(String ctx, Resume r) {
        mapStorage.put(ctx, r);
    }

    @Override
    protected Resume doLoad(String ctx) {
        return mapStorage.get(ctx);
    }

    @Override
    protected void doDelete(String ctx) {
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
