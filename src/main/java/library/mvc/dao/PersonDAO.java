package library.mvc.dao;

import library.mvc.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(fullName, yearOfBirth) VALUES (?,?)", person.getFullName(),person.getYearOfBirth());
    }
    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM PERSON WHERE ID=?",new Object[]{id},new PersonMapper()).
                stream().findAny().orElse(null);
    }
    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE PERSON fullName=?, yearOfBirth=? WHERE ID=?",updatedPerson.getFullName(),updatedPerson.getYearOfBirth(),id);
    }
    public List<Person> index() {
        return jdbcTemplate.query("SELECT * from person", new PersonMapper());
    }
    public void delete(int id) {
        jdbcTemplate.update("DELETE from Person where id=?", id);
    }
}