import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AdminCredentials {
    public static boolean checkAdminCredentials(String username, String password) {
        File credentialsFile = new File("AdminCredentials.txt");
        if (!credentialsFile.isFile()) {
            return false;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(credentialsFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\t", 2);
                if (parts.length == 2
                        && username.equals(parts[0])
                        && password.equals(parts[1])) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
