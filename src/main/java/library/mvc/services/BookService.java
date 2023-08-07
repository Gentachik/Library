package library.mvc.services;

import library.mvc.models.Book;
import library.mvc.models.Person;
import library.mvc.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final BookRepository bookRepository;
    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    public Book findOne(int id) {
        Optional<Book> foundBook = bookRepository.findById(id);
        return  foundBook.orElse(null);
    }
    public List<Book> findAll(){
        return bookRepository.findAll();
    }
    public Optional<Person> findOwnerByBookId(int id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.map(Book::getOwner);
    }
    @Transactional
    public void update(int id, Book book) {
        book.setId(id);
        bookRepository.save(book);
    }
    @Transactional
    public void save(Book book) {
        bookRepository.save(book);
    }
    @Transactional
    public void release(int id) {
         bookRepository.findById(id).ifPresent(
                book -> {
                    book.setOwner(null);
                    book.setTakenAt(null);
                }
        );
    }
    @Transactional
    public void assign(int id, Person selectedPerson) {
        bookRepository.findById(id).ifPresent(
                book -> {
                    book.setOwner(selectedPerson);
                    book.setTakenAt(new Date());
                }
        );
    }
    @Transactional
    public void delete(int id){
        bookRepository.deleteById(id);
    }
}
