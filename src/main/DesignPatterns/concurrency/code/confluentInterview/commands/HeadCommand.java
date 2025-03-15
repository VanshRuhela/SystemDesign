package concurrency.code.confluentInterview.commands;

import java.io.BufferedReader;
import java.io.FileReader;

public class HeadCommand {
    public static void main(String[] args) {
        String filePath = "/Users/vanshruhela/Documents/DSA/System Design/vansh-lld/src/main/DesignPatterns/concurrency/code/confluentInterview/commands/test.txt";
        int n = 3;
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;
            int cnt = 0;
            while(cnt<n && (line = bufferedReader.readLine())!=null){
                System.out.println(line);
                cnt++;
            }
        } catch (Exception e){

        }
    }

}
