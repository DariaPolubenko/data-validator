package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.HashMap;

public class SchemaTest {
    @Test
    public void stringSchemaTest1() {
        var v = new Validator();
        var schema = v.string();

        var actual1 = schema.isValid("");
        assertThat(actual1).isEqualTo(true);

        var actual2 = schema.isValid(null);
        assertThat(actual2).isEqualTo(true);

        var actual3 = schema.minLength(3).isValid("");
        assertThat(actual3).isEqualTo(false);
    }

    @Test
    public void stringSchemaTest2() {

        var v = new Validator();
        var schema = v.string().required();

        var actual1 = schema.isValid(null);
        assertThat(actual1).isEqualTo(false);

        var actual2 = schema.isValid("");
        assertThat(actual2).isEqualTo(false);

        var actual3 = schema.isValid("hexlet");
        assertThat(actual3).isEqualTo(true);


        schema.minLength(4);
        var actual4 = schema.isValid("hex");
        assertThat(actual4).isEqualTo(false);

        var actual5 = schema.isValid("hexlet");
        assertThat(actual5).isEqualTo(true);


        var actual6 = schema.contains("what").isValid("what does the fox say");
        assertThat(actual6).isEqualTo(true);

        var actual7 = schema.contains("whatthe").isValid("what does the fox say");
        assertThat(actual7).isEqualTo(false);


        var schema1 = v.string();
        var actual8 = schema1.minLength(10).minLength(4).isValid("Hexlet");
        assertThat(actual8).isEqualTo(true);
    }

    @Test
    public void numberSchemaTest() {

        var v = new Validator();
        var schema = v.number();


        var actual1 = schema.isValid(5);
        assertThat(actual1).isEqualTo(true);

        var actual2 = schema.isValid(null);
        assertThat(actual2).isEqualTo(true);


        schema.positive();
        var actual3 = schema.isValid(null);
        assertThat(actual3).isEqualTo(true);


        schema.required();
        var actual4 = schema.isValid(null);
        assertThat(actual4).isEqualTo(false);

        var actual5 = schema.isValid(10);
        assertThat(actual5).isEqualTo(true);

        var actual6 = schema.isValid(-10);
        assertThat(actual6).isEqualTo(false);

        var actual7 = schema.isValid(0);
        assertThat(actual7).isEqualTo(false);


        schema.range(5, 10);
        var actual8 = schema.isValid(5);
        assertThat(actual8).isEqualTo(true);

        var actual9 = schema.isValid(10);
        assertThat(actual9).isEqualTo(true);

        var actual10 = schema.isValid(4);
        assertThat(actual10).isEqualTo(false);

        var actual11 = schema.isValid(11);
        assertThat(actual11).isEqualTo(false);
    }


    @Test
    public void mapSchemaTest() {
        var v = new Validator();
        var schema = v.map();

        var actual1 = schema.isValid(null);
        assertThat(actual1).isEqualTo(true);


        schema.required();
        var actual2 = schema.isValid(null);
        assertThat(actual2).isEqualTo(false);

        var actual3 = schema.isValid(new HashMap<>());
        assertThat(actual3).isEqualTo(true);


        var data = new HashMap<String, String>();
        data.put("key1", "value1");
        var actual4 = schema.isValid(data);
        assertThat(actual4).isEqualTo(true);


        schema.sizeof(2);
        var actual5 = schema.isValid(data);
        assertThat(actual5).isEqualTo(false);


        data.put("key2", "value2");
        var actual6 = schema.isValid(data);
        assertThat(actual6).isEqualTo(true);
    }
}

