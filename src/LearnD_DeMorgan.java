/**
 * Learn Card D: De Morgan's Laws  (CED 2.6)
 *
 *   !(a && b)  is the same as  !a || !b
 *   !(a || b)  is the same as  !a && !b
 *
 * Push the ! inside, flip && <-> ||, and negate each part.
 * Negating a comparison flips it:  !(x < 3)  is  x >= 3
 *
 * This program PROVES two expressions are equivalent by testing
 * all four combinations of true/false (a truth table).
 */
public class LearnD_DeMorgan {

    // Prints one row of the truth table: the original and the De Morgan version.
    public static void printRow(boolean rain, boolean cold) {
        boolean original = !(rain && cold);
        boolean deMorgan = !rain || !cold;
        System.out.println(pad(rain) + pad(cold) + "| " + pad(original) + "            " + pad(deMorgan));
    }

    // Makes true and false line up in columns.
    public static String pad(boolean b) {
        if (b) {
            return "true   ";
        } else {
            return "false  ";
        }
    }
    public static void main(String[] args) {
        System.out.println("rain   cold   | !(rain && cold)   !rain || !cold");
        printRow(true, true);
        printRow(true, false);
        printRow(false, true);
        printRow(false, false);

        // A comparison example.
        int score = 72;
        boolean original = !(score >= 70 && score < 80);
        boolean simplified = score < 70 || score >= 80;
        System.out.println("score " + score + ": " + original + " " + simplified);

        // TRY 1: Change score to 65, 79, and 80. Do the two columns always match?
        // TRY 2: On paper, simplify  !(x > 0 || y == 5)  using De Morgan.
        //        Then add it to this program and test it with a few values.
        // TRY 3: Write a WRONG version on purpose (forget to flip && to ||).
        //        Which row of the table catches your mistake?
    }
}
