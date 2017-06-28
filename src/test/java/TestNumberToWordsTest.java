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

    @Test
    public void singleDigitCheck() throws Exception {
        NumberToWord a = new NumberToWord("1");
        String numberString = a.wordify();
        assertTrue(numberString.equals("ONE  DOLLAR"));
    }

    @Test
    public void teenDigitCheck() throws Exception {
        NumberToWord a = new NumberToWord("14");
        String numberString = a.wordify();
        assertTrue(numberString.equals("FOURTEEN  DOLLARS"));
    }


}