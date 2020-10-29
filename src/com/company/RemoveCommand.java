package com.company;

import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import javax.xml.parsers.DocumentBuilderFactory;
import java.util.*;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 * класс для удаления задач
 */
class RemoveCommand extends Command {
    public RemoveCommand(){
        setName("remove");
        setDescription("удаляет задания");
    }

    /**
     * Метод, выполняющий действие, соответствующее команде
     * @param obj - универсальный параметр
     * @param args - передаётся команда от пользователя
     */
    public void action(Object obj, ArrayList<String> args) {

        System.out.println("Введите id: ");
        String id = new Scanner(System.in).nextLine();

        List<Task> tasks = (List<Task>)obj;

        for(Task task : tasks){
            if(task.getID() == Integer.parseInt(id)){
                tasks.remove(task);
                System.out.println("Задание успешно удалено!");
                return;
            }
        }

        System.out.println("Задание с id = " + id + " не найдено!");

    }
}

