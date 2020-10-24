package com.company;

import java.util.*;


class TerminalXML //Класс для работы с коммандами c XML
{
    ArrayList<Command> commands = new ArrayList<Command>();//Список комманд терминала
    Scanner input = new Scanner(System.in);

    public TerminalXML(){
        commands.add(new HelpCommand());
        commands.add(new NewTask());
        commands.add(new ListCommand());
        commands.add(new EditCommand());
        commands.add(new RemoveCommand());
        commands.add(new ListNewCommand());
        commands.add(new ListDoneCommand());
    }

    public void commandsWorking() {

        String keyWord = input.nextLine();

        for (int i = 0; i < commands.size(); i++) {
            if (keyWord.equals(commands.get(i).name)) {
                commands.get(i).action();
                return;
            }
        }
        System.out.println("Некорректный код. Используйте комманду 'help'");
    }
}
