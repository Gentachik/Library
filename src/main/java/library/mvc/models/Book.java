package library.mvc.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {
    private int id;
    @NotEmpty(message = "Book have to have name.")
    @Size(min = 2, max = 100, message = "Wrong title.")
    private String title;
    @NotEmpty(message = "Book have to have author.")
    @Size(min = 2, max = 100, message = "Wrong author.")
    private String author;
    @Min(value = 0, message = "Wrong year of book.")
    private int year;

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(int year) {
        this.year = year;
    }
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public int getYear() {
        return year;
    }
}
