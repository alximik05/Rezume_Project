package model_ideal;

import java.io.*;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by alximik on 04/01/15.
 */
public class Main   {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
//        List<Resume> list = new LinkedList<>();
//        list.forEach(r -> System.out.println(r));

       Iterator<String> it  = Files.lines(Paths.get("/Users/alximik/Desktop/file1"), StandardCharsets.UTF_8).iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
