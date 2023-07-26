package library.mvc.dao;

import library.mvc.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public List<Book> index() {
        return jdbcTemplate.query("SELECT * from Book", new BookMapper());
    }
    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(name, author, year) VALUES (?,?,?)", book.getName(),book.getAuthor(),book.getYear());
    }
    public void update(int id, Book updatedBook) {
        jdbcTemplate.update("UPDATE BOOK SET name=?, author=?, year=? WHERE id=?",updatedBook.getName(),updatedBook.getAuthor(),updatedBook.getYear(),id);
    }
    public Book show(int id) {
        return jdbcTemplate.query("SELECT * FROM BOOK WHERE ID=?",new Object[]{id},new BookMapper()).
                stream().findAny().orElse(null);
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE from Book where id=?", id);
    }

}
