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
}
