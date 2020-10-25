package org.example;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        Class<Employee> classType = Employee.class;
        UniversalSupplier<Employee> universalSupplier = Employee::new;

        SimpleParser<Employee> simpleParser = new SimpleParser<>(universalSupplier, classType);
        JsonListProcessor<Employee> jsonListProcessor = new JsonListProcessor<>(classType);
        JsonStringProcessor jsonStringProcessor = new JsonStringProcessor();

        List<Employee> employeeListTask1 = simpleParser.parseCSV("data.csv");
        String jsonStringTask1 = jsonListProcessor.listToJson(employeeListTask1);
        jsonStringProcessor.writeStringToFile(jsonStringTask1, "data.json");

        List<Employee> employeeListTask2 = simpleParser.parseXML("data.xml");
        String jsonStringTask2 = jsonListProcessor.listToJson(employeeListTask2);
        jsonStringProcessor.writeStringToFile(jsonStringTask2, "data2.json");

        String jsonTask3 = jsonStringProcessor.readStringFromFile("new_data.json");
        List<Employee> employeeListTask3 = jsonListProcessor.jsonToList(jsonTask3);
        for (Employee employee : employeeListTask3) {
            System.out.println(employee);
        }
    }
}
