package lesson34.homework.dao;

import lesson34.homework.models.Author;
import lesson34.homework.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AuthorDAO {

    // CREATE new author
    public Author save(Author author) {
        String sql = "INSERT INTO authors (name, surname) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, author.getName());
            statement.setString(2, author.getSurname());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                author.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error saving author",e);
        }
        return author;
    }

    // READ
    public Author findById(int id) {
        String sql = "SELECT * FROM authors WHERE id = ?";
        Author author = null;

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if(resultSet.next()) {
                author = new Author();
                author.setId(resultSet.getInt("id"));
                author.setName(resultSet.getString("name"));
                author.setSurname(resultSet.getString("surname"));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding author by id",e);
        }
        return author;
    }

    // READ
    public List<Author> findAll() {
        String sql = "SELECT * FROM authors";

        List<Author> authors = new ArrayList<Author>();

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {

            while(resultSet.next()) {
                Author author = new Author();

                author.setId(resultSet.getInt("id"));
                author.setName(resultSet.getString("name"));
                author.setSurname(resultSet.getString("surname"));
                authors.add(author);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding all authors",e);
        }
        return authors;
    }

    // UPDATE
    public Author update(Author author) {
        String sql = "UPDATE authors SET name = ?, surname = ? WHERE id = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, author.getName());
            statement.setString(2, author.getSurname());
            statement.setInt(3, author.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error updating author",e);
        }
        return author;
    }

    // DELETE
    public boolean delete(int id) {
        String sql = "DELETE FROM authors WHERE id = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting author",e);
        }
    }

    // Поиск по имени
    public List<Author> findByName(String name) {
        String sql = "SELECT * FROM authors WHERE name = ?";
        List<Author> authors = new ArrayList<>();

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();

            while(resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getInt("id"));
                author.setName(resultSet.getString("name"));
                author.setSurname(resultSet.getString("surname"));
                authors.add(author);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding authors by name",e);
        }
        return authors;
    }
}
