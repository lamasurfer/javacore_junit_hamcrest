package org.example;

import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SimpleParser<T> {



    private final UniversalSupplier<T> universalSupplier;
    private final Class<T> classType;

    public SimpleParser(UniversalSupplier<T> universalSupplier, Class<T> classType) {
        this.universalSupplier = universalSupplier;
        this.classType = classType;
    }

    public List<T> parseCSV(String fileName) {

        String[] columnMapping = getClassFields();
        List<T> resultList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName));
             CSVReader csvReader = new CSVReader(br)) {

            ColumnPositionMappingStrategy<T> strategy = new ColumnPositionMappingStrategy<>();
            strategy.setType(classType);
            strategy.setColumnMapping(columnMapping);

            CsvToBeanBuilder<T> csvToBeanBuilder = new CsvToBeanBuilder<>(csvReader);
            csvToBeanBuilder.withMappingStrategy(strategy);
            CsvToBean<T> csvToBean = csvToBeanBuilder.build();

            resultList = csvToBean.parse();

        } catch (IOException | NullPointerException e) {
            System.out.println("Exception at parseCSV(...)!");
        }
        return resultList;
    }

    public List<T> parseXML(String fileName) {

        List<T> resultList = new ArrayList<>();
        String[] fields = getClassFields();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new File(fileName));
            document.getDocumentElement().normalize();
            NodeList nodeList = document.getDocumentElement().getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;

                    String[] constructor = new String[fields.length];
                    for (int j = 0; j < fields.length; j++) {
                        String temp = element.getElementsByTagName(fields[j]).item(0).getTextContent();
                        constructor[j] = temp;
                    }
                    T object = universalSupplier.supply(constructor);
                    resultList.add(object);
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException | NullPointerException e) {
            System.out.println("Exception at parseXML(...)!");
        }
        return resultList;
    }

    public String[] getClassFields() {
        Field[] fields = classType.getFields();
        String[] classFields = new String[fields.length];
        for (int i = 0; i < fields.length; i++) {
            classFields[i] = fields[i].getName();
//            если тэги маленькими буквами
//            classFields[i] = fields[i].getName().toLowerCase();
        }
        return classFields;
    }
}
