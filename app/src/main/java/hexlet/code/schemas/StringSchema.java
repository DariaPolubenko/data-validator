package hexlet.code.schemas;


import java.util.function.Predicate;

public final class StringSchema extends BaseSchema<String> {

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
        addCheck("minLength", value -> value.length() >= length);
        return this;
    }

    public StringSchema contains(String characters) {
        addCheck("contains", value -> value.contains(characters));
        return this;
    }
}
