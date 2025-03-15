package llds.jsonparser.code.impl;

import llds.jsonparser.code.JsonElement;

public class JsonString implements JsonElement {
    private final String value;
    public JsonString(String value){
        this.value = value;
    }
    public Object getValue() {
        return value;
    }
}
