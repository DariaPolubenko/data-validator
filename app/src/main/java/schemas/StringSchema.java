package schemas;


public class StringSchema extends BaseSchema<String> {

    public StringSchema minLength(int length) {
        addCheck("minLength", value -> value.length() >= length);
        return this;
    }

    public StringSchema contains(String characters) {
        addCheck("contains", value -> value.contains(characters));
        return this;
    }

}
