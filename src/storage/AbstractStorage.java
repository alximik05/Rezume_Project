package storage;
import model_ideal.Resume;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by alximik on 13/01/15.
 */
abstract public class AbstractStorage<C> implements IStorage {
    protected final Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void clear() {
        logger.info("Delete all resumes.");
        doClear();
    }

    protected abstract void doClear();

    protected abstract C getContext(String uuid);

    protected abstract boolean exist(C ctx);

    @Override
    public void save(Resume r) {
        logger.info("Save resume with uuid=" + r.getUuid());
        C ctx = getContext(r);
        if (exist(ctx)) {
            throw new WebAppException("Resume " + r.getUuid() + "already exist", r);
        }
        doSave(r);
    }

    protected abstract void doSave( Resume r);

    @Override
    public void update(Resume r) {
        logger.info("Update resume with " + r.getUuid());
        C ctx = getContext(r);
        if (!exist(ctx)) {
            throw new WebAppException("Resume " + r.getUuid() + "not exist", r);
        }
        doUpdate(ctx, r);
    }

    protected abstract void doUpdate(C ctx, Resume r);

    @Override
    public Resume load(String uuid) {
        logger.info("Load resume with uuid=" + uuid);
        C ctx = getContext(uuid);
        if (!exist(ctx)) {
            throw new WebAppException("Resume " + uuid + "not exist");
        }
        return doLoad(ctx, uuid);
    }

    protected abstract Resume doLoad(C ctx, String uuid);

    @Override
    public void delete(String uuid) {
        logger.info("Delete resume with uuid=" + uuid);
        C ctx = getContext(uuid);
        if (!exist(ctx)) {
            throw new WebAppException("Resume " + uuid + "not exist", uuid);
        }
        doDelete(ctx, uuid);
    }

    protected abstract void doDelete(C ctx, String uuid);

    @Override
    public Collection<Resume> getAllSorted() {
        logger.info("getAllSorted");
        List<Resume> list = doGetAll();
        Collections.sort(list, ( o1,  o2) ->
                  {
                    int cmp = o1.getFullName().compareTo(o2.getFullName());
                    if (cmp != 0) return cmp;
                    return o1.getUuid().compareTo(o2.getUuid());
                  }
        );
        return list;
    }

    protected abstract List<Resume> doGetAll();

    public abstract int size();

    private C getContext(Resume r) {
        return getContext(r.getUuid());
    }
}

