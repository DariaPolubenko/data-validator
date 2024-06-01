package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public final class MapSchema extends BaseSchema<Map<?, String>> {

    public MapSchema required() {
        Predicate<Map<?, String>> fn = value -> {
            if (value == null) {
                return false;
            }
            return true;
        };
        addCheck("required", fn);
        return this;
    }

    public MapSchema sizeof(Integer minSize) {
        Predicate<Map<?, String>> fn = value -> {
            if (!value.isEmpty()) {
                if (value.size() != minSize) {
                    return false;
                }
            }
            return true;
        };
        addCheck("sizeof", fn);
        isNotNull = true;
        return this;
    }

    public MapSchema shape(Map<?, BaseSchema<String>> object) {
        Predicate<Map<?, String>> fn = value ->
                object.keySet().stream().allMatch(key -> object.get(key).isValid(value.get(key)));
        addCheck("shape", fn);
        return this;
    }
}