package llds.jsonparser.code.impl;

import llds.jsonparser.code.JsonElement;

import java.util.HashMap;
import java.util.Map;

public class JsonObject implements JsonElement {
    private Map<String, JsonElement> properties;
    public JsonObject(Map<String, JsonElement> properties){
        this.properties = properties;
    }

    @Override
    public Object getValue() {
        Map<String, Object> res = new HashMap<>();
        properties.forEach((k,v) -> res.put(k, v.getValue()));
        return res;
    }
}
