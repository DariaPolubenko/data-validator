package hexlet.code;


public class App {
    public static void main(String[] args) throws Exception {

        var v = new Validator();
        var schema = v.number();

        System.out.println(schema.isValid(5)); //true
    }
}
