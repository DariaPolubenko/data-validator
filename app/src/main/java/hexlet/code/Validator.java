package hexlet.code;

import lombok.NoArgsConstructor;
import schemas.NumberSchema;
import schemas.StringSchema;

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

}
