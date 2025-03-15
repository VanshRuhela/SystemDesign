package concurrency.code.confluentInterview.commands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TailCommand {

    public static void main(String[] args) throws IOException {
        String filePath = "/Users/vanshruhela/Documents/DSA/System Design/vansh-lld/src/main/DesignPatterns/concurrency/code/confluentInterview/test.txt";
        int n = 5;
        RandomAccessFile file = null;
        try{
             file = new RandomAccessFile(filePath, "r");
            long fileLength = file.length();
            long pointer = fileLength - 1;
            System.out.println("Pointer at : " + pointer);
            int lineCount = 0;
//           now the pointer is pointing to the end
            file.seek(pointer);
//            System.out.println("read at pointer" + file.readByte());
            while(pointer >= 0){
                int readByte = file.read();
                System.out.println("read at pointer " + (char) readByte);
                if(readByte == '\n'){
                    lineCount ++;
                    if(lineCount == n){
                        break;
                    }
                }
                pointer--;
                file.seek(pointer);
            }

            // print the lines
            if(pointer < 0) {
                file.seek(0);
            }
//            else {
//                System.out.println("incomplete line" + file.readLine());
//            }

            String line;
            while((line = file.readLine()) != null){
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (file != null)
                    file.close();
        }
    }
}
