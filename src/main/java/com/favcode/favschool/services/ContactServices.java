package com.favcode.favschool.services;

import com.favcode.favschool.constants.FavSchoolConstants;
import com.favcode.favschool.controller.ContactController;
import com.favcode.favschool.model.Contact;
import com.favcode.favschool.repository.ContactRepository;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDateTime;

@Slf4j
@Service
public class ContactServices {

    @Autowired
    private ContactRepository contactRepository;
    public ContactServices() {
        System.out.println("Contact Service Bean is initialized");
    }
    /**
     * Save Contact Details into DB
     * @param contact
     * @return boolean
     */
    public boolean saveMessageDetails(Contact contact){
        boolean isSaved = false;
        contact.setStatus(FavSchoolConstants.OPEN);
        contact.setCreatedBy(FavSchoolConstants.ANONYMOUS);
        contact.setCreatedAt(LocalDateTime.now());
        int result = contactRepository.saveContactMsg(contact);
        if (result > 0) {
            isSaved = true;
        }
        return isSaved;
    }
}
