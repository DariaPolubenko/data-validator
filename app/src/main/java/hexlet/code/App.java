package hexlet.code;


public class App {
    public static void main(String[] args) throws Exception {

        var v = new Validator();
        var schema = v.string().minLength(3);
                //.required();
                //minLength(3).contains("wh");
        System.out.println(schema.isValid("lala"));

    }
}
