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

/**
 * Класс для работы с xml файлами
 */
class XMLFileManager {
    static String path = "To-Do List.xml";

    /**
     * Метод для перезаписи заданий
     * @param tasks лист для перечисления задач
     */
    public static  void rewriteTasks(ArrayList<Task> tasks) {
        DocumentBuilder documentBuilder;
        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(path);

            remove(document);

            Element root = document.createElement("Root");
            document.appendChild(root);
            writeDocument(document);

            for(Task task : tasks){
                writeTask(task, document);
            }

            writeDocument(document);
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        };
    }

    /**
     * Чтение задачи из файла
     * @return возвращает список задач из файла
     */
    public static ArrayList<Task> readTasksFromFile() {
        DocumentBuilder documentBuilder;

        try {
            documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(path);

            NodeList nodeList = document.getElementsByTagName("Task");
            ArrayList<Task> newTasks = new ArrayList<Task>();

            for (int i = 0; i < nodeList.getLength(); i++) {
                if (nodeList.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    Element el = (Element) nodeList.item(i);
                    if (el.getNodeName().contains("Task")) {
                        Task task = new Task();

                        task.setID(Integer.parseInt(el.getAttributes().getNamedItem("id").getNodeValue()));
                        task.setCaption( el.getAttributes().getNamedItem("caption").getNodeValue());
                        task.setDescription(el.getElementsByTagName("Description").item(0).getTextContent());
                        task.setPriority(Integer.parseInt(el.getElementsByTagName("Priority").item(0).getTextContent()));
                        task.setDeadline(el.getElementsByTagName("Deadline").item(0).getTextContent());
                        task.setStatus(el.getElementsByTagName("Status").item(0).getTextContent());
                        if(el.getElementsByTagName("Complete").item(0).getTextContent().equals(""))
                            task.setComplete("not set") ;
                        else
                            task.setComplete(el.getElementsByTagName("Complete").item(0).getTextContent());

                        newTasks.add(task);
                    }
                }
            }
            return newTasks;

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void writeTask(Task task, Document document){
        Element newTask = document.createElement("Task");

        newTask.setAttribute("id", task.getID().toString());
        newTask.setAttribute("caption", task.getCaption().toString());

        Element description = document.createElement("Description");
        description.setTextContent(task.getDescription().toString());
        newTask.appendChild(description);

        Element priority = document.createElement("Priority");
        priority.setTextContent(task.getPriority().toString());
        newTask.appendChild(priority);

        Element deadline = document.createElement("Deadline");
        deadline.setTextContent(task.getDeadline().toString());
        newTask.appendChild(deadline);

        Element status = document.createElement("Status");
        status.setTextContent(task.getStatus().toString());
        newTask.appendChild(status);

        Element complete = document.createElement("Complete");
        complete.setTextContent(task.getComplete());
        newTask.appendChild(complete);

        document.getDocumentElement().appendChild(newTask);
    }

    private static void writeDocument(Document document) throws TransformerFactoryConfigurationError {
        try {
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(document);
            FileOutputStream fos = new FileOutputStream(path);
            StreamResult result = new StreamResult(fos);
            tr.transform(source, result);
        } catch (TransformerException | IOException e) {
            e.printStackTrace(System.out);
        }
    }

    private static void remove(Document document) {
        Node task = document.getDocumentElement();
        task.getParentNode().removeChild(task);
        writeDocument(document);
    }
}

