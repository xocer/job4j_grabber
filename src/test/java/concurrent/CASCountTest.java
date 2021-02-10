package concurrent;

import org.junit.Test;
import ru.job4j.concurrent.CASCount;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CASCountTest {
    @Test
    public void whenNormalWork() throws InterruptedException {
        CASCount count = new CASCount();
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                count.increment();
            }
        });
        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                count.increment();
            }
        });
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        assertThat(count.get(), is(20));
    }
}
