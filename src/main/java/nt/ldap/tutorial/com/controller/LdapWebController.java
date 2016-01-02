package nt.ldap.tutorial.com.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import nt.ldap.tutorial.com.entities.Person;
import nt.ldap.tutorial.com.service.LdapService;

@Controller
public class LdapWebController {

    @Autowired
    private LdapService ldapService;

    @RequestMapping("/index")
    public ModelAndView requestListPage() {
        ModelAndView mv = new ModelAndView("listPerson");
        List<Person> listPerson = ldapService.listPerson();
        mv.addObject("allPerson", listPerson);
        return mv;
    }

    @RequestMapping("/login")
    public ModelAndView requestLoginPage() {
        return new ModelAndView("login");
    }

    @RequestMapping("/loginHanler")
    public ModelAndView loginHandler(@RequestParam("userName") String userName, @RequestParam("password") String password) {
        boolean testLogin = ldapService.authenticate(userName, password);
        System.out.print(testLogin);
        return new ModelAndView("login");
    }

    @RequestMapping("/editPerson")
    public ModelAndView requestEditPage(@RequestParam("fullName") String fullName) {
        ModelAndView mv = new ModelAndView("showPerson");
        Person person = ldapService.findByPrimaryKey(fullName);
        if (person != null) {
            mv.addObject("country", person.getCountry());
            mv.addObject("company", person.getCompany());
            mv.addObject("fullName", person.getFullName());
            mv.addObject("phone", person.getPhone());
            mv.addObject("description", person.getDescription());
        }
        return mv;
    }

    @RequestMapping("/savePerson")
    public String savePerson(@RequestParam("fullName") String fullName, @RequestParam("oldFullName") String oldFullName, @RequestParam("phone") String phone,
            @RequestParam("description") String description, HttpServletRequest request) {
        ldapService.savePerson(fullName, oldFullName, phone, description);
        return "redirect:/index.html";
    }

    @RequestMapping("/addPerson")
    public ModelAndView requestAddPage() {
        return new ModelAndView("showPerson");
    }

    @RequestMapping("/removePerson")
    public String requestRemovePerson(@RequestParam("fullName") String fullName) {
        ldapService.removePerson(fullName);
        return "redirect:/index.html";
    }

}
