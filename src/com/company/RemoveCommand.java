package com.company;

import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.*;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

class RemoveCommand extends Command//команда remove
{
    public RemoveCommand(){
        name = "remove";
    }

    public void action() {
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("To-Do List.xml");

            System.out.println("Введите id: ");
            String id = new Scanner(System.in).nextLine();

            NodeList tasks = document.getElementsByTagName("Task");
            for(int i = 0; i < tasks.getLength(); i++){
                if(tasks.item(i).getAttributes().getNamedItem("id").getNodeValue().equals(id)) {
                    tasks.item(i).getParentNode().removeChild(tasks.item(i));
                    NewTask.writeDocument(document, "To-Do List.xml");
                    System.out.println("Задание id = " + id + " успешно удалено");
                    return;
                }
            }

            System.out.println("Задание с id = " + id + " не найдено!");

        } catch (IOException | SAXException | ParserConfigurationException e) {
            e.printStackTrace();
        }
    }
}

