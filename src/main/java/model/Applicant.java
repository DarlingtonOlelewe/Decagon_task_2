package model;

import enums.Gender;
import enums.Qualification;
import enums.Role;
import exceptions.IncorrectEmailException;

public class Applicant extends Person{
    private Role role;
    private Qualification qualification;
    public Applicant(String firstName, String lastName, Gender gender,String email, Role role, Qualification qualification){
        super(firstName, lastName, gender, email);
        this.qualification = qualification;
        this.role = role;
    }

    public Role getRole() {
        return role;
    }


    public Qualification getQualification() {
        return qualification;
    }

    @Override
    public String toString() {
        return "Applicant{" +
                "Name=" + getFirstName()+" "+ getLastName()+
                ", Email=" + getEmail()+
                ", role=" + role +
                ", qualification=" + qualification +
                '}';
    }
}
