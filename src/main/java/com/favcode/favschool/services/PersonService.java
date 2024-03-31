package com.favcode.favschool.services;


import com.favcode.favschool.constants.FavSchoolConstants;
import com.favcode.favschool.model.Person;
import com.favcode.favschool.model.Roles;
import com.favcode.favschool.repository.PersonRepository;
import com.favcode.favschool.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean createNewPerson(Person person){
        boolean isSaved = false;
        Roles role = roleRepository.getByRoleName(FavSchoolConstants.STUDENT_ROLE);
        person.setRoles(role);
        person.setPwd(passwordEncoder.encode(person.getPwd()));
        person = personRepository.save(person);
        if (null != person && person.getPersonId() > 0)
            isSaved = true;

        return isSaved;
    }
}
