package cn.whu.wy.learnjava.base.shell;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Consumer;


/**
 * 参考资料：
 * 1. https://mkyong.com/java/how-to-execute-shell-command-from-java/
 * 2. https://www.baeldung.com/run-shell-command-in-java
 * 3. https://itlanyan.com/grep-invert-match-multiple-strings/
 */
public class ShellExecutor {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        String[] cmd = new String[]{"sshpass", "-p", "1", "ssh", "-o", "StrictHostKeyChecking=no", "wy@127.0.0.1",
                "egrep", "-v", "\"^import|^package\"", "/home/wy/RuntimeDemo.java"};
        if (args[0].equals("1")) {
            t1(cmd);
            t1(cmd);
        }
        if (args[0].equals("2")) {
            t2(cmd);
            t2(cmd);
        }
        if (args[0].equals("3")) {
            t3(cmd);
            t3(cmd);
        }

        if (args[0].equals("0")) {
            t1(cmd);
            t2(cmd);
            t3(cmd);
        }

    }

    static void t1(String[] cmd) {
        ProcessBuilder processBuilder = new ProcessBuilder();

        // -- Linux --

        // Run a shell command
        processBuilder.command(cmd);

        // Run a shell script
        //processBuilder.command("path/to/hello.sh");

        // -- Windows --

        // Run a command
        //processBuilder.command("cmd.exe", "/c", "dir C:\\Users\\mkyong");

        // Run a bat file
        //processBuilder.command("C:\\Users\\mkyong\\hello.bat");

        try {

            Process process = processBuilder.start();

            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("t1 success!");
                System.out.println(output);
//                System.exit(0);
            } else {
                System.out.println("t1 fail, code=" + exitVal);
                System.out.println(output);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void t2(String[] cmd) {
        try {
            Process process = Runtime.getRuntime().exec(cmd);

            StringBuilder output = new StringBuilder();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitVal = process.waitFor();
            if (exitVal == 0) {
                System.out.println("t2 success!");
                System.out.println(output);
//                System.exit(0);
            } else {
                System.out.println("t2 fail, code=" + exitVal);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void t3(String[] cmd) throws IOException, InterruptedException, ExecutionException {
        Process process = Runtime.getRuntime().exec(cmd);
        StreamGobbler streamGobbler =
                new StreamGobbler(process.getInputStream(), System.out::println);
        Future<?> future = Executors.newSingleThreadExecutor().submit(streamGobbler);

        int exitCode = process.waitFor();
        if (exitCode == 0) {
            System.out.println("t3 success");
        } else {
            System.out.println("t3 fail, code=" + exitCode);
        }

        future.get();// waits for streamGobbler to finish
    }

    static class StreamGobbler implements Runnable {
        private InputStream inputStream;
        private Consumer<String> consumer;

        public StreamGobbler(InputStream inputStream, Consumer<String> consumer) {
            this.inputStream = inputStream;
            this.consumer = consumer;
        }

        @Override
        public void run() {
            new BufferedReader(new InputStreamReader(inputStream)).lines()
                    .forEach(consumer);
        }
    }
}