package com.company;

import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.*;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

class EditCommand extends Command//команда edit
{
    public EditCommand() {
        name = "edit";
    }

    public void action() {
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("To-Do List.xml");

            NodeList tasks = document.getElementsByTagName("Task");
            NodeList descriptions = document.getElementsByTagName("Description");
            NodeList priority = document.getElementsByTagName("Priority");
            NodeList deadline = document.getElementsByTagName("Deadline");
            NodeList status = document.getElementsByTagName("Status");
            NodeList complete = document.getElementsByTagName("Complete");

            Scanner scanner = new Scanner(System.in);
            System.out.println("id: ");
            String id = scanner.nextLine();

            for (int i = 0; i < tasks.getLength(); i++) {
                if (tasks.item(i).getAttributes().getNamedItem("id").getNodeValue().equals(id)) {
                    editChild(document, tasks.item(i), (Element) descriptions.item(i));
                    editChild(document, tasks.item(i), (Element) priority.item(i));
                    editChild(document, tasks.item(i), (Element) deadline.item(i));
                    editChild(document, tasks.item(i), (Element) status.item(i));
                    editChild(document, tasks.item(i), (Element) complete.item(i));

                    return;
                }
            }

            System.out.println("Задание с id = " + id + " не найдено!");

        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    public void editChild(Document doc, Node task, Element changeableElement) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Изменить " + changeableElement.getTagName() + "? (Y/N(Любая кнопка))");
        if (scanner.nextLine().equals("Y")) {
            if (changeableElement.getTagName().equals("Status")) {
                System.out.println("Новое значение:");

                switch (scanner.nextLine()){
                    case "NEW":
                        changeableElement.setTextContent(TaskStatus.NEW.toString());
                        System.out.println("Status изменён на NEW");
                        break;
                    case "IN_PROGRESS":
                        changeableElement.setTextContent(TaskStatus.IN_PROGRESS.toString());
                        System.out.println("Status изменён на IN_PROGRESS");
                        break;
                    case "DONE":
                        changeableElement.setTextContent(TaskStatus.DONE.toString());
                        System.out.println("Status изменён на DONE");
                        break;
                    default:
                        System.out.println("Некорректное значение! Status не изменён");
                }
            } else {
                if (changeableElement.getTagName().equals("Status")) {
                    NewTask.setPriority(doc, changeableElement);
                } else {
                    System.out.println("введите новое значение:");
                    changeableElement.setTextContent(scanner.nextLine());
                }
            }
        }

        NewTask.writeDocument(doc, "To-Do List.xml");
    }
}
