package model;

import enums.Role;
import exceptions.NotOwnersException;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private final List<Applicant> applicantList;
    private final List<Staff> staffList;
    private final String storeName;
    private Product[] productsInStore;

    public Store(String storeName, Staff manager) {
        if(!manager.getRole().equals(Role.MANAGER)) throw new NotOwnersException("A cashier can not Instantiate a store");
        this.storeName = storeName;
        this.applicantList = new ArrayList<>();
        this.staffList = new ArrayList<>();
        this.productsInStore = new Product[0];
        manager.setWorksAt(storeName);
        staffList.add(manager);
    }

    public Product[] getProductsInStore(){
        return productsInStore;
    }


    public void addingProducts(Product product){
        int size = productsInStore.length + 1;
        Product[] temp = productsInStore.clone();

        productsInStore = new Product[size];

        for(int i = 0; i < size - 1; i++){
            productsInStore[i] = temp [i];
        }

        productsInStore[size - 1] = product;
    }

    public List<Applicant> getApplicantList() {
        return applicantList;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public String getStoreName() {
        return storeName;
    }

    @Override
    public String toString() {
        return "Store{" +
                "staffList=" + staffList +
                ", storeName='" + storeName + '\'' +
                '}';
    }
}
