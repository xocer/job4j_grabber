package tictac;

import org.junit.Test;
import ru.job4j.tictac.ConsoleMarkX;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.ByteArrayOutputStream;

public class ConsoleMarkXTest {
    @Test
    public void print() {
        var out = new ByteArrayOutputStream();
        new ConsoleMarkX().print(out);
        assertThat(out.toString(), is("X"));
    }
}
