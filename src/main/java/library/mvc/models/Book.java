package library.mvc.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

public class Book {
    private int id;
    private int ownerId;
    @NotEmpty(message = "Book have to have name.")
    private String name;
    @NotEmpty(message = "Book have to have author.")
    private String author;
    @Min(value = 0, message = "Wrong year of book.")
    private int year;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
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
    public String getName() {
        return name;
    }
    public String getAuthor() {
        return author;
    }
    public int getYear() {
        return year;
    }
    public int getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
    public void setOwner(int personId) {
        this.ownerId=personId;
    }
}
