package ood.srp;

import org.junit.Test;
import ru.job4j.ood.srp.Employee;
import ru.job4j.ood.srp.ItReport;
import ru.job4j.ood.srp.MemStore;
import ru.job4j.ood.srp.ReportEngine;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ItReportTest {
    @Test
    public void whenItReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        ItReport engine = new ItReport(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append("<body>")
                .append(System.lineSeparator())
                .append("<p").append(worker.getName()).append("/p>")
                .append(System.lineSeparator())
                .append("<p").append(worker.getHired()).append("/p>")
                .append(System.lineSeparator())
                .append("<p").append(worker.getFired()).append("/p>")
                .append(System.lineSeparator())
                .append("<p").append(worker.getSalary()).append("/p>")
                .append(System.lineSeparator())
                .append("</body");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}
