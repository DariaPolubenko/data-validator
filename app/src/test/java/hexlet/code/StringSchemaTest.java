package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class StringSchemaTest {

    @Test
    public void test1() throws Exception {
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
    public void test2() throws Exception {

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
    }

}
