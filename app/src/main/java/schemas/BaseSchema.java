package schemas;

import java.util.Map;
import java.util.LinkedHashMap;

import java.util.function.Predicate;

public abstract class BaseSchema<T>  {
    protected Map<String, Predicate<T>> checks = new LinkedHashMap<>();

    protected void addCheck(String name, Predicate<T> validate) {
        checks.put(name, validate);
    }

    public BaseSchema<T> required() {
        Predicate<T> fn = value -> {
            if (value instanceof String) {
                if (value.equals("")) {
                    return false;
                }
            }
            if (value == null) {
                return false;
            }
            return true;
        };
        addCheck("required", fn);
        return this;
    }

    public boolean isValid(T text) {
        return checks.values().stream()
                .allMatch(value -> value.test(text));
    }

    public abstract BaseSchema<T> minLength(int length);
    public abstract BaseSchema<T> contains(String characters);
}
