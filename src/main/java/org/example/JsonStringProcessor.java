package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.io.*;

public class JsonStringProcessor {

    public void writeStringToFile(String jsonString, String outputFile) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
            bufferedWriter.write(jsonString);
            bufferedWriter.flush();
        } catch (IOException | NullPointerException e) {
            System.out.println("Exception at writeString(...)!");
        }
    }

    public String readStringFromFile(String inputFile) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String result = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(inputFile))) {
            JsonElement json = gson.fromJson(bufferedReader, JsonElement.class);
            result = gson.toJson(json);
        } catch (IOException | NullPointerException e) {
            System.out.println("Exception at readString(...)!");
        }
        return result;
    }
}
