package test;

import java.io.*;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.nio.file.Files.walkFileTree;

public class Main {

    public static void main(String[] args) throws IOException {
        for (File file : listFiles("D:\\IdeaProjects\\FileProcess\\src\\test")) {
            List<String> lines = linesInFile(file);
            delAllArgsConstructor(lines, file.getName().split("\\.")[0]);
            addAnotation(lines);

//            for (String s : lines) {
//                System.out.println(s);
//            }
//            System.out.println();
            writeFile(lines, file.getAbsolutePath());
        }
    }


    public static List<File> listFiles(String folder) {
        final List<File> files = new ArrayList<File>();
        SimpleFileVisitor<Path> finder = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                files.add(file.toFile());
                return super.visitFile(file, attrs);
            }
        };
        Path path = Paths.get(folder);
        try {
            walkFileTree(path, finder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return files;
    }


    public static List<String> linesInFile(File file) throws IOException {
        List<String> ls = new ArrayList<>();
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
        BufferedReader reader = new BufferedReader(isr);
        String line;
        while ((line = reader.readLine()) != null) {
            ls.add(line);
        }
        reader.close();
        isr.close();
        return ls;
    }


    public static void delAllArgsConstructor(List<String> content, String className) {
        String[] lines = content.toArray(new String[content.size()]);
        int leftBrace = 0, rightBrace = 0;
        int start, end;
        boolean hasBuilder = false;

        for (start = 0; start < lines.length; start++) {
            if (lines[start].replace(" ", "").startsWith("public" + className)) {
//                System.out.println("find allArgsConstructor in " + className + ": line " + start);
                content.clear();

                if (lines[start - 1].trim().equals("@Builder")) hasBuilder = true;
                for (end = start; end < lines.length; end++) {
                    if (lines[end].contains("{")) leftBrace++;
                    if (lines[end].contains("}")) rightBrace++;
                    if (rightBrace == leftBrace) break;
                }
                if (hasBuilder) start--;
                content.addAll(Arrays.asList(lines).subList(0, start));
                content.addAll(Arrays.asList(lines).subList(end + 1, lines.length));
                break;
            }
        }
    }

    public static void addAnotation(List<String> content) {
        //第一行是package
        content.add(1, "import lombok.experimental.SuperBuilder;");

        for (int i = 0; i < content.size(); i++) {
            if (content.get(i).replace(" ", "").startsWith("publicclass")) {
                //仅查找构造函数之前的@注解
                return;
            }
            if (content.get(i).trim().startsWith("@")) {
                content.add(i, "@SuperBuilder");
                break;
            }
        }
    }

    public static void writeFile(List<String> content, String path) throws IOException {
        File file = new File(path);
        FileWriter fw = new FileWriter(file);
        for (String s : content) {
            fw.write(s);
            fw.write(System.lineSeparator());
        }
        fw.close();
    }
}
