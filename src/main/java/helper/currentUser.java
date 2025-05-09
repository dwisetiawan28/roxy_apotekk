package helper;

public class currentUser {
    private static int id;
    private static String username;

    public static void set(int userId, String userName) {
        id = userId;
        username = userName;
    }

    public static int getId() {
        return id;
    }

    public static String getUsername() {
        return username;
    }

    public static void clear() {
        id = 0;
        username = null;
    }
}
