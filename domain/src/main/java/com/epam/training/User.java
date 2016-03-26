package com.epam.training;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author vkrasovsky
 */
@XmlRootElement//TODO: For xml binding
public class User {
    //    private String _id;
    private String email;
    private String firstName;
    private String lastName;
    private String login;

    //TODO: For json, xml binding
    public User() {
    }

    public User(String email, String firstName, String lastName, String login) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.login = login;
    }

//    public String get_id() {
//        return _id;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", login='" + login + '\'' +
                '}';
    }
}
