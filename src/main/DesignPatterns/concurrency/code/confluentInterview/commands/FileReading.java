package concurrency.code.confluentInterview.commands;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Locale;

public class FileReading {
    public static void main(String[] args) {
        String path = "/Users/vanshruhela/Documents/DSA/System Design/vansh-lld/src/main/DesignPatterns/concurrency/code/confluentInterview/test.txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            String line = br.readLine();
            while(line != null){
                line = br.readLine();
                System.out.println(line);
            }
//            FileReader reader = new FileReader("/Users/vanshruhela/Documents/DSA/System Design/vansh-lld/src/main/DesignPatterns/concurrency/code/confluentInterview/test.txt");
//            System.out.println("Reading char by char");
//            int i;
//            while ((i = reader.read()) != -1) {
//                System.out.print((char) i);
//            }
//
//            reader.close();
            System.out.println("File Reader Closed");

        } catch (Exception exception) {
            System.out.println(exception);
        }
    }
}
