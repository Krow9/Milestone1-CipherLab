import java.util.Objects;
import java.util.function.Supplier;

/**
 * Cipher Lab - Milestone 1 Tester
 *
 * Run this file (green arrow next to main) to check your AccessControl methods.
 * You do NOT need to change anything in this file.
 *
 * PASS = your method gave the expected answer.
 * FAIL = read the "expected" vs "got" line, then check the notes listed
 *        in that section's header.
 */
public class AccessControlTester {

    private static int corePassed = 0;
    private static int coreTotal = 0;
    private static int stretchPassed = 0;
    private static int stretchTotal = 0;
    private static boolean stretchMode = false;

    public static void main(String[] args) {
        System.out.println("=========================================");
        System.out.println(" CIPHER LAB - MILESTONE 1 TESTER");
        System.out.println("=========================================");

        section("1. isValidLength", "CED 2.2 + 2.5 - Lesson 1 & 3 notes");
        check("isValidLength(\"abc\")", () -> AccessControl.isValidLength("abc"), false);
        check("isValidLength(\"1234567\")  [7 chars]", () -> AccessControl.isValidLength("1234567"), false);
        check("isValidLength(\"12345678\") [8 chars]", () -> AccessControl.isValidLength("12345678"), true);
        check("isValidLength(\"password123\")", () -> AccessControl.isValidLength("password123"), true);
        check("isValidLength(64 chars)", () -> AccessControl.isValidLength(repeat("a", 64)), true);
        check("isValidLength(65 chars)", () -> AccessControl.isValidLength(repeat("a", 65)), false);

        section("2. accessLevel", "CED 2.3 - Lesson 1 notes");
        check("accessLevel(\"admin\")", () -> AccessControl.accessLevel("admin"), 3);
        check("accessLevel(\"teacher\")", () -> AccessControl.accessLevel("teacher"), 2);
        check("accessLevel(\"student\")", () -> AccessControl.accessLevel("student"), 1);
        check("accessLevel(\"hacker\")", () -> AccessControl.accessLevel("hacker"), 0);
        check("accessLevel(\"Admin\")  [capital A]", () -> AccessControl.accessLevel("Admin"), 0);

        section("3. canLogin", "CED 2.5 - Lesson 3 notes");
        check("canLogin(true, 0, false)", () -> AccessControl.canLogin(true, 0, false), true);
        check("canLogin(true, 2, false)", () -> AccessControl.canLogin(true, 2, false), true);
        check("canLogin(true, 3, false)", () -> AccessControl.canLogin(true, 3, false), false);
        check("canLogin(false, 0, false)", () -> AccessControl.canLogin(false, 0, false), false);
        check("canLogin(true, 0, true)", () -> AccessControl.canLogin(true, 0, true), false);

        section("4. isAfterHours", "CED 2.5 - Lesson 3 notes");
        check("isAfterHours(0)", () -> AccessControl.isAfterHours(0), true);
        check("isAfterHours(6)", () -> AccessControl.isAfterHours(6), true);
        check("isAfterHours(7)", () -> AccessControl.isAfterHours(7), false);
        check("isAfterHours(12)", () -> AccessControl.isAfterHours(12), false);
        check("isAfterHours(17)", () -> AccessControl.isAfterHours(17), false);
        check("isAfterHours(18)", () -> AccessControl.isAfterHours(18), true);
        check("isAfterHours(23)", () -> AccessControl.isAfterHours(23), true);

        section("5. accessDecision", "CED 2.4 - Lesson 2 notes (nested if)");
        check("accessDecision(\"guest\", 10, true, 0)", () -> AccessControl.accessDecision("guest", 10, true, 0), "UNKNOWN ROLE");
        check("accessDecision(\"student\", 10, true, 3)", () -> AccessControl.accessDecision("student", 10, true, 3), "LOCKED");
        check("accessDecision(\"admin\", 10, false, 5)  [lock beats wrong pw]", () -> AccessControl.accessDecision("admin", 10, false, 5), "LOCKED");
        check("accessDecision(\"student\", 10, false, 0)", () -> AccessControl.accessDecision("student", 10, false, 0), "DENIED");
        check("accessDecision(\"student\", 10, true, 0)", () -> AccessControl.accessDecision("student", 10, true, 0), "GRANTED");
        check("accessDecision(\"student\", 19, true, 0)", () -> AccessControl.accessDecision("student", 19, true, 0), "AFTER HOURS");
        check("accessDecision(\"teacher\", 19, true, 0)", () -> AccessControl.accessDecision("teacher", 19, true, 0), "GRANTED");
        check("accessDecision(\"teacher\", 21, true, 0)", () -> AccessControl.accessDecision("teacher", 21, true, 0), "GRANTED");
        check("accessDecision(\"teacher\", 22, true, 0)", () -> AccessControl.accessDecision("teacher", 22, true, 0), "AFTER HOURS");
        check("accessDecision(\"teacher\", 5, true, 0)", () -> AccessControl.accessDecision("teacher", 5, true, 0), "AFTER HOURS");
        check("accessDecision(\"admin\", 3, true, 0)", () -> AccessControl.accessDecision("admin", 3, true, 0), "GRANTED");
        check("accessDecision(\"admin\", 3, false, 1)", () -> AccessControl.accessDecision("admin", 3, false, 1), "DENIED");

        section("6. isBlocked (De Morgan)", "CED 2.6 - Lesson 3 notes");
        check("isBlocked(0, false)", () -> AccessControl.isBlocked(0, false), false);
        check("isBlocked(2, false)", () -> AccessControl.isBlocked(2, false), false);
        check("isBlocked(3, false)", () -> AccessControl.isBlocked(3, false), true);
        check("isBlocked(0, true)", () -> AccessControl.isBlocked(0, true), true);
        check("isBlocked(5, true)", () -> AccessControl.isBlocked(5, true), true);
        System.out.println("   Reminder: the tester checks your ANSWERS, not your De Morgan");
        System.out.println("   steps. Your teacher grades the steps in your comment.");

        stretchMode = true;
        section("STRETCH: strengthRating", "Unit 1 String methods + CED 2.3/2.5");
        check("strengthRating(\"abc\")", () -> AccessControl.strengthRating("abc"), "WEAK");
        check("strengthRating(\"password\")", () -> AccessControl.strengthRating("password"), "MEDIUM");
        check("strengthRating(\"longpassword123\")  [no symbol]", () -> AccessControl.strengthRating("longpassword123"), "MEDIUM");
        check("strengthRating(\"short!\")  [symbol, too short]", () -> AccessControl.strengthRating("short!"), "WEAK");
        check("strengthRating(\"Gr4n1te!Peak\")", () -> AccessControl.strengthRating("Gr4n1te!Peak"), "STRONG");
        check("strengthRating(\"correct#horse\")", () -> AccessControl.strengthRating("correct#horse"), "STRONG");
        check("strengthRating(\"me@granitepeak\")", () -> AccessControl.strengthRating("me@granitepeak"), "STRONG");

        System.out.println();
        System.out.println("=========================================");
        System.out.println(" CORE:    " + corePassed + " / " + coreTotal + " passed");
        System.out.println(" STRETCH: " + stretchPassed + " / " + stretchTotal + " passed");
        if (corePassed == coreTotal) {
            System.out.println(" All core tests pass. Next: match the sample runs");
            System.out.println(" for main, then take the Quiz 1 gate.");
        } else {
            System.out.println(" Keep going! Fix the FAILs from the top down.");
        }
        System.out.println("=========================================");
    }

