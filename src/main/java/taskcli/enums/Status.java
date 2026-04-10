package taskcli.enums;

public enum Status {
    TODO("To do"),
    IN_PROGRESS("In progress"),
    DONE("Done");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }

    public static Status fromString(String text) {
        text = text.trim()
            .toUpperCase()
            .replace(" ", "_")
            .replace("-", "_");

        try {
            return Status.valueOf(text);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unknown status: " + text);
        }
    }
}
