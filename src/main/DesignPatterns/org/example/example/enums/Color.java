package org.example.example.enums;

public enum Color {
    RED, GREEN, BLUE, BLACK;

    private Color(){
        System.out.println("Constructor called for : " +this.toString());
    }

    public static void main(String[] args) {
        for(Color c: Color.values()){
            System.out.println(c + " at idx: " + c.ordinal());
        }
    }
}
