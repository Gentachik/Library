package library.mvc.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Full name can't ne empty.")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "It's doesn't look like a full name.")
    @Size(min = 2, max = 100, message = "Wrong full name.")
    @Column(name = "fullname")
    private String fullName;
    @Min(value = 1900, message = "Year of birth have to be bigger then 1900")
    @Column(name = "yearofbirth")
    private int yearOfBirth;
    @OneToMany(mappedBy = "owner")
    private List<Book> books;
    public Person(){

    }
    public Person(String fullName, int yearOfBirth, List<Book> books) {
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
        this.books=books;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
    public String getFullName() {
        return fullName;
    }
    public int getYearOfBirth() {
        return yearOfBirth;
    }
    public List<Book> getBooks() {
        return books;
    }
    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
