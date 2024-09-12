package org.example.behavioral.template.code;

public class Demo {
    public static void main(String [] args){
        PaymentFlow pay = new PayToFriend();
        pay.sendMoney();
        System.out.println("\n\n");
        pay = new PayToMerchant();
        pay.sendMoney();
    }
}
