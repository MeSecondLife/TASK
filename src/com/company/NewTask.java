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
 * Класс для создания новой задачи
 */
class NewTask extends Command {
    public NewTask(){
        setName("new");
        setDescription("создаёт новую задачу");
    }

    /**
     * метод выполняющие действия, соответствующие команде.
     * @param obj - универсальный параметр
     * @param args - передаётся команда от пользователя
     */
    public void action(Object obj, ArrayList<String> args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = (ArrayList<Task>)obj;
        Task lastItem = new Task();

        lastItem.setID(getID(tasks));

        System.out.println("заголовок: ");
        lastItem.setCaption(scanner.nextLine());

        System.out.println("описание задачи: ");
        lastItem.setDescription(scanner.nextLine());

        System.out.println("срок выполнения: ");
        lastItem.setDeadline(scanner.nextLine());

        System.out.println("приоритет: ");
        lastItem.setPriority(scanner.nextLine());

        lastItem.setStatus(TaskStatus.NEW);

        tasks.add(lastItem);
    }

    private int getID(ArrayList<Task> tasks){
        Integer id = 0;

        for(Task task : tasks){
            if(task.getID() > id){
                id = task.getID();
            }
        }

        id++;
        return id;
    }
}