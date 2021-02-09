package cach;

import org.junit.Test;
import ru.job4j.cach.Base;
import ru.job4j.cach.NonBlockingCash;
import ru.job4j.cach.OptimisticException;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;


public class NonBlockingCashTest {
    @Test
    public void whenOk() throws InterruptedException {
        NonBlockingCash cash = new NonBlockingCash();
        Base one = new Base(1, 0);
        one.setName("Jack");
        cash.add(one);

        Base two = new Base(1, 0);
        two.setName("Bob");
        cash.update(two);

        Base three = new Base(1, 1);
        three.setName("Jon");
        cash.update(three);

        assertThat(cash.find(1).getVersion(), is(2));
    }

    @Test
    public void test() throws InterruptedException {
        AtomicReference<Exception> exceptionAtomicReference = new AtomicReference<>();
        NonBlockingCash cash = new NonBlockingCash();

        Thread thread = new Thread(() -> {
            Base one = new Base(1, 0);
            cash.add(one);
            try {
                Base two = new Base(1, 1);
                cash.update(two);
            } catch (OptimisticException e) {
                exceptionAtomicReference.set(e);
            }
        });
        thread.start();
        thread.join();
        assertThat(exceptionAtomicReference.get().getMessage(), is("Ошибочка"));

    }
}
