package lesson34.homework.models;

import java.util.ArrayList;
import java.util.List;

public class Book {

    private int id;
    private String title;

    // Ссылка на автора ("Многие-к-одному")
    private Author author;

    // Список категорий этой книги
    private List<Category> categories = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }


}
