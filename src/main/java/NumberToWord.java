import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by josh on 27/6/17.
 */
public class NumberToWord {

    private static final String[] ONE_TO_NINE = {
            "ZERO",
            "ONE",
            "TWO",
            "THREE",
            "FOUR",
            "FIVE",
            "SIX",
            "SEVEN",
            "EIGHT",
            "NINE"

    };

    private static final String[] ELEVEN_TO_NINETEEN = {
            "TEN",
            "ELEVEN",
            "TWELVE",
            "THIRTEEN",
            "FOURTEEN",
            "FIFTEEN",
            "SIXTEEN",
            "SEVENTEEN",
            "EIGHTEEN",
            "NINETEEN"
    };

    private static final String[] TENS = {
            "TWENTY",
            "THIRTY",
            "FORTY",
            "FIFTY",
            "SIXTY",
            "SEVENTY",
            "EIGHTY",
            "NINETY"
    };

    private static final String[] MAGNITUDES = {
            "HUNDRED",
            "THOUSAND",
            "MILLION",
            "BILLION",
            "TRILLION",
            "QUADRILLION",
            "QUINTILLION",
            "SEXTILLION",
            "SEPTILLION",
            "OCTILLION",
            "NONILLION",
            "DECILLION",
    };

    private boolean neg = false;
    private boolean oneDollar = false;
    private boolean oneCent = false;
    private int dollarPart ;
    private String centPart ;
    private int mag;


    /**
     * Constructor being used to test conditions before being operated on
     *
     * @param num: the number which is input as a string
     * @throws NumberToWordException: throws when num is null
     */
    public NumberToWord(String num) throws NumberToWordException, NumberFormatException {


        if(num == null){
            throw new NumberToWordException("Num can not be null");
        }
        if(!isInteger(num,10)) {
            System.out.println(num);
            System.out.println(isInteger(num,10));
            throw new NumberFormatException("Number needed");
        }
        if(isNegative(num)){
            this.neg = true;
            num = num.substring(1);
        }

        if (num.contains(".")){

            this.dollarPart = Integer.parseInt(num.split("[.]")[0]);
            this.centPart = num.split("[.]")[1];
            System.out.println("this shouldnt be here");
            if(isJustOne(handleCents(this.centPart))) {
                this.oneCent = true;
            }
        } else {
            this.dollarPart = Integer.parseInt(num.split("[.]")[0]);
            this.centPart = null;
        }

        if(isJustOne(this.dollarPart)){
            this.oneDollar = true;
        }

      //  this.mag = num.replace("\\W", "").length()/3;
    }


    /**
     * The method used to convert the number to words
     * @return A string of words which are the numbers wordified
     */

    public String wordify() {
        List<String> numberWords = new ArrayList<>();
        if(neg){
            numberWords.add("NEGATIVE");
        }


        numberWords.add(maker(this.dollarPart));
        if (oneDollar){
            numberWords.add("DOLLAR");
        } else if(this.dollarPart>0) {
            numberWords.add("DOLLARS");
        }
        if (centPart != null){
            if (this.dollarPart > 0){
                numberWords.add(" AND " + performZeroToNinetyNinePattern(handleCents(this.centPart)) + " CENTS");
            } else if(oneCent) {
                numberWords.add(performZeroToNinetyNinePattern(handleCents(centPart)) + " CENT");
            } else {
                numberWords.add(performZeroToNinetyNinePattern(handleCents(centPart)) + " CENT");

            }

        }

        return numberWords.stream().collect(Collectors.joining(" "));
    }


    /**
     *
     * @param num
     * @return
     */
    private String maker(int num) {

        List<String> numberWords = new ArrayList<>();
        Stack numberQueue = numberSplitter(num);


        while(!numberQueue.isEmpty()){
            int item = (Integer) numberQueue.pop();

            numberWords.add(performZeroToNinetyNinePattern(item) + " ");
            if(!(numberQueue.size() == 0)) {
                numberWords.add(MAGNITUDES[numberQueue.size()]);
            }

        }

        return numberWords.stream().collect(Collectors.joining(" "));
    }

    /**
     * A function that matches the pattern of the english terms
     * @param num must be below 999 or else it will cause all types of nonsense
     * @return a string that is of similar pattern to the 0 to 999 in spelt english
     */

    private String performZeroToNinetyNinePattern(int num){
        if (num <= 9) {
            return ONE_TO_NINE[num];
        } else if (num <= 19) {
            // Numbers are named oddly
            return ELEVEN_TO_NINETEEN[num-10];
        } else if (num <= 99) {
            if ((num) % 10 == 0) {
                return TENS[(num / 10) - 2];
            } else {
                return TENS[(num / 10) - 2] + "-" + ONE_TO_NINE[(num) % 10];
            }
        } else {
            if((num)%100 == 0){
                // English has a preference for the word "hundred" so it can be hard coded here with no issue
                return ONE_TO_NINE[((num)/100)] +  " " + MAGNITUDES[0];
            } else {
                // Handle this
               return ONE_TO_NINE[(num)/100] + " " + MAGNITUDES[0] + " AND " + performZeroToNinetyNinePattern((num%100));
            }
        }

    }

    /**
     * Splits the numbers into groups of three or less
     * @param num
     * @return
     */
    private Stack numberSplitter(int num){
        Stack<Integer> numberQueue;

        numberQueue = new Stack<>();
        while(num >= 1) {

            numberQueue.add(num%1000);
            num = num/1000;
        }
        return numberQueue;
    }

    /**
     * A helper method used to determine whether a string number is negative
     * @param number
     * @return returns true if the first number is a dash (-)
     */
    private boolean isNegative(String number) {
        return number.replaceAll("\\s+","").charAt(0) == '-';
    }

    /**
     * TODO
     * @param s
     * @param radix
     * @return
     */
    private static boolean isInteger(String s, int radix) {
        s = s.replaceAll("\\W","");
        Scanner sc = new Scanner(s.trim());
        if(!sc.hasNextInt(radix)) return false;
        sc.nextInt(radix);
        return !sc.hasNext();
    }

    /**
     * TODO
     * @param num
     * @return
     */
    private static boolean isJustOne(int num){
        return num == 1|| num == -1;
    }

    /**
     * Takes a string in the form of cents and returns the actual number keeping the position of the 0.
     * @param cents
     * @return
     */
    private int handleCents(String cents) {
        if(Integer.parseInt(cents) == 0 && cents.length() > 1){
            return Integer.parseInt(cents);
        } else if(cents.length() == 1) {
            return Integer.parseInt(cents) * 10;
        } else {
            return Integer.parseInt(cents);
        }
    }
}

