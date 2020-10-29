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
 *  класс для изменения задач
 */
class EditCommand extends Command {
    public EditCommand() {
        setName("edit");
        setDescription("изменяет задания");
    }

    /**
     * Метод, выполняющий действие, соответствующее команде
     * @param obj - универсальный параметр
     * @param args - передаётся команда от пользователя
     */
    public void action(Object obj, ArrayList<String> args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Task> tasks = (ArrayList<Task>)obj;

        System.out.println("ID изменяемого объекта: ");
        int id = Integer.parseInt(scanner.nextLine());

        for (Task task : tasks) {
            if (task.getID() == id) {
                System.out.println("Изменить заголовок (Y/N)?");
                if (scanner.nextLine().equals("Y")) {
                    System.out.println("Введите новое значение:");
                    task.setCaption(scanner.nextLine());
                }

                System.out.println("Изменить описание (Y/N)?");
                if (scanner.nextLine().equals("Y")) {
                    System.out.println("Введите новое значение:");
                    task.setDescription(scanner.nextLine());
                }

                System.out.println("Изменить приоритет (Y/N)?");
                if (scanner.nextLine().equals("Y")) {
                    System.out.println("Введите новое значение:");
                    task.setPriority(scanner.nextLine());
                }

                System.out.println("Изменить дедлайн (Y/N)?");
                if (scanner.nextLine().equals("Y")) {
                    System.out.println("Введите новое значение:");
                    task.setDeadline(scanner.nextLine());
                }

                System.out.println("Изменить статус (Y/N)?");
                if (scanner.nextLine().equals("Y")) {
                    System.out.println("Введите новое значение: new/done/in progress");
                    switch (scanner.nextLine()) {
                        case "new":
                            task.setStatus(TaskStatus.NEW);
                            break;
                        case "done":
                            task.setStatus(TaskStatus.DONE);
                            break;
                        case "in progress":
                            task.setStatus(TaskStatus.IN_PROGRESS);
                            break;
                        default:
                            System.out.println("Некорректный ввод! Статус не изменён.");
                    }

                }
                return;
            }
        }
        System.out.println("Задания с ID = " + id + " не существует!");
    }
}
