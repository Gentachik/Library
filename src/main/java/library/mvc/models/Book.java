package library.mvc.models;

import javax.validation.constraints.NotEmpty;

public class Book {
    private int id;
    @NotEmpty
    private String name;
    @NotEmpty
    private String author;
    @NotEmpty
    private int year;
}
