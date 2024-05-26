package schemas;

public class StringSchema extends Schema {
    private boolean notNull;
    private int minLength;
    private String symbols;


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

    public boolean isValid(Object object) throws Exception {
        var text = (String) object;

        if (isRequired()) {
            if (text == null || text.equals("")) {
                return false;
            }
        }
        if (hasMinLength()) {
            if (text == null || text.length() < this.minLength) {
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

}
