package nl.david.converter;

public enum AccountType {
    SAVINGS ("Savings"),
    CHECKING ("ABN");

    private final String name;

    AccountType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
