package lesson34.homework.dao;

import lesson34.homework.models.Profile;
import lesson34.homework.models.User;
import lesson34.homework.DatabaseConnection;
import java.sql.*;
import java.util.Optional;

public class ProfileDAO {

    // CREATE
    public Profile save(Profile profile) {
        String sql = "INSERT INTO profiles (bio, avatar_url, user_id) VALUES (?,?,?)";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            statement.setString(1, profile.getBio());
            statement.setString(2, profile.getAvatarUrl());
            statement.setInt(3, profile.getUser().getId());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                profile.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error saving profile",e);
        }
        return profile;
    }

    // READ
    public Optional<Profile> findById(int id) {
        String sql = "SELECT p.*, u.username, u.email " +
                "FROM profiles p " +
                "LEFT JOIN users u ON p.user_id = u.id " +
                "WHERE p.id = ?";
        Profile profile = null;

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                profile = new Profile();
                profile.setId(resultSet.getInt("id"));
                profile.setBio(resultSet.getString("bio"));
                profile.setAvatarUrl(resultSet.getString("avatar_url"));

                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                profile.setUser(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding profile by id",e);
        }
        return Optional.ofNullable(profile);
    }

    // READ by user id
    public Optional<Profile>findByUserId(int userId) {
        String sql = "SELECT p.*, u.username, u.email " +
                "FROM profiles p " +
                "LEFT JOIN users u ON p.user_id = u.id " +
                "WHERE p.user_id = ?";
        Profile profile = null;

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                profile = new Profile();
                profile.setId(resultSet.getInt("id"));
                profile.setBio(resultSet.getString("bio"));
                profile.setAvatarUrl(resultSet.getString("avatar_url"));

                User user = new User();
                user.setId(resultSet.getInt("user_id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                profile.setUser(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding profile by id",e);
        }
        return Optional.ofNullable(profile);
    }

    // UPDATE
    public Profile update(Profile profile) {
        String sql = "UPDATE profiles SET bio = ?, avatar_url = ?, user_id = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, profile.getBio());
            statement.setString(2, profile.getAvatarUrl());
            statement.setInt(3, profile.getUser().getId());
            statement.setInt(4, profile.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating profile",e);
        }
        return profile;
    }

    // DELETE
    public boolean delete(int id) {
        String sql = "DELETE FROM profiles WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting profile by id",e);
        }
    }

    // DELETE by user id
    public boolean deleteByUserId(int userId) {
        String sql = "DELETE FROM profiles WHERE user_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting profile by user id", e);
        }
    }
}
