package yoshi.task;

public class Deadline extends Task{
    protected String by;

    public Deadline(String description, String by){
        super(description);
        this.by = by;
    }

    public String getBy(){
        return by;
    }

    @Override
    public String toString(){
        return "[D][" + getStatusIcon() +"] " + description + " (by: " + by + ")";
    }
}
