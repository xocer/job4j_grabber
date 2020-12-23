package ood.srp;

import org.junit.Test;
import ru.job4j.ood.srp.AccReport;
import ru.job4j.ood.srp.Employee;
import ru.job4j.ood.srp.HrReport;
import ru.job4j.ood.srp.MemStore;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HrReportTest {
    @Test
    public void whenHrReportGenerated() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        Employee workerAcc = new Employee("Inna", now, now, 200);
        Employee workerIt = new Employee("Max", now, now, 150);
        store.add(worker);
        store.add(workerAcc);
        store.add(workerIt);

        HrReport engine = new HrReport(store);
        StringBuilder expect = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(workerAcc.getName()).append(";")
                .append(workerAcc.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(workerIt.getName()).append(";")
                .append(workerIt.getSalary()).append(";")
                .append(System.lineSeparator())
                .append(worker.getName()).append(";")
                .append(worker.getSalary()).append(";");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}
