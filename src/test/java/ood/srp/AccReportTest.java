package ood.srp;

import org.junit.Test;
import ru.job4j.ood.srp.*;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class AccReportTest {
    @Test
    public void whenAccReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 7000);
        store.add(worker);
        SalaryConverter salaryConverter = new SalaryConverter();
        AccReport engine = new AccReport(store, salaryConverter);
        ReportEngine reportEngine = new ReportEngine(engine);
        StringBuilder expect = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getHired()).append(";")
                .append(worker.getFired()).append(";")
                .append(salaryConverter.convert(worker.getSalary()));
        assertThat(reportEngine.generate(em -> true), is(expect.toString()));
    }
}