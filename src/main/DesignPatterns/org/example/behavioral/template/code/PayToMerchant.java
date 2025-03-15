package org.example.behavioral.template.code;

public class PayToMerchant extends PaymentFlow{
    @Override
    public void validateRequest() {
        System.out.println("Validate P2M");
    }

    @Override
    public void calculateFee() {
        System.out.println("2% fee charged");
    }

    @Override
    public void debitAmount() {
        System.out.println("debit amt p2m");
    }

    @Override
    public void creditAmount() {
        System.out.println("credit amt p2m");
    }
}
