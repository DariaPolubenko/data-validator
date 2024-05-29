package schemas;

import java.util.Map;
import java.util.LinkedHashMap;

import java.util.function.Predicate;

public class BaseSchema<T>  {
    protected Map<String, Predicate<T>> checks = new LinkedHashMap<>();
    protected boolean isNotNull;
    protected T data;

    protected void addCheck(String name, Predicate<T> validate) {
        checks.put(name, validate);
    }

    public boolean isValid(T object) {
        if (object == null & isNotNull) {
            return false;
        }
        data = object;

        return checks.values().stream()
                    .allMatch(fn -> fn.test(object));
    }
}
