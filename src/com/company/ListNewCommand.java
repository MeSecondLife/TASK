package com.company;

class ListNewCommand extends Command//команда list -s new
{
    public ListNewCommand(){
        name = "list -s new";
    }

    public void action(){
        System.out.println("Список новых задач:");
        ListCommand.tasksSelectionWrite(TaskStatus.NEW);
    }
}

