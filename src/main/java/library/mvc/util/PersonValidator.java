package library.mvc.util;

import library.mvc.dao.PersonDAO;
import library.mvc.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;
    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Person person=(Person) o;

        if(personDAO.getPersonByFullName(person.getFullName()).isPresent()){
            errors.rejectValue("fullName","","We already have person with this full name");
        }
    }
}
