package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.domain.Contact;
import com.mycompany.myapp.domain.Person;
import com.mycompany.myapp.repository.ContactRepository;
import com.mycompany.myapp.repository.PersonRepository;
import com.mycompany.myapp.repository.search.ContactSearchRepository;
import com.mycompany.myapp.repository.search.PersonSearchRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@Transactional
public class TestController {

    private PersonRepository personRepository;
    private PersonSearchRepository personSearchRepository;
    private ContactRepository contactRepository;
    private ContactSearchRepository contactSearchRepository;

    public TestController(
        PersonRepository personRepository,
        PersonSearchRepository personSearchRepository,
        ContactRepository contactRepository,
        ContactSearchRepository contactSearchRepository
    ) {
        this.personRepository = personRepository;
        this.personSearchRepository = personSearchRepository;
        this.contactRepository = contactRepository;
        this.contactSearchRepository = contactSearchRepository;
    }

    @PostMapping("/1")
    public Person createTet() {
        var person = new Person();
        person.setName("Johne");
        personRepository.save(person);

        var contact = new Contact();
        contact.setValue("fake@gmail.com");
        person.addContact(contact);
        contactRepository.save(contact);

        // here
        personSearchRepository.save(person);

        return person;
    }
}
