package schemas;

import schemas.annotation.HasString;
import schemas.annotation.MinLength;
//import schemas.annotation.NotNull;

import java.lang.annotation.*;

//import java.lang.reflect.;
import java.util.Arrays;
import java.util.List;


public class StringSchema {
    private boolean notNull;
    private int length;

    public StringSchema() {}

    public StringSchema required() {
        notNull = true;
        return this;
    }

    //@MinLength
    public StringSchema minLength(int count) throws Exception {
        if (count < 0) {
            throw new Exception("Длина не может быть меньше 0");
        } else {
            length = count;
        }
        return this;
    }

    //@HasString
    public StringSchema contains(String text) {
        var str = text;
        return this;
    }

    public boolean isValid(String text) throws Exception {

        if (isNotValidRequired()) {
            return false;
        }

        if (!hasMinLength(text)) {
            return false;
        }
        return true;


        //принимает строку, которую надо проверить
    //вытаскиваем у объекта, какие есть у него аннотации
    //если анотация есть, то слежующий метод проверки на условия

        //новая теория: нужно обраться к полю методов с аннотациями
        // и проверить, какое значения у этих полей. Если не null - то делаем проверки на тру/фолс
        //если нулл - то не было инициализации

    }

    public boolean isNotValidRequired() throws Exception {
        var field = this.getClass().getDeclaredField("notNull");
        field.setAccessible(true);
        var value = (boolean) field.get(this);
        field.setAccessible(false);

        return value;
    }

    public boolean hasMinLength(String text) throws Exception {
        var field = this.getClass().getDeclaredField("length");
        field.setAccessible(true);
        var value = (int) field.get(this);
        field.setAccessible(false);

        if (text.length() < value) {
            return false;
        }

        return true;
    }

}
