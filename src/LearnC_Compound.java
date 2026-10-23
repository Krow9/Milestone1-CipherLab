/**
 * Learn Card C: Compound Boolean Expressions  (CED 2.5)
 *
 *   &&  AND - true only if BOTH sides are true
 *   ||  OR  - true if AT LEAST ONE side is true
 *   !   NOT - flips true and false
 *
 * 1. PREDICT each line before you run.
 * 2. RUN it. Were you right?
 * 3. EXPLORE: Do the TRY tasks at the bottom.
 */
public class LearnC_Compound {
    public static void main(String[] args) {
        int age = 16;
        boolean hasPermit = true;

        System.out.println("Can drive: " + (age >= 16 && hasPermit));
        System.out.println("Teen: " + (age >= 13 && age <= 19));
        System.out.println("Free entry: " + (age < 5 || age >= 65));
        System.out.println("No permit: " + !hasPermit);

        // Short-circuit: Java stops as soon as it knows the answer.
        // count is 0, so the left side is false and the division never runs.
        int total = 10;
        int count = 0;
        if (count != 0 && total / count > 2) {
            System.out.println("Big average");
        } else {
            System.out.println("Safe! No division by zero.");
        }

        // TRY 1: Change age to 70. Which lines change?
        // TRY 2: Change && to || in "Teen". Set age to 40. Why is it wrong now?
        // TRY 3: In the short-circuit if, swap the two sides of &&.
        //        Run it. What happens, and why?
    }
}
