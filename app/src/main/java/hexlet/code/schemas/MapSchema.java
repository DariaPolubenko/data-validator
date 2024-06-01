package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map<?, ?>> {

    public MapSchema required() {
        addCheck("required", value -> {
            if (value == null) {
                return false;
            }
            return true;
        });
        return this;
    }

    public MapSchema sizeof(Integer minSize) {
        addCheck("sizeof", value -> {
            if (value != null) {
                if (value.size() != minSize) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }

    public MapSchema shape(Map<?, BaseSchema<String>> object) {
        addCheck("shape", value ->
                object.keySet().stream().allMatch(key -> {
                    var schema = object.get(key);
                    var strValue = (String) value.get(key);
                    return schema.isValid(strValue);
                }));
        return this;
    }
}
