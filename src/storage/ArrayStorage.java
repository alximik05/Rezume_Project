package storage;


import model_ideal.Resume;


import java.util.*;

/**
 * Created by alximik on 04/01/15.
 */
public class ArrayStorage implements IStorage {
    private static final int LIMIT = 100;
    private Resume[] array = new Resume[LIMIT];

    @Override
    public void clear() {
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }

//    public void save(Resume resume) {
//        int idx = -1;
//        for (int i = 0; i < array.length; i++) {
//            if (array[i] != null) {
//                if (array[i].equals(resume)) {
//                    System.out.println("Резюме уже есть");
//                    break;
//                }
//            }
//            else if (idx == -1) {
//                idx = i;
//            }
//        }
//        array[idx] = resume;
//    }
    @Override
    public void save(Resume resume) {
        int idx = -1;
        boolean flag = true;
        for (int i = 0; i < array.length; i++) {
            if (array[i] != null) {
                if (array[i].equals(resume)) {
                    System.out.println("Резюме уже созднано");
                    flag = false;
                    break;
                }
            }
            else if (idx == -1) idx = i;
        }
        if (idx != -1 && flag) {
            array[idx] = resume;
        }
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
        if (idx >= LIMIT) {
            System.out.println("Резюме не найдено");
        }
    }

    @Override
    public Resume load(String uuid) {
        for (int i = 0; i < array.length; i++) {
            if (uuid.equals(array[i].getUuid())) {
                return array[i];
            }
        }

        System.out.println("Резюме с данным UUID не найдно");
        return null;
    }

    @Override
    public void delete(String uuid) {
        int idx = 1;
        for (int i = 0; i < array.length; i++) {
            idx++;
            if (uuid.equals(array[i].getUuid())) {
                array[i] = null;
                break;
            }
        }
        if (idx >= LIMIT) {
            System.out.println("Резюме с данным UUID не найдно");
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
}
