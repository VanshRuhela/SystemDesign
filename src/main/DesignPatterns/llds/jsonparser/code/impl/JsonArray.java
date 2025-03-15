package llds.jsonparser.code.impl;

import llds.jsonparser.code.JsonElement;

import java.util.List;
import java.util.stream.Collectors;

public class JsonArray implements JsonElement {
    private List<JsonElement> elements;

    public JsonArray(List<JsonElement> elements){
        this.elements = elements;
    }

    @Override
    public Object getValue() {
        return elements.stream().map(JsonElement::getValue).collect(Collectors.toList());
    }
}
