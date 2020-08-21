package com.joymacharia.collaboapp.models;

public class Task {
    private int id;
    private  int taskImage;
    private String taskTitle;
    private String taskDeadline;
    //////////////////////////
    //private String task,date;
    private User user;


public Task() {
    }
    /*Create a constructor for the recipe data model
     * Pass the parameters recipeImage, recipeTitle and recipeDescription*/

   /* public Task(String taskTitle,String task, String taskDeadline,String date)// String recipeIngredients)
    {
        this.task= task;
        this.date= date;
        this.taskTitle = taskTitle;
        this.taskDeadline = taskDeadline;

    } */

//////////////////////////////////////////////


    public Task(int taskImage, String taskTitle, String taskDeadline)// String recipeIngredients)
    {
        this.taskImage = taskImage;
        this.taskTitle = taskTitle;
        this.taskDeadline = taskDeadline;

    }


    /* Create the getters and return the specific object*/


    public int getTaskImage(){ return  taskImage;}
    public String getTaskTitle()
    {
        return taskTitle;
    }
    public String getTaskDeadline()
    {
        return taskDeadline;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    ///////////////////////////
   /* public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }          */
    public void setTaskDeadline(String taskDeadline){
        this.taskDeadline= taskDeadline;

    }
    public  void setTaskTitle(String taskTitle)
    {
        this.taskTitle = taskTitle;
    }
    public  void setTaskImage(int taskImage)
    {
        this.taskImage = taskImage;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
