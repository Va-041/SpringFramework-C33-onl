package lesson34.homework.dao;

import lesson34.homework.DatabaseConnection;
import lesson34.homework.models.Author;
import lesson34.homework.models.Book;
import lesson34.homework.models.Category;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDAO {

    // CREATE
    public Category save(Category category) {
        String sql = "INSERT INTO categories (name) VALUES (?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, category.getName());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                category.setId(generatedKeys.getInt(1));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error saving category", e);
        }
        return category;
    }

    // READ
    public Optional<Category> findById(int id) {
        String sql = "SELECT * FROM categories WHERE id = ?";
        Category category = null;

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding category by id",e);
        }
        return Optional.ofNullable(category);
    }

    // READ all
    public List<Category> findAll() {
        String sql = "SELECT * FROM categories";
        List<Category> categories = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                categories.add(category);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding categories",e);
        }
        return categories;
    }

    // UPDATE
    public Category update(Category category) {
        String sql = "UPDATE categories SET name=? WHERE id=?";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, category.getName());
            statement.setInt(2, category.getId());
            statement.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException("Error updating book category",e);
        }
        return category;
    }

    // DELETE
    public boolean delete(int id) {
        String sql = "DELETE FROM categories WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            int rowAffected = statement.executeUpdate();
            return rowAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting book category",e);
        }
    }

    // SEARCH by name
    public List<Category> findByName(String name) {
        String sql = "SELECT * FROM categories WHERE name = ?";
        List<Category> categories = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + name + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt("id"));
                category.setName(resultSet.getString("name"));
                categories.add(category);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding categories",e);
        }
        return categories;
    }

    // READ category with all books
    public Optional<Category> findByIdWithBooks(int id) {
        String sql = "SELECT c.*, b.id as book_id, b.title as book_title, " +
                "a.id as author_id, a.name as author_name, a.surname as author_surname " +
                "FROM categories c " +
                "LEFT JOIN book_categories bc ON c.id = bc.category_id " +
                "LEFT JOIN books b ON bc.book_id = b.id " +
                "LEFT JOIN authors a ON b.author_id = a.id " +
                "WHERE c.id = ?";
        Category category = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                if (category == null) {
                    category = new Category();
                    category.setId(resultSet.getInt("id"));
                    category.setName(resultSet.getString("name"));
                }

                // Добавляем книги, если они есть
                int bookId = resultSet.getInt("book_id");
                if (!resultSet.wasNull()) {
                    Book book = new Book();
                    book.setId(bookId);
                    book.setTitle(resultSet.getString("book_title"));

                    Author author = new Author();
                    author.setId(resultSet.getInt("author_id"));
                    author.setName(resultSet.getString("author_name"));
                    author.setSurname(resultSet.getString("author_surname"));
                    book.setAuthor(author);

                    category.getBooks().add(book);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding category with books by id", e);
        }
        return Optional.ofNullable(category);
    }

    //READ all categories with books
    public List<Category> findAllWithBooks() {
        String sql = "SELECT c.*, b.id as book_id, b.title as book_title, " +
                "a.id as author_id, a.name as author_name, a.surname as author_surname " +
                "FROM categories c " +
                "LEFT JOIN book_categories bc ON c.id = bc.category_id " +
                "LEFT JOIN books b ON bc.book_id = b.id " +
                "LEFT JOIN authors a ON b.author_id = a.id " +
                "ORDER BY c.id";

        List<Category> categories = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            Category currentCategory = null;
            int currentCategoryId = -1;

            while (resultSet.next()) {
                int categoryId = resultSet.getInt("id");

                // Если это новая категория
                if (currentCategory == null || currentCategoryId != categoryId) {
                    if (currentCategory != null) {
                        categories.add(currentCategory);
                    }

                    currentCategory = new Category();
                    currentCategory.setId(categoryId);
                    currentCategory.setName(resultSet.getString("name"));

                    currentCategoryId = categoryId;
                }

                // Добавляем книги, если они есть
                int bookId = resultSet.getInt("book_id");
                if (!resultSet.wasNull()) {
                    Book book = new Book();
                    book.setId(bookId);
                    book.setTitle(resultSet.getString("book_title"));

                    Author author = new Author();
                    author.setId(resultSet.getInt("author_id"));
                    author.setName(resultSet.getString("author_name"));
                    author.setSurname(resultSet.getString("author_surname"));
                    book.setAuthor(author);

                    currentCategory.getBooks().add(book);
                }
            }

            // Добавляем последнюю категорию
            if (currentCategory != null) {
                categories.add(currentCategory);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding all categories with books", e);
        }
        return categories;
    }
}
