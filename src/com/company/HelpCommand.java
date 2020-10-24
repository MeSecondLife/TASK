package com.company;

class HelpCommand extends Command //команда help
{
    public HelpCommand(){
        name = "help";
    }

    public void action() {
        System.out.print("Список комманд:\n");
        System.out.print("'list -s new' - вывод новых задач\n");
        System.out.print("'list -s done' - вывод выполненных задач\n");
        System.out.print("'list' - вывод всех задач\n");
    }
}
