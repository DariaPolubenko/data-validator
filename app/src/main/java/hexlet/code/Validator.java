package hexlet.code;

import lombok.NoArgsConstructor;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import hexlet.code.schemas.MapSchema;


@NoArgsConstructor
public final class Validator {

    public StringSchema string() {
        var schema = new StringSchema();
        return schema;
    }

    public NumberSchema number() {
        var schema = new NumberSchema();
        return schema;
    }

    public MapSchema map() {
        var schema = new MapSchema();
        return schema;
    }
}
