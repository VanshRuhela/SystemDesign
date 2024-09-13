package org.example.behavioral.chainofResponsibilty.code;
public class Demo {
    public static void main(String[] a){
        LogProcessor logger = new InfoLogProcessor(new DebugLogProcessor(new ErrorLogProcessor(null)));

        logger.log(LogProcessor.ERROR , "exception");
        logger.log(LogProcessor.DEBUG , "Debug this ");
        logger.log(LogProcessor.INFO , "Info printed");

    }
}
