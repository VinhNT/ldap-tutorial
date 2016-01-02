package nt.ldap.tutorial.com.service.impl;

import java.util.List;

import javax.naming.directory.DirContext;
import javax.naming.ldap.LdapName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.ContextSource;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.security.ldap.LdapUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import nt.ldap.tutorial.com.entities.Person;
import nt.ldap.tutorial.com.mapper.PersonAttributesMapper;
import nt.ldap.tutorial.com.service.LdapService;

@Component
public class LdapServiceIml implements LdapService {

    @Autowired
    private ContextSource contextSource;

    @Autowired
    private LdapTemplate ldapTemplate;

    @Override
    public boolean authenticate(String userDn, String credentials) {
        DirContext ctx = null;

        try {
            ctx = contextSource.getContext("DN: uid=admin,ou=MyCompany,c=Sweden,dc=example,dc=com", "secret");
            return true;
        } catch (Exception e) {
            return false;
        } finally {
            LdapUtils.closeContext(ctx);
        }
    }

    @Override
    public boolean create(Person person) {
        try {
            ldapTemplate.create(person);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean update(Person person) {
        try {
            ldapTemplate.update(person);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public LdapName buildDn(String country, String company, String fullname) {
        return LdapNameBuilder.newInstance().add("c", country).add("ou", company).add("cn", fullname).build();
    }

    @Override
    public Person findByPrimaryKey(String fullname) {
        LdapName dn = buildDn("Sweden", "MyCompany", fullname);
        Person person = ldapTemplate.findByDn(dn, Person.class);
        return person;
    }

    @Override
    public List<Person> listPerson() {
        return ldapTemplate.search("ou=MyCompany,c=Sweden", "cn=*", new PersonAttributesMapper());
    }

    @Override
    public boolean savePerson(String fullName, String oldFullName, String phone, String description) {
        if (StringUtils.isEmpty(oldFullName)) {
            return addPerson(fullName, phone, description);
        }
        Person person = ldapTemplate.findByDn(buildDn("Sweden", "MyCompany", oldFullName), Person.class);
        if (person != null) {
            person.setFullName(fullName);
            person.setPhone(phone);
            person.setDescription(description);
            ldapTemplate.update(person);
        } else {
            return addPerson(fullName, phone, description);
        }
        return true;
    }

    private boolean addPerson(String fullName, String phone, String description) {
        Person person = new Person();
        person.setFullName(fullName);
        person.setPhone(phone);
        person.setDescription(description);
        person.setCompany("MyCompany");
        person.setCountry("Sweden");
        person.setLastName(getLastName(fullName));
        ldapTemplate.create(person);
        return true;
    }

    private String getLastName(String fullName) {
        int bIndex = fullName.lastIndexOf(' ');
        if (bIndex < 0) {
            bIndex = 0;
        }
        return fullName.substring(bIndex).trim();
    }

    @Override
    public boolean removePerson(String fullName) {
        Person person = ldapTemplate.findByDn(buildDn("Sweden", "MyCompany", fullName), Person.class);
        if (person != null) {
            ldapTemplate.delete(person);
            return true;
        }
        return false;
    }
}
