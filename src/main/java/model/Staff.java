package model;

import enums.Gender;
import enums.Role;
import exceptions.IncorrectEmailException;

public class Staff extends Person{
    private Role role;
    private final String StaffId;
    private static int num = 1;
    private String worksAt;

    public Staff(String firstName, String lastName, Gender gender, String email, Role role) {
        super(firstName, lastName, gender, email);
        this.role = role;
        this.StaffId = "Staff " + getRole()+ " - " + num;
        num++;
    }

    public Role getRole() {
        return role;
    }

    public void setWorksAt(String storeName){
        this.worksAt = storeName;
    }

    public String getWorksAt() {
        return worksAt;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "name="+getFirstName() +" "+getLastName()+
                ", role=" + role +
                ", StaffId=" + StaffId + '\'' +
                ", Works At=" + worksAt + '\'' +
                '}';
    }
}
