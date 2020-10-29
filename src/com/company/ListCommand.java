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
 * класс для вывода задач по статусам
 */
class ListCommand extends Command {
    public ListCommand(){
        setName("list");
        setDescription("выводит задания");
    }

    /**
     * Метод, выполняющий действие, соответствующее команде
     * @param obj - универсальный параметр
     * @param args - передаётся команда от пользователя
     */
    public void action(Object obj, ArrayList<String> args){
        System.out.println("Список всех задач:");
        ArrayList<Task> tasks = (ArrayList<Task>)obj;

        if(args.size() == 3) {
            if (args.get(1).equals("-s")) {
                if (args.get(2).equals("done")) {
                    for (Task task : tasks) {
                        if (task.getStatus().toString().equals("DONE"))
                            printTask(task);
                    }
                }
                if (args.get(2).equals("new")) {
                    for (Task task : tasks) {
                        if (task.getStatus().toString().equals("NEW"))
                            printTask(task);
                    }
                }
            }
        } else{
            for (Task task : tasks) {
                printTask(task);
            }
        }
    }

    private void printTask(Task task){
        System.out.println("id = " + task.getID());
        System.out.println("заголовок: " + task.getCaption());
        System.out.println("описание: " + task.getDescription());
        System.out.println("дедлайн: " + task.getDeadline());
        System.out.println("приоритет: " + task.getPriority());
        System.out.println("статус: " + task.getStatus());
        System.out.println("дата завершения: " + task.getComplete());
    }
}
