package hexlet.code;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import schemas.StringSchema;

public class StringSchemaTest {

    @Test
    public static void test1() {
        var v = new Validator();
        var schema = v.string();
        assertThat(schema).isEqualTo(null);

        var actual1 = schema.isValid("");
        assertThat(actual1).isEqualTo(true);

        var actual2 = schema.isValid(null);
        assertThat(actual2).isEqualTo(true);


        schema.required();
        var actual3 = schema.isValid(null);
        assertThat(actual3).isEqualTo(false);

        var actual4 = schema.isValid("");
        assertThat(actual4).isEqualTo(false);

        var actual5 = schema.isValid("hexlet");
        assertThat(actual5).isEqualTo(true);


        var actual6 = schema.contains("what").isValid("what does the fox say");
        assertThat(actual6).isEqualTo(true);

        var actual7 = schema.contains("whatthe").isValid("what does the fox say");
        assertThat(actual7).isEqualTo(false);
    }

}
