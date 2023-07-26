package library.mvc.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class Person {
    private int id;
    @NotEmpty(message = "Full name can't ne empty.")
    @Pattern(regexp = "[A-Z]\\w+ [A-Z]\\w+", message = "It's doesn't look like a full name.")
    private String fullName;
    @Min(value = 1900, message = "Year of birth have to be bigger then 1900")
    private int yearOfBirth;
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
}
