public class OtherMethods {
    public void checkPasswordMatch(String pw, String cpw) throws PasswordMismatchException {
        if (!pw.equals(cpw)) {
            throw new PasswordMismatchException("Passwords do not match");
        }
    }
}
