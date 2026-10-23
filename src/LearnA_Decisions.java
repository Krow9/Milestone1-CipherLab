/**
 * Learn Card A: Boolean Expressions and if / else  (CED 2.1 - 2.3)
 *
 * 1. PREDICT: Before you run, write down what you think prints.
 * 2. RUN it. Were you right?
 * 3. EXPLORE: Do the TRY tasks at the bottom, one at a time.
 */
public class LearnA_Decisions {
    public static void main(String[] args) {
        int temp = 58;

        // A Boolean expression is a question with a true/false answer.
        boolean isCold = temp < 60;
        System.out.println("isCold: " + isCold);

        // if / else if / else: Java checks from the top.
        // The FIRST true condition wins, and the rest are skipped.
        if (temp >= 80) {
            System.out.println("Wear shorts");
        } else if (temp >= 60) {
            System.out.println("Wear a T-shirt");
        } else {
            System.out.println("Wear a jacket");
        }

        // Compare Strings with .equals(), never ==
        String day = "Saturday";
        if (day.equals("Saturday")) {
            System.out.println("Weekend!");
        }
        System.out.println("Done");

        // TRY 1: Change temp to 80, then 60, then 59. Predict before each run.
        // TRY 2: Swap the first two conditions (put temp >= 60 first).
        //        Set temp to 85. What prints now? Why?
        // TRY 3: Change day to "saturday" (lowercase s). What happens?
    }
}
