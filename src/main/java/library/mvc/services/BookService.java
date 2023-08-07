package library.mvc.services;

import library.mvc.models.Book;
import library.mvc.models.Person;
import library.mvc.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    public List<Book> findAll(boolean sortByYear){
        if(sortByYear)
            return bookRepository.findAll(Sort.by("year"));
        else
            return bookRepository.findAll();

    }
    public List<Book> findWithPagination(Integer page, Integer booksPerPage, boolean sortByYear){
        if(sortByYear)
            return bookRepository.findAll(PageRequest.of(page,booksPerPage,Sort.by("year"))).getContent();
        else
            return bookRepository.findAll(PageRequest.of(page,booksPerPage)).getContent();
    }
    public Optional<Person> findOwnerByBookId(int id) {
        return bookRepository.findById(id).map(Book::getOwner);
    }
    public List<Book> findByTitle(String findByTitle){
        return bookRepository.findByTitleStartingWith(findByTitle);
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
