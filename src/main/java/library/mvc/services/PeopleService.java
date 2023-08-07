package library.mvc.services;

import library.mvc.models.Book;
import library.mvc.models.Person;
import library.mvc.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;
    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }
    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return  foundPerson.orElse(null);
    }
    public List<Person> findAll(){
        return peopleRepository.findAll();
    }
    public Optional<Person> findPersonByFullName(String fullName) {
        return peopleRepository.findPersonByFullName(fullName);
    }
    public List<Book> findBooksOfPerson(int id){
        Optional<Person> person= peopleRepository.findById(id);
        if(person.isPresent()){
            Hibernate.initialize(person.get().getBooks());

            return person.get().getBooks();
        }else
            return Collections.emptyList();
    }
    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }
    @Transactional
    public void update(int id, Person person) {
        person.setId(id);
        peopleRepository.save(person);
    }
    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }
}