    // ----- helpers (you don't need to read below this line) -----

    private static void section(String name, String help) {
        System.out.println();
        System.out.println("-- " + name + "   (stuck? " + help + ")");
    }

    private static void check(String label, Supplier<Object> actual, Object expected) {
        Object result;
        try {
            result = actual.get();
        } catch (UnsupportedOperationException e) {
            result = "NOT STARTED YET";
        } catch (Exception e) {
            result = "CRASHED: " + e.getClass().getSimpleName();
        }
        boolean ok = Objects.equals(result, expected);
        if (stretchMode) {
            stretchTotal++;
            if (ok) stretchPassed++;
        } else {
            coreTotal++;
            if (ok) corePassed++;
        }
        if (ok) {
            System.out.println("   [PASS] " + label + " -> " + show(result));
        } else {
            System.out.println("   [FAIL] " + label);
            System.out.println("          expected: " + show(expected) + "   got: " + show(result));
        }
    }

    private static String show(Object o) {
        if (o instanceof String && !((String) o).startsWith("CRASHED") && !o.equals("NOT STARTED YET")) {
            return "\"" + o + "\"";
        }
        return String.valueOf(o);
    }

    private static String repeat(String s, int n) {
        String out = "";
        for (int i = 0; i < n; i++) {
            out += s;
        }
        return out;
    }
}
