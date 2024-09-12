package org.example.behavioral.template.code;

public class PayToFriend extends PaymentFlow{

    @Override
    public void validateRequest() {
        System.out.println("Validate for P2F");
    }

    @Override
    public void calculateFee() {
        System.out.println("0% fee charged");
    }

    @Override
    public void debitAmount() {
        System.out.println("Debit amt p2f");
    }

    @Override
    public void creditAmount() {
        System.out.println("Credit  amt p2f");
    }
}
