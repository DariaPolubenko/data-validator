package hexlet.code.schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        Predicate<Integer> fn = value -> {
            if (value == null) {
                return false;
            }
            return true;
        };
        addCheck("required", fn);
        return this;
    }

    public NumberSchema positive() {
        Predicate<Integer> fn = value -> {
            if (value != null) {
                if (value <= 0) {
                    return false;
                }
            }
            return true;
        };
        addCheck("positive", fn);
        return this;
    }

    public NumberSchema range(Integer start, Integer end) {
        Predicate<Integer> fn = value -> {
            if (value != null) {
                if (value < start || value > end) {
                    return false;
                }
            }
            return true;
        };
        addCheck("range", fn);
        isNotNull = true;
        return this;
    }
}
