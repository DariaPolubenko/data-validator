package schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema<M, L> extends BaseSchema<Map<M, L>> {

    public MapSchema required() {
        Predicate<Map<M, L>> fn = value -> {
            if (value == null) {
                return false;
            }
            return true;
        };
        addCheck("required", fn);
        return this;
    }

    public MapSchema sizeof(Integer minSize) {
        Predicate<Map<M, L>> fn = value -> {
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

    public MapSchema shape(Map<M, BaseSchema<L>> object) {
        Predicate<Map<M, L>> fn = value ->
                object.keySet().stream().allMatch(key -> object.get(key).isValid(data.get(key)));
        addCheck("shape", fn);
        return this;
    }
}
