package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JsonListProcessor<T> {

    private final Class<T> classType;

    public JsonListProcessor(Class<T> classType) {
        this.classType = classType;
    }

    public String listToJson(List<T> list) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Type listType = new TypeToken<List<T>>() {
        }.getType();
        return gson.toJson(list, listType);
    }

    public List<T> jsonToList(String jsonString) {
        List<T> resultList = new ArrayList<>();
        try {
            JSONParser jsonParser = new JSONParser();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            JSONArray jsonArray = (JSONArray) jsonParser.parse(jsonString);
            for (Object jsonArrayObject : jsonArray) {
                T object = gson.fromJson(String.valueOf(jsonArrayObject), classType);
                resultList.add(object);
            }
        } catch (ParseException | NullPointerException e) {
            System.out.println("Exception at jsonToList(...)!");
        }
        return resultList;
    }
}
