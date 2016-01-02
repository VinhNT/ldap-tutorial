package nt.ldap.tutorial.com.service;

import java.util.List;

import javax.naming.ldap.LdapName;

import nt.ldap.tutorial.com.entities.Person;

public interface LdapService {

    Person findByPrimaryKey(String fullname);

    List<Person> listPerson();

    LdapName buildDn(String country, String company, String fullname);

    boolean authenticate(String userDn, String credentials);

    boolean create(Person person);

    boolean savePerson(String fullName, String oldFullName, String phone, String description);

    boolean removePerson(String fullName);
}
