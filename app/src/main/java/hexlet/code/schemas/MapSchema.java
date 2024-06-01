package hexlet.code.schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema<K, V> extends BaseSchema<Map<K, V>> {

    public MapSchema required() {
        Predicate<Map<K, V>> fn = value -> {
            if (value == null) {
                return false;
            }
            return true;
        };
        addCheck("required", fn);
        return this;
    }

    public MapSchema sizeof(Integer minSize) {
        Predicate<Map<K, V>> fn = value -> {
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

    public MapSchema shape(Map<K, BaseSchema<V>> object) {
        Predicate<Map<K, V>> fn = value ->
                object.keySet().stream().allMatch(key -> object.get(key).isValid(data.get(key)));
        addCheck("shape", fn);
        return this;
    }
}
