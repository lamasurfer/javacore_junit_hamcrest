package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class SimpleParserTest {


    final Class<Employee> classType = Employee.class;
    final UniversalSupplier<Employee> universalSupplier = Employee::new;
    final SimpleParser<Employee> simpleParser = new SimpleParser<>(universalSupplier, classType);

    @Test
    void testParseCSV_validArgument() {

        final URL resourceFileURL = this.getClass().getResource("/test_data.csv");
        final File resourceFile = new File(resourceFileURL.getFile());
        final String argument = resourceFile.getPath();

        final List<Employee> expectedList = new ArrayList<>();
        expectedList.add(new Employee(1, "John", "Smith", "USA", 25));
        expectedList.add(new Employee(2, "Ivan", "Petrov", "RU", 23));

        final List<Employee> resultList = simpleParser.parseCSV(argument);

        assertEquals(expectedList, resultList);
        assertThat(expectedList, is(resultList));
    }

    @Test
    void testParseCSV_emptyArgument_noException() {

        final String argument = "";
        final List<Employee> expectedList = new ArrayList<>();
        final List<Employee> resultList = simpleParser.parseCSV(argument);

        assertEquals(expectedList, resultList);
        assertDoesNotThrow(() -> simpleParser.parseCSV(argument));

        assertThat(expectedList, is(resultList));
    }

    @Test
    void testParseCSV_nullArgument_noException() {

        final List<Employee> expectedList = new ArrayList<>();
        final List<Employee> resultList = simpleParser.parseCSV(null);

        assertEquals(expectedList, resultList);
        assertDoesNotThrow(() -> simpleParser.parseCSV(null));

        assertThat(expectedList, is(resultList));
    }

    @Test
    void testParseXML_validArgument() {

        final URL resourceFile = this.getClass().getResource("/test_data.xml");
        final File resourceFileName = new File(resourceFile.getFile());
        final String argument = resourceFileName.getPath();

        final List<Employee> expectedList = new ArrayList<>();
        expectedList.add(new Employee(1, "John", "Smith", "USA", 25));
        expectedList.add(new Employee(2, "Ivan", "Petrov", "RU", 23));

        final List<Employee> resultList = simpleParser.parseXML(argument);

        assertEquals(expectedList, resultList);
        assertThat(expectedList, is(resultList));
    }

    @Test
    void testParseXML_emptyArgument_noException() {

        final String argument = "";

        final List<Employee> expectedList = new ArrayList<>();
        final List<Employee> resultList = simpleParser.parseXML(argument);

        assertEquals(expectedList, resultList);
        assertDoesNotThrow(() -> simpleParser.parseXML(""));

        assertThat(expectedList, is(resultList));
    }

    @Test
    void testParseXML_nullArgument_noException() {

        final List<Employee> expectedList = new ArrayList<>();
        final List<Employee> resultList = simpleParser.parseXML(null);

        assertEquals(expectedList, resultList);
        assertDoesNotThrow(() -> simpleParser.parseXML(null));

        assertThat(expectedList, is(resultList));
    }

    @Test
    void testGetClassFields() {

        String[] expected = new String[]{
                "id",
                "firstName",
                "lastName",
                "country",
                "age",
        };

        String[] result = simpleParser.getClassFields();

        assertArrayEquals(expected, result);
        assertThat(expected, is(result));
    }
}