package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

class JsonListProcessorTest {

    final JsonListProcessor<Employee> jsonListProcessor = new JsonListProcessor<>(Employee.class);

    @Test
    void testListToJson_validArgument() {

        final List<Employee> argumentList = new ArrayList<>();
        argumentList.add(new Employee(1, "John", "Smith", "USA", 25));
        argumentList.add(new Employee(2, "Ivan", "Petrov", "RU", 23));

        final String expected = "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Smith\",\n" +
                "    \"country\": \"USA\",\n" +
                "    \"age\": 25\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 2,\n" +
                "    \"firstName\": \"Ivan\",\n" +
                "    \"lastName\": \"Petrov\",\n" +
                "    \"country\": \"RU\",\n" +
                "    \"age\": 23\n" +
                "  }\n" +
                "]";

        final String result = jsonListProcessor.listToJson(argumentList);

        assertEquals(expected, result);

        assertThat(expected, is(result));
    }

    @Test
    void testListToJson_emptyList_doesNotThrowException() {

        final List<Employee> argumentList = new ArrayList<>();

        assertDoesNotThrow(() -> jsonListProcessor.listToJson(argumentList));

    }

    @Test
    void testListToJson_emptyList() {

        final List<Employee> argumentList = new ArrayList<>();
        final String expected = "[]";
        final String result = jsonListProcessor.listToJson(argumentList);

        assertEquals(expected, result);

        assertThat(expected, is(result));

    }

    @Test
    void testListToJson_nullArgument_doesNotThrowException() {

        assertDoesNotThrow(() -> jsonListProcessor.listToJson(null));


    }

    @Test
    void testListToJson_nullArgument() {

        final String expected = "null";
        final String result = jsonListProcessor.listToJson(null);

        assertEquals(expected, result);


        assertThat(expected, is(result));
    }


    @Test
    void testJsonToList_validArgument() {

        final String argument = "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"firstName\": \"John\",\n" +
                "    \"lastName\": \"Smith\",\n" +
                "    \"country\": \"USA\",\n" +
                "    \"age\": 25\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 2,\n" +
                "    \"firstName\": \"Ivan\",\n" +
                "    \"lastName\": \"Petrov\",\n" +
                "    \"country\": \"RU\",\n" +
                "    \"age\": 23\n" +
                "  }\n" +
                "]";

        final List<Employee> expectedList = new ArrayList<>();
        expectedList.add(new Employee(1, "John", "Smith", "USA", 25));
        expectedList.add(new Employee(2, "Ivan", "Petrov", "RU", 23));
        final List<Employee> resultList = jsonListProcessor.jsonToList(argument);

        assertEquals(expectedList, resultList);

        assertThat(expectedList, is(resultList));
    }

    @Test
    void testJsonToList_emptyArgument_doesNotThrowException() {

        final String argument = "";
        assertDoesNotThrow(() -> jsonListProcessor.jsonToList(argument));

    }

    @Test
    void testJsonToList_emptyArgument() {

        final String argument = "";
        final List<Employee> expectedList = new ArrayList<>();
        final List<Employee> resultList = jsonListProcessor.jsonToList(argument);

        assertEquals(expectedList, resultList);

        assertThat(expectedList, is(resultList));
    }

    @Test
    void testJsonToList_nullArgument_doesNotThrowException() {

        assertDoesNotThrow(() -> jsonListProcessor.jsonToList(null));

    }

    @Test
    void testJsonToList_nullArgument() {

        final List<Employee> expectedList = new ArrayList<>();
        final List<Employee> resultList = jsonListProcessor.jsonToList(null);

        assertEquals(expectedList, resultList);

        assertThat(expectedList, is(resultList));
    }
}