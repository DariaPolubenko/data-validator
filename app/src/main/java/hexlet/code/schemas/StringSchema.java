package hexlet.code.schemas;


import java.util.function.Predicate;

public class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        Predicate<String> fn = value -> {
            if (value == null || value.equals("")) {
                return false;
            }
            return true;
        };
        addCheck("required", fn);
        return this;
    }

    public StringSchema minLength(int length) {
        addCheck("minLength", value -> value.length() >= length && length >= 0);
        isNotNull = true;
        return this;
    }

    public StringSchema contains(String characters) {
        addCheck("contains", value -> value.contains(characters));
        isNotNull = true;
        return this;
    }
}
