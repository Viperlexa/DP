package example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Parser {

    public static void parseClass(String path) throws FileNotFoundException, IOException {
        File f = new File(path);
        StringBuilder sb = new StringBuilder();
        String symb = "{";
        f.exists();

        try {
            BufferedReader in = new BufferedReader(new FileReader(f.getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    s = s.trim();
                    if (s.contains("class")) {
                        String[] spliter = s.split(" ");
                        for (int i = 0; i < spliter.length; i++) {
                            if (spliter[i].equals("class")) {
                                if (spliter[i + 1].contains(symb)) {
                                    System.out.println(spliter[i + 1].substring(0, spliter[i + 1].length() - 1));
                                } else {
                                    System.out.println(spliter[i + 1]);
                                }
                            }
                        }
                    }
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void parseMethod(String path) throws FileNotFoundException, IOException {
        File f = new File(path);
        StringBuilder sb = new StringBuilder();
        String symb = "{";
        String symb1 = "(";
        String symb2 = ")";
        f.exists();

        try {
            BufferedReader in = new BufferedReader(new FileReader(f.getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    s = s.trim();
                    if (s.contains("public") || s.contains("private") || s.contains("protected")) {
                        String[] spliter = s.split(" ");
                        if (spliter[0].equals("public") || spliter[0].equals("private") || spliter[0].equals("protected")) {
                            if (s.contains(symb1) && s.contains(symb2)) {
                                System.out.println(s);

                            }
                        }
                    }
                }
            } finally {
                in.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

