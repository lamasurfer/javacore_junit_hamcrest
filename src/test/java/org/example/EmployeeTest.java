package org.example;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeTest {

    @Test
    void testEmployee_validArgument() {
        String[] argument = new String[]{
                "1",
                "John",
                "Smith",
                "USA",
                "25",
        };

        Employee expected = new Employee(1, "John", "Smith", "USA", 25);
        Employee result = new Employee(argument);

        assertEquals(expected, result);

        assertThat(expected, is(result));

    }

    @Test
    void testEmployee_InvalidArgument_throwsException() {
        String[] argument = new String[]{
                "aaaa",
                "John",
                "Smith",
                "USA",
                "aaaa",
        };

        assertThrows(NumberFormatException.class, () -> new Employee(argument));

    }

    @Test
    void testEmployee_nullArgument_throwsException() {

        assertThrows(NullPointerException.class, () -> new Employee(null));

    }
}
