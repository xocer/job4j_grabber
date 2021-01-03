package ru.job4j.ood.srp;

import java.util.function.Predicate;

public class XMLReport implements Report {
    private final Store store;

    public XMLReport(Store store) {
        this.store = store;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;");
        for (Employee employee : store.findBy(filter)) {
            text.append(System.lineSeparator())
                    .append("<name").append(employee.getName()).append("/name>")
                    .append(System.lineSeparator())
                    .append("<hired").append(employee.getHired()).append("/hired>")
                    .append(System.lineSeparator())
                    .append("<fired").append(employee.getFired()).append("/fired>")
                    .append(System.lineSeparator())
                    .append("<salary").append(employee.getSalary()).append("/salary>");
        }
        return text.toString();
    }
}
