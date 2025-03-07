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

    /**
     * Overrides the original toString() method.
     * @return the string equivalent of the deadline task as follows, e.g.
     * "[D][X] Do CS2113 weekly quiz (by: 11.59pm)
     */
    @Override
    public String toString(){
        return "[D][" + getStatusIcon() +"] " + description + " (by: " + by + ")";
    }
}
