package schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<String, String>> {

    public MapSchema required() {
        Predicate<Map<String, String>> fn = value -> {
            if (value == null) {
                return false;
            }
            return true;
        };
        addCheck("required", fn);
        return this;
    }

    public MapSchema sizeof(Integer minSize) {
        Predicate<Map<String, String>> fn = value -> {
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
}
