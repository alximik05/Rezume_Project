package storage;

import model_ideal.Resume;

import java.util.Collection;


/**
 * Created by alximik on 04/01/15.
 */
public interface IStorage {
    void clear();

    void save(Resume resume);

    void update(Resume resume);

    Resume load(String uuid);

    void delete(String uuid);

    Collection<Resume> getAllSorted();

    int size();

    boolean isSectonSupported();
}
