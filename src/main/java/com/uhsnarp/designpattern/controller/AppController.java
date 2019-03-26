package com.uhsnarp.designpattern.controller;

import com.uhsnarp.designpattern.builder.Contact;
import com.uhsnarp.designpattern.builder.ContactBuilder;
import com.uhsnarp.designpattern.factory.Pet;
import com.uhsnarp.designpattern.factory.PetFactory;
import com.uhsnarp.designpattern.repository.PresidentEntity;
import com.uhsnarp.designpattern.repository.PresidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class AppController {

    @Autowired
    private PetFactory petFactory;

    @GetMapping
    public String getDefault() {
        return "{\"message\": \"Hello World\"}";
    }

    @PostMapping("adoptPet/{type}/{name}")
    public Pet adoptPet(@PathVariable String type, @PathVariable String name) {
        Pet pet = this.petFactory.createPet(type);
        pet.setName(name);
        pet.feed();
        return pet;
    }

    @GetMapping("presidents")
    public List<Contact> getPresidents() {
        List<Contact> contacts = new ArrayList<>();

        Contact contact = new Contact();
        contact.setFirstName("George");
        contact.setLastName("Washington");
        contacts.add(contact);

        contacts.add(new Contact("John", "Adams", null));

        contacts.add(new ContactBuilder().setFirstName("Thomas").setLastName("Jefferson").buildContact());

        return contacts;
    }

    @Autowired
    PresidentRepository presidentRepository;

    @GetMapping("presidents/{id}")
    public PresidentEntity getPresById(@PathVariable Long id) {
        return this.presidentRepository.findById(id).get();
    }

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("presidentContact/{id}")
    public Contact getPresContById(@PathVariable Long id) {
        PresidentEntity entity = this.restTemplate
                .getForEntity("http://localhost:8080/presidents/{id}",
                        PresidentEntity.class,
                        id).getBody();
        return (new ContactBuilder()
                .setFirstName(entity.getFirstName())
                .setLastName(entity.getLastName())
                .setEmailAddress(entity.getEmailAddress())
                .buildContact());
    }

}
