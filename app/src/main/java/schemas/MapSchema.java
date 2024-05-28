package schemas;

import java.util.Map;
import java.util.function.Predicate;

public class MapSchema extends BaseSchema<Map<String, String>> {

    public MapSchema sizeof(Integer minSize) {
        Predicate<Map<String, String>> fn = value -> {
            if (!value.isEmpty()) {
                if (value.size() < minSize) {
                    return false;
                }
            }
            return true;
        };
        addCheck("sizeof", fn);
        return this;
    }

    public MapSchema minLength(int length) {
        return this;
    }

    public MapSchema contains(String characters) {
        return this;
    }
}

