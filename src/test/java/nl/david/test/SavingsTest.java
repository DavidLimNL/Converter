package nl.david.test;

import nl.david.converter.AccountType;
import nl.david.converter.Converter;
import org.junit.Test;

public class SavingsTest {

    @Test
    public void generateSavingsAccountFileTest() {
        String dataFile = "src/main/resources/TXT150709232709.TAB";
        String referenceFile = "src/main/resources/categories.csv";

        Converter conv = new Converter();
        conv.readDataFile(dataFile, AccountType.SAVINGS);
        conv.readCategoriesFile(referenceFile);
        conv.translateCategory();
        //conv.processTransfers();
        conv.writeImportFile();
        System.out.println("Savings account file generated!");
    }
}
