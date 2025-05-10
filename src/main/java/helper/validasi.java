package helper;

public class validasi {
    public static boolean isValidGmail(String email) {
        return email != null && email.endsWith("@gmail.com");
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber != null && phoneNumber.matches("\\d{11,}");
    }
}
