package model;

import enums.Gender;
import exceptions.IncorrectEmailException;

import java.util.regex.Pattern;

abstract class Person {
    private String firstName;
    private String lastName;
    private Gender gender;
    private String email;
    private String emailRegex = "^(.+)@(.+).(.com)$";
    private Pattern pattern = Pattern.compile(emailRegex);

    public Person(String firstName, String lastName, Gender gender, String email){
        if(!pattern.matcher(email).matches())throw new IncorrectEmailException("Invalid Email");
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;

    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Gender getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender=" + gender +
                ", email='" + email + '\'' +
                '}';
    }
}
