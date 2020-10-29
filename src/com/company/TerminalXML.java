package com.company;

import java.util.*;


/**
 * Класс для работы с командами и задачами
 */
class TerminalXML {
    private ArrayList<Command> commands = new ArrayList<Command>();
    private ArrayList<Task> tasks = new ArrayList<Task>();
    Scanner input = new Scanner(System.in);

    public TerminalXML(){
        commands.add(new HelpCommand());
        commands.add(new NewTask());
        commands.add(new ListCommand());
        commands.add(new EditCommand());
        commands.add(new RemoveCommand());
    }

    /**
     * Осуществляет вызов команд в зависимости от введённой в консоль строки
     */
    public void commandsWorking() {
        String keyWord;
        tasks = XMLFileManager.readTasksFromFile();

        while(true) {
            boolean flag = false;
            keyWord = input.nextLine();

            if (keyWord.equals("end")) {
                XMLFileManager.rewriteTasks(tasks);
                System.exit(0);
            }

            ArrayList<String> commandWords = getArgs(keyWord);

            for (int i = 0; i < commands.size(); i++) {
                if (commandWords.get(0).equals(commands.get(i).getName())) {
                    if(commands.get(i).getName().equals("help")){
                        commands.get(i).action(commands, commandWords);
                        flag = true;
                        break;
                    }
                    commands.get(i).action(tasks, commandWords);
                    keyWord = "";
                    flag = true;
                    break;
                }
            }

            if(!flag) {
                System.out.println("Команды " + keyWord + " не существует!");
            }
        }
    }

    /**
     * Разбивает строку на слова
     * @param line - строка, которую ввёл пользователь
     * @return - возвращает лист слов
     */
    private ArrayList<String> getArgs(String line){
        ArrayList<String> clearLine = new ArrayList<>();
        String [] arr = line.split(" ");

        for (int i = 0; i < arr.length; i++){
            if(!arr[i].equals(""))
                clearLine.add(arr[i]);
        }

        return clearLine;
    }
}