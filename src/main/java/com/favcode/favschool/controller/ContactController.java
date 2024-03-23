package com.favcode.favschool.controller;

import com.favcode.favschool.model.Contact;
import com.favcode.favschool.services.ContactServices;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import static org.springframework.web.bind.annotation.RequestMethod.POST;
@Slf4j
@Controller
public class ContactController {
    private final ContactServices contactServices;

    @Autowired
    public ContactController(ContactServices contactServices) {
        this.contactServices = contactServices;
    }

    @RequestMapping(value = "/contact")
    public String displayHomePage(Model model){
        model.addAttribute("contact", new Contact());
        return "contact.html";
    }

    /*@PostMapping(value = "/saveMsg")
    @RequestMapping(value = "/saveMsg", method = POST)
    public ModelAndView saveMessage(@RequestParam String name, @RequestParam String mobileNum,@RequestParam String email,
                                    @RequestParam String subject, @RequestParam String message){
        log.info("Name : " + name);
        log.info("Mobile Number : " + mobileNum);
        log.info("Email : " + email);
        log.info("Subject : " + subject);
        log.info("Message : " + message);
        return new ModelAndView("redirect:/Contact");
    }*/
    @RequestMapping(value = "/saveMsg", method = POST)
    public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors){
        if (errors.hasErrors()){
            log.error("Contact form validation failed due to : " + errors.toString());
            return "contact.html";
        }
        contactServices.saveMessageDetails(contact);
        contactServices.setCounter(contactServices.getCounter()+1);
        log.info("Number of times the Contact form is submitted: " + contactServices.getCounter());
        return "redirect:/contact";
    }
}
