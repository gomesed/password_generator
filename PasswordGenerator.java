import java.security.SecureRandom;
import java.util.Random;

public class PasswordGenerator {
    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789";
    private static final String PUNCTUATION = "!@#$%&*()_+-=[]|,./?><";

    private static final String ALL = UPPER + LOWER + DIGITS + PUNCTUATION;

    private static final Random RANDOM = new SecureRandom();

    public static void main(String[] args) {
        int length = 8;
        String password = generatePassword(length);
        System.out.println("Generated password: " + password);
    }

    public static String generatePassword(int length) {
        if (length < 4) {
            throw new IllegalArgumentException("Password length must be at least 4 characters");
        }

        StringBuilder passwordBuilder = new StringBuilder(length);
        passwordBuilder.append(getRandomChar(UPPER));
        passwordBuilder.append(getRandomChar(LOWER));
        passwordBuilder.append(getRandomChar(DIGITS));
        passwordBuilder.append(getRandomChar(PUNCTUATION));

        for (int i = 0; i < length - 4; i++) {
            passwordBuilder.append(getRandomChar(ALL));
        }

        String password = passwordBuilder.toString();
        char[] passwordChars = password.toCharArray();
        for (int i = 0; i < passwordChars.length; i++) {
            int j = RANDOM.nextInt(passwordChars.length);
            char temp = passwordChars[i];
            passwordChars[i] = passwordChars[j];
            passwordChars[j] = temp;
        }
        return new String(passwordChars);
    }

    private static char getRandomChar(String charset) {
        int index = RANDOM.nextInt(charset.length());
        return charset.charAt(index);
    }
}
