package net.orbitdev.orbitcommunity.shared.license;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class License {
    private static final String INSERT_LICENSE_SQL = "INSERT INTO licenses (key) VALUES (?)";

    public void storeLicenseKey(String key) {
        try (Connection connection = DriverManager.getConnection("jdbc:sqlite:licenses.db");
             PreparedStatement statement = connection.prepareStatement(INSERT_LICENSE_SQL)) {
            statement.setString(1, key);
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
