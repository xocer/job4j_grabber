package concurrent.completablefuture;

import org.junit.Test;
import ru.job4j.concurrent.completablefuture.RolColSum;

import java.util.concurrent.ExecutionException;

import static org.junit.Assert.assertEquals;

public class RolColSumTest {
    @Test
    public void simpleSumTest() {
        int[][] array = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum.Sums[] sums = RolColSum.sum(array);
        int result = sums[2].getColSum() + sums[1].getRowSum();
        assertEquals(result, 33);
    }

    @Test
    public void simpleAsyncSumTest() throws ExecutionException, InterruptedException {
        int[][] array = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        RolColSum.Sums[] sums = RolColSum.asyncSum(array);
        int result = sums[2].getColSum() + sums[1].getRowSum();
        assertEquals(result, 33);
    }
}
