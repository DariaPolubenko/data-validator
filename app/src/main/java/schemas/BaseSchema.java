package schemas;

import java.util.Map;
import java.util.LinkedHashMap;

import java.util.function.Predicate;

public class BaseSchema<T>  {
    protected Map<String, Predicate<T>> checks = new LinkedHashMap<>();
    protected boolean isNotNull;

    protected void addCheck(String name, Predicate<T> validate) {
        checks.put(name, validate);
    }

    public boolean isValid(T data) {
        if (data == null & isNotNull) {
            return false;
        }
        return checks.values().stream()
                    .allMatch(fn -> fn.test(data));
    }
}
