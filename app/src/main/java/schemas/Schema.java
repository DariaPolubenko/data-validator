package schemas;

public abstract class Schema {

    public boolean isRequired() throws Exception {
        var value = (boolean) getValue("notNull");
        return value;
    }

    public Object getValue(String fieldName) throws Exception {
        var field = this.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        var value = field.get(this);
        field.setAccessible(false);

        return value;
    }

    public abstract boolean isValid(Object object) throws Exception;

}
