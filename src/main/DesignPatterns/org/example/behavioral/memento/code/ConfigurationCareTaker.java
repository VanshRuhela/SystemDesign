package org.example.behavioral.memento.code;

import java.util.ArrayList;
import java.util.List;

public class ConfigurationCareTaker {
    List<ConfigurationMemento> history = new ArrayList<>();

    public void addMemento(ConfigurationMemento memento){
        history.add(memento);
    }

    public ConfigurationMemento undo(){
        if(!history.isEmpty()){
            int lastMementoToIdx = history.size() - 1;
            ConfigurationMemento getLastMemento = history.get(lastMementoToIdx);
            history.remove(lastMementoToIdx);
            return getLastMemento;
        }
        return null;
    }
}
