package com.company;

import java.util.ArrayList;

/**
 * Является абстрактным классом родителем для остальных команд
 */
abstract class Command {
    private String name;

    /**
     * @return  name; Изменяет значение на value
     */
    public String getName(){return name;}
    protected void setName(String value){name = value;}

    private String description;

    /**
     *
     * @return description; Изменяет значение на value
     */
    public String getDescription(){return description;}
    protected void setDescription(String value){description = value;}

    /**
     * Метод, выполняющий действие, соответствующее команде
     * @param obj - универсальный параметр
     * @param args - передаётся команда от пользователя
     */
    public abstract void action(Object obj, ArrayList<String> args);
}

