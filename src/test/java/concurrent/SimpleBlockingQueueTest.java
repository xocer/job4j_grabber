package concurrent;

import org.junit.Test;
import ru.job4j.concurrent.SimpleBlockingQueue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SimpleBlockingQueueTest {
    @Test
    public void thenAllGood() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(10);
        Thread produce = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                queue.offer(i);
            }
        });

        Thread consume = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                queue.poll();
            }
        });

        produce.start();
        consume.start();
        produce.join();
        consume.join();
        assertThat(queue.getCount(), is(5));
    }
}