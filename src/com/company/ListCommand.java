package com.company;

import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;


class ListCommand extends Command//команда list
{
    public ListCommand(){
        name = "list";
    }

    public void action(){
        System.out.println("Список всех задач:");
        ShowAllTasks();
    }

    private void ShowAllTasks(){
        try {
            DocumentBuilder documentBuilder;
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("To-Do List.xml");

            NodeList nodeList = document.getElementsByTagName("Task");

            for (int i = 0; i < nodeList.getLength(); i++) {
                if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) nodeList.item(i);
                    if (el.getNodeName().contains("Task")) {
                        writeList(el);
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void tasksSelectionWrite(TaskStatus status){
        try {
            DocumentBuilder documentBuilder;
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("To-Do List.xml");

            NodeList nodeList = document.getElementsByTagName("Task");

            for (int i = 0; i < nodeList.getLength(); i++) {
                if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) nodeList.item(i);

                    if (el.getElementsByTagName("Status").item(0).getTextContent().equals(status.toString()))
                        ListCommand.writeList(el);
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeList(Element element) {
        System.out.println("Задача\nid =" + element.getAttributes().getNamedItem("id").getNodeValue());
        System.out.println("Описание: " + element.getElementsByTagName("Description").item(0).getTextContent());
        System.out.println("Приоритет: " + element.getElementsByTagName("Priority").item(0).getTextContent());
        System.out.println("Дедлайн: " + element.getElementsByTagName("Deadline").item(0).getTextContent());
        System.out.println("Статус: " + element.getElementsByTagName("Status").item(0).getTextContent());
        System.out.println("Дата завершения: " + element.getElementsByTagName("Complete").item(0).getTextContent());

        System.out.println();
    }
}

