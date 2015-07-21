package nl.david.test;

import nl.david.converter.AccountType;
import nl.david.converter.Converter;
import org.junit.Test;

/**
 * Created by uc59io on 21-7-2015.
 */
public class SavingsTest {

    @Test
    public void generateSavingsAccountFileTest() {
        String dataFile = "resources/TXT150709232709.TAB";
        String referenceFile = "resources/categories.csv";

        Converter conv = new Converter();
        conv.readDataFile(dataFile, AccountType.SAVINGS);
        conv.readCategoriesFile(referenceFile);
        conv.translateCategory();
        //conv.processTransfers();
        conv.writeImportFile();
        System.out.println("Savings account file generated!");
    }
}
