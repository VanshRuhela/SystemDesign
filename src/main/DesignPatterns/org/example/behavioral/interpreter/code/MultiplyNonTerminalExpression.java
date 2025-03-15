package org.example.behavioral.interpreter.code;

public class MultiplyNonTerminalExpression implements AbstractExpression{

    AbstractExpression left;
    AbstractExpression right;

    public MultiplyNonTerminalExpression(AbstractExpression l , AbstractExpression r){
        this.left = l;
        this.right = r;
    }

    @Override
    public int interpret(Context ctx){
        return left.interpret(ctx) * right.interpret(ctx);
    }
}
