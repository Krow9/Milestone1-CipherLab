/**
 * Learn Card B: Nested if Statements and Rule Order  (CED 2.4)
 *
 * 1. PREDICT what prints for each call in main.
 * 2. RUN it. Were you right?
 * 3. EXPLORE: Do the TRY tasks at the bottom.
 */
public class LearnB_Nested {

    // A movie theater ticket checker.
    public static String ticket(int age, boolean hasTicket) {
        if (!hasTicket) {
            return "Buy a ticket first";
        } else {
            // This if is NESTED: it only runs when the outer else runs.
            if (age >= 17) {
                return "Enjoy the movie";
            } else if (age >= 13) {
                return "PG-13 only";
            } else {
                return "Bring an adult";
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(ticket(20, true));
        System.out.println(ticket(14, true));
        System.out.println(ticket(9, true));
        System.out.println(ticket(20, false));

        // TRY 1: Move the hasTicket check so it happens AFTER the age checks.
        //        Which call above now gives a wrong answer? Why?
        // TRY 2: Add a rule: anyone 65 or older gets "Senior discount".
        //        Where does it have to go so it works?
        // TRY 3: Rewrite ticket() using only if / else if / else with no
        //        nesting. Is it easier or harder to read?
    }
}
