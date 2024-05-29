package hexlet.code;


public class App {
    public static void main(String[] args) throws Exception {

        var v = new Validator();
        var schema = v.string();

        //schema.required();
        schema.minLength(2);
        //schema.contains("lo");

        //System.out.println(schema.isValid("")); //false
        //System.out.println(schema.isValid(null)); //false


        var v1 = new Validator();
        var schema1 = v1.number();

        System.out.println(schema1.isValid(5)); //true

        //schema1.positive();
        schema1.range(1, 3);
        System.out.println(schema1.isValid(null)); //false
/*
        var v2 = new Validator();
        var schema2 = v2.map();

        var data = new HashMap<String, String>();
        data.put("key1", "value1");

        schema2.isValid(data);

         */
    }
}
