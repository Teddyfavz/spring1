package com.favcode.favschool.services;

import com.favcode.favschool.controller.ContactController;
import com.favcode.favschool.model.Contact;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

@Slf4j
@Service
//@RequestScope
//@SessionScope
@ApplicationScope
@Data
public class ContactServices {

    private int counter = 0;

    public ContactServices() {
        System.out.println("Contact Service Bean is initialized");
    }

    public boolean saveMessageDetails(Contact contact){
        boolean isSaved = true;
        //TODO - Needs to persist the data into the DB table
        log.info(contact.toString());
        return isSaved;
    }
}
