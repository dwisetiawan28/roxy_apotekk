import java.sql.Connection;
import config.DatabaseConfig;

public class Main {
    public static void main(String[] args) {
        Connection conn = DatabaseConfig.connect();
        if (conn != null) {
            System.out.println("Koneksi ke database berhasil!");
        } else {
            System.out.println("Gagal terkoneksi ke database.");
        }
    }
}
