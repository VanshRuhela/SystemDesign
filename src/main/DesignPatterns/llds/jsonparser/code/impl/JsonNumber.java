package llds.jsonparser.code.impl;

import llds.jsonparser.code.JsonElement;

public class JsonNumber implements JsonElement {
    private Number number;
    public JsonNumber(Number number){
        this.number = number;
    }

    @Override
    public Object getValue() {
        return number;
    }
}
