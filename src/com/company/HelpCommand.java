package com.company;

import java.util.ArrayList;

/**
 * класс реализует команду help
 */
class HelpCommand extends Command {
    public HelpCommand(){
        setName("help");
        setDescription("Выводит списк комманд");
    }

    /**
     * Метод, выполняющий действие, соответствующее команде
     * @param obj - универсальный параметр
     * @param args - передаётся команда от пользователя
     */
    public void action(Object obj, ArrayList<String> args) {
        ArrayList<Command> commands = (ArrayList<Command>)obj;

        System.out.println("Список комманд:");
        for(Command item : commands){
            System.out.println("имя: " + item.getName() + "\n"+ "Описание: " + item.getDescription());
        }
    }
}

