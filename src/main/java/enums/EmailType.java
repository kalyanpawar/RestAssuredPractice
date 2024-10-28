package enums;

public enum EmailType {
    HOME("Home"),
    OFFICE("Office");

    private final String abbreviation;

    EmailType(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getValue() {
        return abbreviation;
    }

//    @Override
//    public String toString() {
//        return this.name() + " (" + abbreviation + ")";
//    }
}


