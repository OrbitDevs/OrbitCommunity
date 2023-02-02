package net.orbitdev.orbitcommunity.shared.license;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LicenseChecker {
    private static final String SELECT_LICENSE_SQL = "SELECT * FROM licenses WHERE key = ?";

    public boolean checkLicenseKey(String key) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:licenses.db");
             PreparedStatement statement = connection.prepareStatement(SELECT_LICENSE_SQL)) {
            statement.setString(1, key);
            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
