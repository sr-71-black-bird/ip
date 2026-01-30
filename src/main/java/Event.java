public class Event extends Task {
    String start;
    String end;

    public Event(String name, String start, String end) throws WrongInstructionException {
        super(name);
        if (this.start.equals("")) {
            throw new WrongInstructionException("Your start date invalid");
        }
        if (this.end.equals("")) {
            throw new WrongInstructionException("Your end date invalid");
        }
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), this.start, this.end);
    }
}
