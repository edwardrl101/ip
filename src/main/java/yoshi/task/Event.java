package yoshi.task;

public class Event extends Task {
    protected String from; // the end time of the event.
    protected String to; // the start time of the event.

    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    /**
     * Overrides the original toString() method.
     * @return a string equivalent of the event, in the format as follows, e.g.
     * "[E][] CS2113 Project meeting (from: 4pm to: 6pm)"
     */
    @Override
    public String toString() {
        return "[E][" + getStatusIcon() + "] " + description + " (from: " + from + " to: " + to + ")";
    }
}
