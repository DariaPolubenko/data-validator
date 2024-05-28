package hexlet.code;

import lombok.NoArgsConstructor;
//import schemas.MapSchema;
import schemas.NumberSchema;
import schemas.StringSchema;
import schemas.MapSchema;

@NoArgsConstructor
public class Validator {

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
