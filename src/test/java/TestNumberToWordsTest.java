import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by josh on 28/6/17.
 */
public class TestNumberToWordsTest {

    /**
     * Constructor being used to test conditions before being operated on
     *
     * @param num : the number which is input as a string
     * @throws NumberToWordException: throws when num is null
     */
//    public TestNumberToWordsTest(String num) throws NumberToWordException, NumberFormatException {
//        super(num);
//    }

    /****************************************************************

     Dollar Tests

     *****************************************************************/


    @Test
    public void singleDigitCheck() throws Exception {
        NumberToWord a = new NumberToWord("9");
        String numberString = a.wordify();
        assertTrue(numberString.equals("NINE DOLLARS"));
    }

    @Test
    public void zeroCheck() throws Exception {

        NumberToWord a = new NumberToWord("0");
        String numberString = a.wordify();
        assertTrue(numberString.equals("ZERO DOLLARS"));
    }

    @Test
    public void singleDigitDollarCheck() throws Exception {

        NumberToWord a = new NumberToWord("1");
        String numberString = a.wordify();
        assertTrue(numberString.equals("ONE DOLLAR"));
    }

    @Test
    public void negativeDigitsDollarCheck() throws Exception {

        NumberToWord a = new NumberToWord("-2");
        String numberString = a.wordify();
        assertTrue(numberString.equals("NEGATIVE TWO DOLLARS"));
    }

    @Test
    public void teenDigitCheck() throws Exception {

        NumberToWord a = new NumberToWord("14");
        String numberString = a.wordify();
        assertTrue(numberString.equals("FOURTEEN DOLLARS"));
    }

    @Test
    public void largestDigitCheck() throws Exception {

        NumberToWord a = new NumberToWord("999999999");
        String numberString = a.wordify();
        assertTrue(numberString.equals("NINE HUNDRED AND NINETY-NINE MILLION NINE HUNDRED AND " +
                "NINETY-NINE THOUSAND NINE HUNDRED AND NINETY-NINE DOLLARS"));
    }


    /****************************************************************

     Cents Tests

     *****************************************************************/

    @Test
    public void centsDigitCheck() throws Exception {

        NumberToWord a = new NumberToWord("0.11");
        String numberString = a.wordify();

        assertTrue(numberString.equals("ELEVEN CENTS"));
    }

    @Test
    public void centsSingleDigitCheck() throws Exception {

        NumberToWord a = new NumberToWord("0.1");
        String numberString = a.wordify();

        assertTrue(numberString.equals("TEN CENTS"));
    }

    @Test
    public void centDigitCheck() throws Exception {

        NumberToWord a = new NumberToWord("0.01");
        String numberString = a.wordify();

        assertTrue(numberString.equals("ONE CENT"));
    }

    @Test
    public void negativeCentsDigitCheck() throws Exception {

        NumberToWord a = new NumberToWord("-0.02");
        String numberString = a.wordify();

        assertTrue(numberString.equals("NEGATIVE TWO CENTS"));
    }


    /****************************************************************

     Dollars And Cents Tests

     *****************************************************************/


    @Test
    public void dollarAndCentDigitCheck() throws Exception {

        NumberToWord a = new NumberToWord("1.01");
        String numberString = a.wordify();

        assertTrue(numberString.equals("ONE DOLLAR AND ONE CENT"));
    }

    @Test
    public void dollarAndCentsDigitCheck() throws Exception {

        NumberToWord a = new NumberToWord("1.11");
        String numberString = a.wordify();

        assertTrue(numberString.equals("ONE DOLLAR AND ELEVEN CENTS"));
    }

    @Test
    public void negativeDollarAndCentsDigitCheck() throws Exception {

        NumberToWord a = new NumberToWord("-1.11");
        String numberString = a.wordify();

        assertTrue(numberString.equals("NEGATIVE ONE DOLLAR AND ELEVEN CENTS"));
    }


    @Test
    public void largeDollarsAndCentsDigitCheck() throws Exception {

        NumberToWord a = new NumberToWord("12345678.91");
        String numberString = a.wordify();

        assertTrue(numberString.equals("TWELVE MILLION THREE HUNDRED AND FORTY-FIVE" +
                " THOUSAND SIX HUNDRED AND SEVENTY-EIGHT DOLLARS AND NINETY-ONE CENTS"));
    }

    /****************************************************************

     Exception Tests

     *****************************************************************/


    @Test(expected = NumberFormatException.class)
    public void testNumberToWordException() throws NumberFormatException, NumberToWordException {

        NumberToWord a = new NumberToWord("a word");
    }

    @Test(expected = NumberToWordException.class)
    public void testNumberFormatException() throws NumberFormatException, NumberToWordException {

        NumberToWord a = new NumberToWord(null);
    }

    @Test(expected = NumberFormatException.class)
    public void testNumberTooLargeException() throws NumberFormatException, NumberToWordException {

        NumberToWord a = new NumberToWord("12345678910");
    }
}