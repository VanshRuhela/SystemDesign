package concurrency.code.confluentInterview.commands;

import java.io.*;
import java.util.LinkedList;

public class TailCommandUsingStringBuilder {

    private static void readUsingBuferredReader(String filePath, int n){

        LinkedList<String> lastNLines = new LinkedList<>();
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String line;
            while((line = bufferedReader.readLine())!=null){
                if(lastNLines.size()==n){
                    lastNLines.removeFirst();
                }
                lastNLines.addLast(line);
            }

            for(var fullLine : lastNLines){
                System.out.println(fullLine);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void readUsingRandomFileAcess(String filePath , int n){
        LinkedList<String> lastNLines = new LinkedList<>();
        try{
            RandomAccessFile file = new RandomAccessFile(filePath, "r");
            int fileLength = (int) file.length();
            int pointer = fileLength -1;
            int lineCount = 0;
            StringBuilder builder = new StringBuilder();

            while(pointer >=0 && lineCount < n){
                file.seek(pointer);
                char c = (char) file.readByte();
                if(c == '\n'){
                    lastNLines.addFirst(builder.reverse().toString());
                    lineCount++;
                    builder.setLength(0); // reset the builder
                }
                else {
                    builder.append(c);
                }
                pointer --;
            }

            if(!builder.isEmpty()){
                lastNLines.addFirst(builder.reverse().toString());
            }

            // print
            for(var lines : lastNLines){
                System.out.println(lines);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        String filePath = "/Users/vanshruhela/Documents/DSA/System Design/vansh-lld/src/main/DesignPatterns/concurrency/code/confluentInterview/commands/test.txt";
        int n = 3;

        readUsingBuferredReader(filePath, n);
        readUsingRandomFileAcess(filePath,n);
    }
}
