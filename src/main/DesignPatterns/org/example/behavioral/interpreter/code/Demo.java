package org.example.behavioral.interpreter.code;

public class Demo {
    public static void main(String[]a){
//        initialize context
        Context ctx = new Context();
        ctx.put("a", 2);
        ctx.put("b", 3);

        // a*b
        AbstractExpression expression = new MultiplyNonTerminalExpression(
                new NumberTerminalExpression("a"), new NumberTerminalExpression("b"));

        System.out.println(expression.interpret(ctx));
    }
}
