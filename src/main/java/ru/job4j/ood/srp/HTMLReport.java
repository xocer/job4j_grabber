package ru.job4j.ood.srp;

import java.util.function.Predicate;

public class HTMLReport implements Report {
    private final Store store;

    public HTMLReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        for (Employee employee : store.findBy(filter)) {
            text.append(System.lineSeparator())
                    .append("<body>")
                    .append(System.lineSeparator())
                    .append("<p").append(employee.getName()).append("/p>")
                    .append(System.lineSeparator())
                    .append("<p").append(employee.getHired()).append("/p>")
                    .append(System.lineSeparator())
                    .append("<p").append(employee.getFired()).append("/p>")
                    .append(System.lineSeparator())
                    .append("<p").append(employee.getSalary()).append("/p>")
                    .append(System.lineSeparator())
                    .append("</body");
        }
        return text.toString();
    }
}
