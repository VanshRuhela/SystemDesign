package llds.alaramalert.code.impl;

import llds.alaramalert.code.Action;

public class TerminateInstanceAction implements Action {
    private String instanceID;
    public TerminateInstanceAction(String instanceID){
        this.instanceID = instanceID;
    }


    @Override
    public void execute() {
        System.out.println("Terminating the instance");
    }
}
