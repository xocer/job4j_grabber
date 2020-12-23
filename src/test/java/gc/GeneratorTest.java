package gc;

import org.junit.Test;

import java.util.HashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GeneratorTest {

    @Test
    public void whenProduceNormWork() {
        Generator generator = new Generator();
        String result = generator.produce("I am a ${name}, Who are ${subject}? ", new HashMap<String, String>());
        assertThat(result, is(new String("ожидаемая строка")));
    }

    @Test(expected = Exception.class)
    public void whenProduceKeysHaveProblem() {
        Generator generator = new Generator();
        String result = generator.produce("I am a ${name}, Who are ${subject}? ", new HashMap<String, String>());
    }
}
