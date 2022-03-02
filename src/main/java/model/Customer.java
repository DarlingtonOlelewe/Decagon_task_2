package model;

import enums.Gender;
import exceptions.IncorrectEmailException;

import java.util.HashMap;
import java.util.Map;

public class Customer extends Person{
    private final Map<String, Integer> cartMap;

    public Customer(String firstName, String lastName, Gender gender, String email){
        super(firstName, lastName, gender, email);
        this.cartMap = new HashMap<>();

    }
    public Map<String, Integer> getCartMap(){
        return cartMap;
    }


}
