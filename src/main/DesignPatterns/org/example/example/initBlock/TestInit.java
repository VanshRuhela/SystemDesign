package org.example.example.initBlock;

public class TestInit {
    {
        System.out.println("init block called");
    }

    TestInit(){
        System.out.println("Def cons");
    }
}
