package nl.david.test;

import nl.david.converter.AccountType;
import nl.david.converter.Converter;
import org.junit.Test;

public class CheckingTest {

    @Test
    public void generateCheckingAccountFileTest() {
        String dataFile = "src/main/resources/TXT150709232737.TAB";
        String referenceFile = "src/main/resources/categories.csv";

        Converter conv = new Converter();
        conv.readDataFile(dataFile, AccountType.CHECKING);
        conv.readCategoriesFile(referenceFile);
        conv.translateCategory();
        //conv.processTransfers();
        conv.writeImportFile();
        System.out.println("Checking account file generated!");
    }
}
