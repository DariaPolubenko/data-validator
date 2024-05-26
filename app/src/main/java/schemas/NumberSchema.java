package schemas;

public class NumberSchema extends Schema {
    private boolean notNull;
    private boolean notNegative;
    private Integer start;
    private Integer end;

    public NumberSchema required() {
        this.notNull = true;
        return this;
    }

    public NumberSchema positive() {
        this.notNegative = true;
        return this;
    }

    public NumberSchema range(int start, int end) {
        this.start = start;
        this.end = end;
        return this;
    }

    public boolean isValid(Object object) throws Exception {
        var number = (Integer) object;

        if (isRequired()) {
            if (number == null) {
                return false;
            }
        }
        if (isPositive()) {
            if (number != null) {
                if (number <= 0) {
                    return false;
                }
            }
        }
        if (hasRange()) {
            if (number != null) {
                if (number < start || number > end) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isPositive() throws Exception  {
        var value = (boolean) getValue("notNegative");
        return value;
    }

    public boolean hasRange() throws Exception {
        var value = (Integer) getValue("start");
        if (value == null) {
            return false;
        }
        return true;
    }
}
