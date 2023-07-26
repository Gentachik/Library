package library.mvc.controllers;

import library.mvc.dao.BookDAO;
import library.mvc.models.Book;
import library.mvc.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    @Autowired
    public BooksController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }
    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }
    @GetMapping("/new")
    public String newBook(@ModelAttribute("book")Book book){
        return "books/new";
    }
    @GetMapping("/{id}/edit")
    public String edit( Model model, @PathVariable("id") int id){
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        bookDAO.delete(id);
        return "redirect:/books";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult, @PathVariable("id") int id) {
        if(bindingResult.hasErrors())
            return "books/edit";

        bookDAO.update(id, book);
        return "redirect:/books";
    }
    @PostMapping()
    public String create(@ModelAttribute("book") @Valid Book book, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return "books/new";
        bookDAO.save(book);
        return "redirect:/books";
    }
}
