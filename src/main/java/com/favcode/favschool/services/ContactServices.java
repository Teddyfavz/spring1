package com.favcode.favschool.services;

import com.favcode.favschool.constants.FavSchoolConstants;
import com.favcode.favschool.model.Contact;
import com.favcode.favschool.repository.ContactRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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

    public List<Contact> findMsgsWithOpenStatus() {
        List<Contact> contactMsgs = contactRepository.findMsgsWithStatus(FavSchoolConstants.OPEN);
        return contactMsgs;
    }

    public boolean updateMsgStatus(int contactId, String updatedBy) {
        boolean isUpdated = false;
        int result = contactRepository.updateMsgStatus(contactId,FavSchoolConstants.CLOSE,updatedBy);
        if (result>0){
            isUpdated = true;
        }
        return isUpdated;
    }
}
