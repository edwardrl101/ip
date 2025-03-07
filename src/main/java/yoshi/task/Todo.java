package yoshi.task;

public class Todo extends Task{
    public Todo(String description) {
        super(description);
    }

    /**
     * Overrides the original toString() method.
     * @return the string equivalent of the todo task as follows, e.g.
     * "[T][X] Meet up with a friend."
     */
    @Override
    public String toString() {
        return "[T][" + getStatusIcon() + "] " + description;
    } // Commit
}
