package com.company;

class ListDoneCommand extends Command//команда list -s done
{
    public ListDoneCommand(){
        name = "list -s done";
    }

    public void action(){
        System.out.println("Список выполненных задач:");
        ListCommand.tasksSelectionWrite(TaskStatus.DONE);
    }
}

