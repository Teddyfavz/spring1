package com.favcode.favschool.controller;

import com.favcode.favschool.model.FavClass;
import com.favcode.favschool.model.Person;
import com.favcode.favschool.repository.FavClassRepository;
import com.favcode.favschool.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    FavClassRepository favClassRepository;

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model){
        List<FavClass> favClasses = favClassRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("classes.html");
        modelAndView.addObject("favClasses", favClasses);
        modelAndView.addObject("favClass", new FavClass());
        return modelAndView;
    }

    @PostMapping("/addNewClass")
    public ModelAndView addNewClass(Model model, @ModelAttribute("favClass") FavClass favClass){
        favClassRepository.save(favClass);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @RequestMapping("/deleteClass")
    public ModelAndView deleteClass (Model model, @RequestParam int id){
        Optional<FavClass> favClass = favClassRepository.findById(id);
        for (Person person : favClass.get().getPersons()){
            person.setFavClass(null);
            personRepository.save(person);
        }
        favClassRepository.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @GetMapping("/displayStudents")
    public ModelAndView displayStudents (Model model, @RequestParam int classId, HttpSession session,
                                         @RequestParam(value = "error", required = false) String error){
        String errorMessage = null;
        ModelAndView modelAndView = new ModelAndView("students.html");
        Optional<FavClass> favClass = favClassRepository.findById(classId);
        modelAndView.addObject("favClass", favClass.get());
        modelAndView.addObject("person", new Person());
        session.setAttribute("favClass", favClass.get());
        if (error != null){
            errorMessage = "Invalid Email entered";
            modelAndView.addObject("errorMessage", errorMessage);
        }
        return modelAndView;
    }

    @PostMapping("/addStudent")
    public ModelAndView addStudent(Model model, @ModelAttribute("person") Person person, HttpSession session){
        ModelAndView modelAndView = new ModelAndView();
        FavClass favClass = (FavClass) session.getAttribute("favClass");
        Person personEntity = personRepository.readByEmail(person.getEmail());
        if (personEntity == null || !(personEntity.getPersonId() > 0)){
            modelAndView.setViewName("redirect:/admin/displayStudents?classId="+favClass.getClassId()+"&error=true");
            return modelAndView;
        }
        personEntity.setFavClass(favClass);
        personRepository.save(personEntity);
        favClass.getPersons().add(personEntity);
        favClassRepository.save(favClass);
        modelAndView.setViewName("redirect:/admin/displayStudents?classId="+favClass.getClassId());
        return modelAndView;
    }

    @GetMapping("/deleteStudent")
    public ModelAndView deleteStudent(Model model, @RequestParam int personId, HttpSession session){
        FavClass favClass = (FavClass) session.getAttribute("favClass");
        Optional<Person> person = personRepository.findById(personId);
        person.get().setFavClass(null);
        favClass.getPersons().remove(person.get());
        FavClass favClassSaved = favClassRepository.save(favClass);
        session.setAttribute("favClass", favClassSaved);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayStudents?classId="
                +favClass.getClassId());
        return  modelAndView;
    }
}
