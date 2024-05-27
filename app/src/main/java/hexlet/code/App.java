package hexlet.code;


import java.util.HashMap;

public class App {
    public static void main(String[] args) throws Exception {

        var v = new Validator();
        var schema = v.number();

        System.out.println(schema.isValid(5)); //true


        var v1 = new Validator();
        var schema1 = v1.map();

        var data = new HashMap<String, String>();
        data.put("key1", "value1");

        schema1.isValid(data);
    }
}
