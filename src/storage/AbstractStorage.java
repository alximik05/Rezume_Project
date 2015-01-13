package storage;

import model_ideal.Resume;
import java.util.Collection;
import java.util.logging.Logger;

/**
 * Created by alximik on 13/01/15.
 */
public abstract class AbstractStorage implements IStorage {

    Logger logger = Logger.getLogger(getClass().getName());

    @Override
    public void clear() {
        logger.info("Clear all storage");
        doClear();

    }

    protected abstract void doClear();


    @Override
    public void save(Resume resume) {
        logger.info("Save resume with UUID " + resume.getUuid() );
        if (isContain(resume.getUuid())) {
            throw new WebAppException("Resume " + resume.getUuid() + " already exist in storage", resume);
        }
        doSave(resume);
        
    }

    protected abstract void doSave(Resume resume);

    @Override
    public void update(Resume resume) {

        logger.info("Update resume with UUID " + resume.getUuid() );
        if (!isContain(resume.getUuid())) {
            throw new WebAppException("Resume " + resume.getUuid() + " not found in storage");
        }
        doUpdate(resume);
    }

    protected abstract void doUpdate(Resume resume);

    @Override
    public  Resume load(String uuid) {
        logger.info("Load resume with UUID " + uuid);
        if (!isContain(uuid)) {
            throw new WebAppException("Resume " + uuid + " not found in storage");
        }
        return doLoad(uuid);
    }

    protected abstract Resume doLoad(String uuid);

    @Override
    public void delete(String uuid) {
        logger.info("Delete resume with UUID " + uuid);
        if (!isContain(uuid)) {
            throw new WebAppException("Resume " + uuid + " not found in storage");
        }        doDelete(uuid);
    }

    protected abstract void doDelete(String uuid);

    @Override
    public Collection<Resume> getAllSorted() {
        logger.info("Sort by full name");
        return doGetAllSorted();

    }

    protected abstract Collection<Resume> doGetAllSorted();

    @Override
    public int size() {
        return doSize();
    }

    protected abstract int doSize();

    public abstract boolean isContain(String uuid);


}

