package lesson34.homework;

import lesson34.homework.dao.*;
import lesson34.homework.models.*;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class MainClass {

    public static void main(String[] args) throws SQLException {

        // Инициализация DAO
        AuthorDAO authorDAO = new AuthorDAO();
        BookDAO bookDAO = new BookDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        UserDAO userDAO = new UserDAO();
        ProfileDAO profileDAO = new ProfileDAO();

        // Тестирование работы с авторами и книгами
        System.out.println("=== ТЕСТИРОВАНИЕ АВТОРОВ И КНИГ ===");

        // Создаем автора
        Author author = new Author();
        author.setName("Лев");
        author.setSurname("Толстой");
        author = authorDAO.save(author);
        System.out.println("Создан автор: " + author.getName() + " " + author.getSurname());

        // Создаем книгу
        Book book = new Book();
        book.setTitle("Война и мир");
        book.setAuthor(author);
        book = bookDAO.save(book);
        System.out.println("Создана книга: " + book.getTitle());

        // Создаем категории
        Category fiction = new Category();
        fiction.setName("Художественная литература");
        fiction = categoryDAO.save(fiction);

        Category classic = new Category();
        classic.setName("Классика");
        classic = categoryDAO.save(classic);

        // Добавляем категории к книге
        bookDAO.addCategoryToBook(book.getId(), fiction.getId());
        bookDAO.addCategoryToBook(book.getId(), classic.getId());
        System.out.println("Добавлены категории к книге");

        // Получаем книгу с категориями
        Optional<Book> bookWithCategories = bookDAO.findByIdWithCategories(book.getId());
        if (bookWithCategories.isPresent()) {
            Book foundBook = bookWithCategories.get();
            System.out.println("Книга: " + foundBook.getTitle());
            System.out.println("Автор: " + foundBook.getAuthor().getName() + " " + foundBook.getAuthor().getSurname());
            System.out.println("Категории:");
            for (Category cat : foundBook.getCategories()) {
                System.out.println("  - " + cat.getName());
            }
        }

        // Тестирование работы с пользователями и профилями
        System.out.println("\n=== ТЕСТИРОВАНИЕ ПОЛЬЗОВАТЕЛЕЙ И ПРОФИЛЕЙ ===");

        // Создаем пользователя
        User user = new User();
        user.setUsername("ivanov");
        user.setEmail("ivanov@example.com");
        user = userDAO.save(user);
        System.out.println("Создан пользователь: " + user.getUsername());

        // Создаем профиль
        Profile profile = new Profile();
        profile.setBio("Люблю читать книги");
        profile.setAvatarUrl("/avatars/ivanov.jpg");
        profile.setUser(user);
        profile = profileDAO.save(profile);
        System.out.println("Создан профиль для пользователя");

        // Получаем профиль с информацией о пользователе
        Optional<Profile> userProfile = profileDAO.findByUserId(user.getId());
        if (userProfile.isPresent()) {
            Profile foundProfile = userProfile.get();
            System.out.println("Профиль пользователя: " + foundProfile.getUser().getUsername());
            System.out.println("Bio: " + foundProfile.getBio());
        }

        // Получаем все книги с категориями
        System.out.println("\n=== ВСЕ КНИГИ С КАТЕГОРИЯМИ ===");
        List<Book> allBooks = bookDAO.findAllWithCategories();
        for (Book b : allBooks) {
            System.out.println("Книга: " + b.getTitle());
            System.out.println("  Автор: " + b.getAuthor().getName() + " " + b.getAuthor().getSurname());
            System.out.println("  Категории: " + b.getCategories().size());
        }

        // Получаем все категории с книгами
        System.out.println("\n=== ВСЕ КАТЕГОРИИ С КНИГАМИ ===");
        List<Category> allCategories = categoryDAO.findAllWithBooks();
        for (Category c : allCategories) {
            System.out.println("Категория: " + c.getName());
            System.out.println("  Книг: " + c.getBooks().size());
        }
    }
}