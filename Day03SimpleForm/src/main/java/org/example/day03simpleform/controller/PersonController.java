package org.example.day03simpleform.controller;

import jakarta.validation.Valid;
import org.example.day03simpleform.domain.Person;
import org.example.day03simpleform.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    // Shows the main page with a list of all people
    @GetMapping({"/", "/index"})
    public String showPersonList(Model model) {
        List<Person> personList = personRepository.findAll();
        model.addAttribute("personList", personList);
        return "list-person";
    }

    // Shows the empty form for adding a new person
    @GetMapping("/addPersonForm")
    public String showAddPersonForm(Model model) {
        model.addAttribute("person", new Person());
        return "add-update-form";
    }

    // Shows the form pre-filled with data for updating an existing person
    @GetMapping("/showUpdateForm")
    public String showUpdateForm(@RequestParam Long id, Model model) {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid person Id:" + id));
        model.addAttribute("person", person);
        // FIX: Return the name of the form template
        return "add-update-form";
    }

    // COMBINED METHOD: Handles both adding and updating a person
    @PostMapping("/savePerson")
    public String savePerson(@Valid @ModelAttribute("person") Person person, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-update-form";
        }
        personRepository.save(person);
        return "redirect:/index";
    }

    // Deletes a person by their ID
    @GetMapping("/deletePerson")
    public String deletePerson(@RequestParam Long id) {
        personRepository.deleteById(id);
        return "redirect:/index";
    }
}