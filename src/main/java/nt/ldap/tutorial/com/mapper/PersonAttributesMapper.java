package nt.ldap.tutorial.com.mapper;


import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

import org.springframework.ldap.core.AttributesMapper;

import nt.ldap.tutorial.com.entities.Person;

public class PersonAttributesMapper implements AttributesMapper<Person> {
    @Override
    public Person mapFromAttributes(Attributes attrs) throws NamingException {
        Person person = new Person();
        Attribute att = attrs.get("cn");
        if (att != null) {
            person.setFullName((String) att.get());
        }
        att = attrs.get("sn");
        if (att != null) {
            person.setLastName((String) att.get());
        }
        att = attrs.get("description");
        if (att != null) {
            person.setDescription((String) att.get());
        }
        att = attrs.get("telephoneNumber");
        if (att != null) {
            person.setPhone((String) att.get());
        }
        person.setCompany("MyCompany");
        person.setCountry("Sweden");
        return person;
    }

}
