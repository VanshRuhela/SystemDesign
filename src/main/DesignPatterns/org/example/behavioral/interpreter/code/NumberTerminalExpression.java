package org.example.behavioral.interpreter.code;

public class NumberTerminalExpression implements AbstractExpression{
    String stringVal;

    NumberTerminalExpression(String val){
        this.stringVal = val;
    }

    @Override
    public int interpret(Context ctx){
        return ctx.get(stringVal);
    }
}
