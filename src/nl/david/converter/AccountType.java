package nl.david.converter;

/**
 * Created by uc59io on 21-7-2015.
 */
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
