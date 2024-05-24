package hexlet.code;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import schemas.StringSchema;

@NoArgsConstructor
public class Validator {

    public StringSchema string() {
        var schema = new StringSchema();
        return schema;
    }

}
