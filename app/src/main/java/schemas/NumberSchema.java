package schemas;

import java.util.function.Predicate;

public class NumberSchema extends BaseSchema<Integer> {

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
        return this;
    }

    public NumberSchema minLength(int length) {
        return this;
    }

    public NumberSchema contains(String characters) {
        return this;
    }


}

