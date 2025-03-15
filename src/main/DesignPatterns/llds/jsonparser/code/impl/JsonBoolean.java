package llds.jsonparser.code.impl;

import llds.jsonparser.code.JsonElement;

public class JsonBoolean implements JsonElement {
    private Boolean value;
    public JsonBoolean(Boolean value){
        this.value = value;
    }

    @Override
    public Boolean getValue() {
        return value;
    }
}
