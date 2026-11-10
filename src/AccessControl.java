import com.sun.org.apache.xpath.internal.functions.FuncStringLength;
import sun.security.util.Length;

import java.util.Scanner;

/**
 * Cipher Lab - Milestone 1: The Rules
 *
 * You are building the login gatekeeper for the Granite Peak secure server.
 * Every method below has its header written for you. Your job is to replace
 * each placeholder "return" with working code.
 *
 * Check your work any time by running AccessControlTester.
 *
 * Name: Krow Jennings
 */
public class AccessControl {

    /** The password stored on the server for this simulation. */
    public static final String STORED_PASSWORD = "Gr4n1te!Peak";

    /**
     * 1. A password is a valid length if it has at least 8 characters
     *    AND no more than 64 characters.
     *
     * isValidLength("abc")          -> false
     * isValidLength("password123")  -> true
     */



    public static boolean isValidLength(String password) {
        // TODO - delete the next line and write your code

        if ( (password.length() >= 8) && (password.length() <= 64) ) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 2. Return the access level for a role:
     *    "admin" -> 3, "teacher" -> 2, "student" -> 1, anything else -> 0
     *
     * Hint: compare Strings with .equals(), not ==
     */
    public static int accessLevel(String role) {
        // TODO - delete the next line and write your code
        if (role.equals("admin")) {
            return 3;
        } else if (role.equals("teacher")) {
            return 2;
        } else if (role.equals("student")) {
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 3. A user can log in only if ALL of these are true:
     *    - the password is correct
     *    - they have fewer than 3 failed attempts
     *    - the account is NOT locked
     *
     * Write this as a single return statement.
     */
    public static boolean canLogin(boolean correctPassword, int failedAttempts, boolean accountLocked) {
        // TODO - delete the next line and write your code
        boolean canlogin;
        if (correctPassword == true && failedAttempts < 3 && accountLocked == false ) {
            canlogin = true;
        } else {
            canlogin= false;
        }
        return canlogin;
    }

    /**
     * 4. School hours are 7:00 to 17:59 (hours 7 through 17, 24-hour clock).
     *    Return true if the hour is OUTSIDE school hours.
     *
     * isAfterHours(6)  -> true
     * isAfterHours(7)  -> false
     * isAfterHours(18) -> true
     */
    public static boolean isAfterHours(int hour) {
        // TODO - delete the next line and write your code
        if (hour >= 7 && hour <= 17.59){
            return false;
        } else {
            return true;
        }
    }

    /**
     * 5. Decide what happens when someone tries to log in.
     *    Check the rules IN THIS ORDER and return the first one that applies:
     *
     *    a) Unknown role (access level 0)          -> "UNKNOWN ROLE"
     *    b) 3 or more failed attempts              -> "LOCKED"
     *    c) Wrong password                         -> "DENIED"
     *    d) During school hours                    -> "GRANTED"
     *    e) After hours:
     *         admin                                -> "GRANTED"
     *         teacher, hour 18 through 21          -> "GRANTED"
     *         teacher, any other after-hours time  -> "AFTER HOURS"
     *         student                              -> "AFTER HOURS"
     *
     * You may (and should!) call your other methods inside this one.
     */
    public static String accessDecision(String role, int hour, boolean correctPassword, int failedAttempts) {
        // TODO - delete the next line and write your code
        if (accessLevel(role) == 0) {
            System.out.println("UNKNOWN ROLE");
            return "UNKNOWN ROLE";
        } else {
            if (failedAttempts >= 3) {
                System.out.println("LOCKED");
                return "LOCKED";
            } else {
                if (correctPassword == false){
                    System.out.println("DENIED");
                    return "DENIED";
                } else {
                    if (isAfterHours(hour) == false) {
                        return "GRANTED";
                    } else if (isAfterHours(hour) == true) {
                        if ("admin" == role){
                            System.out.println("GRANTED");
                            return "GRANTED";
                        } else if ("teacher" == role) {
                            if (hour >= 18 && hour <= 21) {
                                System.out.println("GRANTED");
                                return "GRANTED";
                            } else {
                                return "AFTER HOURS";
                            }
                        } else if ("student" == role) {
                            return "AFTER HOURS";
                        }
                    }
                }
            }
        }
        return null;
    }

    /**
     * 6. De Morgan's Laws.
     *    A senior developer wrote this condition for "the user is blocked":
     *
     *        !(failedAttempts < 3 && !accountLocked)
     *
     *    It works, but it is hard to read. Use De Morgan's Laws to rewrite it
     *    WITHOUT a ! in front of the parentheses.
     *
     *    In the comment below, show your work step by step, then return
     *    your simplified version.
     *
     *    Step 1: !(failedAttempts < 3 && !accountLocked)
     *    Step 2: (!failedAttempts < 3 && accountLocked)
     *    Final: (failedAttempts >= 3 || accountLocked)
     */
    public static boolean isBlocked(int failedAttempts, boolean accountLocked) {
        // TODO - delete the next line and write your code
        return (failedAttempts >= 3 || accountLocked);
    }

    /**
     * STRETCH (needed for A-level evidence)
     *    "STRONG" - at least 12 characters AND contains at least one of ! @ #
     *    "MEDIUM" - otherwise, at least 8 characters
     *    "WEAK"   - anything shorter
     *
     * Hint: indexOf from Unit 1 returns -1 when something is not found.
     */
    public static String strengthRating(String password) {
        // TODO (stretch) - delete the next line and write your code
        if (password.length() >= 12 && password.contains("!") || password.contains("@") || password.contains("#")) {
            return "STRONG";
        } else if (password.length() >= 8) {
            return "MEDIUM";
        } else {
            return "WEAK";
        }
    }

    /**
     * The interactive program. Match the sample runs in the milestone page.
     * One login attempt only. Loops come in Milestone 2!
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("=== GRANITE PEAK SECURE SERVER ===");
        System.out.print("Role (admin / teacher / student): ");
        String role = input.nextLine();
        System.out.print("Current hour (0-23): ");
        int hour = input.nextInt();
        System.out.print("Failed attempts so far: ");
        int failed = input.nextInt();
        input.nextLine(); // clear the leftover newline
        System.out.print("Password: ");
        String password = input.nextLine();

        // TODO 1: If the password is not a valid length, print the warning
        //         shown in the sample runs.

        // TODO 2: Decide whether the password is correct (compare it to
        //         STORED_PASSWORD) and get the access decision.

        // TODO 3: Print the access level and the decision.

        // TODO 4: Print the extra message for GRANTED or LOCKED
        //         (see sample runs). Nothing extra for other decisions.

        input.close();
    }
}
