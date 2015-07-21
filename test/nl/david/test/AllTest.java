package nl.david.test;

import org.junit.Test;

/**
 * Created by uc59io on 21-7-2015.
 */
public class AllTest {
    @Test
    public void CheckingTest() {
        CheckingTest ct = new CheckingTest();
        ct.generateCheckingAccountFileTest();
    }

    @Test
    public void SavingsTest() {
        SavingsTest st = new SavingsTest();
        st.generateSavingsAccountFileTest();
    }
}
