package org.example.behavioral.template.code;

public abstract class PaymentFlow {
    public abstract void validateRequest();
    public abstract void calculateFee();
    public abstract void debitAmount();
    public abstract void creditAmount();

    //Template method which defines the order

    public final void sendMoney(){
        validateRequest();
        debitAmount();
        calculateFee();
        creditAmount();
    }
}
