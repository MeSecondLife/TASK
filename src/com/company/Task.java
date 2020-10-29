package com.company;

/**
 * Класс для хранения данных задачи
 */
class Task {
    private Integer id;
    private String caption;
    private String description;
    private Integer priority;
    private String Deadline;
    private TaskStatus status;
    private String complete;

    /**
     *
     * @return id, caption, description, priority, DeadLine, status, complete. Изменяет их значение на value.
     */
    public Integer getID() { return id; }
    public void setID(int value) { id = value; }

    public String getCaption() { return caption; }
    public void setCaption(String value) { caption = value; }

    public String getDescription() { return description; }
    public void setDescription(String value) { description = value; }

    public Integer getPriority() { return priority; }
    public void setPriority(int value) {
        if(value >= 0 && value <= 100)
            priority = value;
        else
            System.out.println("Некорректное значение приоритета!");
    }
    public void setPriority(String value) {
        int num;

        try{
            num = Integer.parseInt(value);

            if(num >= 0 && num <= 100)
                priority = num;
            else
                System.out.println("Некорректное значение приоритета!");
        }
        catch (Exception e){
            System.out.println("Некорректное значение приоритета!");
        };
    }

    public String getDeadline() { return Deadline; }
    public void setDeadline(String value) { Deadline = value; }

    public TaskStatus getStatus() { return status; }//получить status
    public void setStatus(String value) { status = TaskStatus.valueOf(value); }//изменить status
    public void setStatus(TaskStatus value) { status = value; }//изменить status

    public String getComplete() { return complete; }
    public void setComplete(String value) { complete = value; }


}
