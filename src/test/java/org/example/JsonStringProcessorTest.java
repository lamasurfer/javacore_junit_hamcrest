package org.example;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.net.URL;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class JsonStringProcessorTest {

    final JsonStringProcessor jsonStringProcessor = new JsonStringProcessor();

    @Test
    void testReadStringFromFile_validArgument() {

        final URL resourceFileURL = this.getClass().getResource("/test_data.json");
        final File resourceFile = new File(resourceFileURL.getFile());
        final String argument = resourceFile.getPath();

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

        final String result = jsonStringProcessor.readStringFromFile(argument);

        assertEquals(expected, result);

        assertThat(expected, is(result));
    }

    @Test
    void testReadStringFromFile_noFile_doesNotThrowException() {

        final String emptyArgument = "";
        assertDoesNotThrow(() -> jsonStringProcessor.readStringFromFile(emptyArgument));

    }

    @Test
    void testReadStringFromFile_noFile() {

        final String emptyArgument = "";
        final String result = jsonStringProcessor.readStringFromFile(emptyArgument);

        assertNull(result);

    }

    @Test
    void testReadStringFromFile_nullArgument_doesNotThrowException() {

        assertDoesNotThrow(() -> jsonStringProcessor.readStringFromFile(null));

    }

    @Test
    void testReadStringFromFile_nullArgument() {

        final String expected = jsonStringProcessor.readStringFromFile(null);
        assertNull(expected);

    }
}