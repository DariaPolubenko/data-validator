package hexlet.code;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import hexlet.code.schemas.BaseSchema;

import static org.assertj.core.api.Assertions.assertThat;

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

        //var actual4 = schema.minLength(3).isValid(null);
        //assertThat(actual4).isEqualTo(false);
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

        var actual8 = schema.isValid("what does the fox say");
        assertThat(actual8).isEqualTo(false);


        var schema1 = v.string();
        var actual9 = schema1.minLength(10).minLength(4).isValid("Hexlet");
        assertThat(actual9).isEqualTo(true);

        var actual10 = schema1.isValid("hex");
        assertThat(actual10).isEqualTo(false);

        //var actual11 = schema1.isValid(null);
        //assertThat(actual11).isEqualTo(false);
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

        schema.range(-10, -5);
        var actual12 = schema.isValid(-6);
        assertThat(actual12).isEqualTo(false);

        var actual13 = schema.isValid(null);
        assertThat(actual13).isEqualTo(false);
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


        var schema1 = v.map().sizeof(1);
        var actual7 = schema1.isValid(null);
        assertThat(actual7).isEqualTo(false);
    }

    @Test
    public void mapShapeTest() {
        var v = new Validator();

        var schema = v.map();

        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", v.string().required());
        schemas.put("lastName", v.string().required().minLength(2));

        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");

        var actual1 = schema.isValid(human1);
        assertThat(actual1).isEqualTo(true);


        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);

        var actual2 = schema.isValid(human2);
        assertThat(actual2).isEqualTo(false);


        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");

        var actual3 = schema.isValid(human3);
        assertThat(actual3).isEqualTo(false);
    }
}

