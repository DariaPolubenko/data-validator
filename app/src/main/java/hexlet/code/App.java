package hexlet.code;


import schemas.BaseSchema;

import java.util.HashMap;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {


        var v = new Validator();
        var schema = v.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);

        System.out.println(schema.isValid(human2));

        System.out.println(v.string().required().isValid("John"));
        System.out.println(v.string().required().minLength(2).isValid(null));
    }
}
