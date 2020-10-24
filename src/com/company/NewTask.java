package com.company;

import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.*;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;
import org.xml.sax.SAXException;


class NewTask extends Command//команда new
{
    public NewTask(){
        name = "new";
    }

    public void action(){
        Scanner scanner = new Scanner(System.in);
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse("To-Do List.xml");

            // Получаем корневой элемент
            Element root = document.getDocumentElement();

            // Добавление Task в файл
            Element task = document.createElement("Task");

            task.setAttribute("id", getID(document.getElementsByTagName("Task")));

            System.out.println("заголовок: ");
            task.setAttribute("caption", scanner.nextLine());

            System.out.println("описание задачи: ");
            Element description = document.createElement("Description");
            description.setTextContent(scanner.nextLine());
            task.appendChild(description);

            setPriority(document, task);//Добавление приоритета

            System.out.println("срок выполнения: ");
            Element deadline = document.createElement("Deadline");
            deadline.setTextContent(scanner.nextLine());
            task.appendChild(deadline);

            Element status = document.createElement("Status");
            status.setTextContent(TaskStatus.NEW.toString());
            task.appendChild(status);

            Element complete = document.createElement("Complete");
            task.appendChild(complete);

            root.appendChild(task);

            document.getDocumentElement().appendChild(task);// Добавляем задачу в корневой элемент
            writeDocument(document, "To-Do List.xml");
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace(System.out);
        }
    }
    public static void writeDocument(Document document, String path) throws TransformerFactoryConfigurationError {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty(OutputKeys.INDENT, "yes");//Нужно для построчной записи
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream(path);
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
    }
    private String getID(NodeList tasks){
        int lastIndex = tasks.getLength();
        if(lastIndex > 0) {
            Integer id = Integer.parseInt(tasks.item(lastIndex-1).getAttributes().getNamedItem("id").getNodeValue()) + 1;
            String strID = id.toString();
            return strID;
        }
        return "0";
    }
    public static void setPriority(Document document, Node task){
        Scanner scanner = new Scanner(System.in);

        System.out.println("приоритет (1-100): ");
        Element priority = document.createElement("Priority");

        do {
            Integer input = 0;

            try {
                input = Integer.parseInt(scanner.nextLine());}
            catch (NumberFormatException e) {
                System.out.println("некорректный ввод значния приоритета!\n Введите другое значение:");
                continue;
            }

            if (input >= 0 && input <= 100) {
                priority.setTextContent(input.toString());
                task.appendChild(priority);
                break;
            } else {
                System.out.println("некорректный ввод значния приоритета!\n Введите другое значение:");
            }

        }while(true);//приоритет
    }
}

