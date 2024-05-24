package schemas;

public class StringSchema {
    private boolean notNull;
    private int minLength;
    private String symbols;

    public StringSchema() {
    }


    public StringSchema required() {
        this.notNull = true;
        return this;
    }

    public StringSchema minLength(int count) throws Exception {
        if (count < 0) {
            throw new Exception("Длина не может быть меньше 0");
        } else {
            this.minLength = count;
        }
        return this;
    }

    public StringSchema contains(String characters) {
        this.symbols = characters;
        return this;
    }

    public boolean isValid(String text) throws Exception {
        if (isRequired()) {
            if (text == null || text.equals("")) {
                return false;
            }
        }
        if (hasMinLength()) {
            if (text == null) {
                return false;
            } else if (text.length() < this.minLength) {
                return false;
            }
        }
        if (hasContains()) {
            if (!text.contains(this.symbols)) {
                return false;
            }
        }
        return true;
    }

    public boolean isRequired() throws Exception {
        var value = (boolean) getValue("notNull");
        return value;
    }

    public boolean hasMinLength() throws Exception {
        var value = (int) getValue("minLength");

        if (value == 0) {
            return false;
        } else if (value < 0) {
            throw new Exception("Длина не может быть отрицательная");
        }
        return true;
    }

    public boolean hasContains() throws Exception {
        var value = getValue("symbols");
        if (value == null) {
            return false;
        }
        return true;
    }

    public Object getValue(String fieldName) throws Exception {
        var field = this.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        var value = field.get(this);
        field.setAccessible(false);

        return value;
    }
}
