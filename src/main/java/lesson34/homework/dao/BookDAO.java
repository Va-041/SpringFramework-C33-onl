package lesson34.homework.dao;

import lesson34.homework.models.Book;
import lesson34.homework.models.Author;
import lesson34.homework.DatabaseConnection;
import lesson34.homework.models.Category;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookDAO {

    // CREATE
    public Book save(Book book) {
        String sql = "INSERT INTO books (title, author_id) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getAuthor().getId());
            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                book.setId(generatedKeys.getInt(1));
            }

        }  catch (SQLException e) {
            throw new RuntimeException("Error saving book", e);
        }
        return book;
    }

    // READ
    public Optional<Book> findById(int id) {
        String sql = "SELECT b.*, a.name as author_name, a.surname as author_surname " +
                "FROM books b " +
                "LEFT JOIN authors a ON b.author_id = a.id " +
                "WHERE b.id = ?";

        Book book = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));

                Author author = new Author();
                author.setId(resultSet.getInt("author_id"));
                author.setName(resultSet.getString("author_name"));
                author.setSurname(resultSet.getString("author_surname"));
                book.setAuthor(author);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding books by id", e);
        }
        return Optional.ofNullable(book);
    }

    //READ
    public List<Book> findAll() {
        String sql = "SELECT b.*, a.name as author_name, a.surname as author_surname " +
                "FROM books b " +
                "LEFT JOIN authors a ON b.author_id = a.id ";

        List<Book> books = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));

                Author author = new Author();
                author.setId(resultSet.getInt("author_id"));
                author.setName(resultSet.getString("author_name"));
                author.setSurname(resultSet.getString("author_surname"));
                book.setAuthor(author);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error finding all books", e);
        }
        return books;
    }

    // UPDATE
    public Book update(Book book) {
        String sql = "UPDATE books SET title = ?, author_id = ? WHERE id = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getAuthor().getId());
            statement.setInt(3, book.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error updating book", e);
        }
        return book;
    }

    //DELETE
    public boolean delete(int id) {
        String sql = "DELETE FROM books WHERE id = ?";

        try(Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt (1, id);
            int rowAffected = statement.executeUpdate();
            return rowAffected > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting book", e);
        }
    }

    //SEARCH by name
    public List<Book> findByTitle(String title) {
        String sql = "SELECT b.*, a.name as author_name, a.surname as author_surname " +
                "FROM books b " +
                "LEFT JOIN authors a ON b.author_id = a.id " +
                "WHERE b.title ILIKE ?";
        List<Book> books = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, "%" + title.toLowerCase() + "%");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));

                Author author = new Author();
                author.setId(resultSet.getInt("author_id"));
                author.setName(resultSet.getString("author_name"));
                author.setSurname(resultSet.getString("author_surname"));
                book.setAuthor(author);

                books.add(book);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding books by title", e);
        }
        return books;
    }

    //SEARCH books from one author
    public List<Book> findByAuthorId(int id) {
        String sql = "SELECT b.*, a.name as author_name, a.surname as author_surname " +
                "FROM books b " +
                "LEFT JOIN authors a ON b.author_id = a.id " +
                "WHERE b.author_id = ?";
        List<Book> books = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));

                Author author = new Author();
                author.setId(resultSet.getInt("author_id"));
                author.setName(resultSet.getString("author_name"));
                author.setSurname(resultSet.getString("author_surname"));
                book.setAuthor(author);

                books.add(book);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding books by author id", e);
        }
        return books;
    }

    //ADD category to book
    public void addCategoryToBook(int bookId, int categoryId) {
        String sql = "INSERT INTO book_categories (book_id, category_id) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, bookId);
            statement.setInt(2, categoryId);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error adding category", e);
        }
    }

    //DELETE category from book
    public void removeCategoryFromBook(Book book, Category category) {
        String sql = "DELETE FROM book_categories WHERE book_id = ? AND category_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, book.getId());
            statement.setInt(2, category.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error removing category from book", e);
        }
    }

    //READ books with all categories
    public Optional<Book> findByIdWithCategories(int id) {
        String sql = "SELECT b.*, a.name as author_name, a.surname as author_surname, " +
                "c.id as category_id, c.name as category_name " +
                "FROM books b " +
                "LEFT JOIN authors a ON b.author_id = a.id " +
                "LEFT JOIN book_categories bc ON b.id = bc.book_id " +
                "LEFT JOIN categories c ON bc.category_id = c.id " +
                "WHERE b.id = ?";
        Book book = null;

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                if (book == null) {
                    book = new Book();
                    book.setId(resultSet.getInt("id"));
                    book.setTitle(resultSet.getString("title"));

                    Author author = new Author();
                    author.setId(resultSet.getInt("author_id"));
                    author.setName(resultSet.getString("author_name"));
                    author.setSurname(resultSet.getString("author_surname"));
                    book.setAuthor(author);
                }

                // Добавляем категории, если они есть
                int categoryId = resultSet.getInt("category_id");
                if (!resultSet.wasNull()) {
                    Category category = new Category();
                    category.setId(categoryId);
                    category.setName(resultSet.getString("category_name"));
                    book.getCategories().add(category);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding book with categories by id", e);
        }
        return Optional.ofNullable(book);
    }

    //READ all books with categories
    public List<Book> findAllWithCategories() {
        String sql = "SELECT b.*, a.name as author_name, a.surname as author_surname, " +
                "c.id as category_id, c.name as category_name " +
                "FROM books b " +
                "LEFT JOIN authors a ON b.author_id = a.id " +
                "LEFT JOIN book_categories bc ON b.id = bc.book_id " +
                "LEFT JOIN categories c ON bc.category_id = c.id " +
                "ORDER BY b.id";

        List<Book> books = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            Book currentBook = null;
            int currentBookId = -1;

            while (resultSet.next()) {
                int bookId = resultSet.getInt("id");

                // Если это новая книга
                if (currentBook == null || currentBookId != bookId) {
                    if (currentBook != null) {
                        books.add(currentBook);
                    }

                    currentBook = new Book();
                    currentBook.setId(bookId);
                    currentBook.setTitle(resultSet.getString("title"));

                    Author author = new Author();
                    author.setId(resultSet.getInt("author_id"));
                    author.setName(resultSet.getString("author_name"));
                    author.setSurname(resultSet.getString("author_surname"));
                    currentBook.setAuthor(author);

                    currentBookId = bookId;
                }

                // Добавляем категории, если они есть
                int categoryId = resultSet.getInt("category_id");
                if (!resultSet.wasNull()) {
                    Category category = new Category();
                    category.setId(categoryId);
                    category.setName(resultSet.getString("category_name"));
                    currentBook.getCategories().add(category);
                }
            }

            // Добавляем последнюю книгу
            if (currentBook != null) {
                books.add(currentBook);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding all books with categories", e);
        }
        return books;
    }

    //READ books from category
    public List<Book> findByCategoryId(int categoryId) {
        String sql = "SELECT b.*, a.name as author_name, a.surname as author_surname " +
                "FROM books b " +
                "LEFT JOIN authors a ON b.author_id = a.id " +
                "JOIN book_categories bc ON b.id = bc.book_id " +
                "WHERE bc.category_id = ?";
        List<Book> books = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setTitle(resultSet.getString("title"));

                Author author = new Author();
                author.setId(resultSet.getInt("author_id"));
                author.setName(resultSet.getString("author_name"));
                author.setSurname(resultSet.getString("author_surname"));
                book.setAuthor(author);

                books.add(book);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error finding books by category", e);
        }
        return books;
    }
}
