package com.declanbuckley.neighFURhood;
import java.util.ArrayList;

public class Owner {
    private String name;
    private String phoneNumber;
    private String email;
    private String password;
    private String address;
    private ArrayList<Pet> pets;
    private static ArrayList<Owner> owners = new ArrayList<Owner>();
    // Constructor
    public Owner( String name, String phoneNumber, String address) {

        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.pets = new ArrayList<Pet>();
    }
    public Owner(String email, String password, String name, String phoneNumber, String address) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.pets = new ArrayList<Pet>();
        owners.add(this);
    }
    // Getters
    public static ArrayList<Owner> getOwners() {
        return owners;
    }
    public String getName() {
        return name;
    }
    public ArrayList<Pet> getPets() {
        return pets;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public String getPassword(){ 
        return password; 
    }
    public String getEmail(){
        return email;
    }
    public String getAddress() {
        return address;
    }
    // setters
    public void changeName(String newName) {
        this.name = newName;
    }
    public void changePhoneNumber(String newPhoneNumber) {
        this.phoneNumber = newPhoneNumber;
    }
    public void changeAddress(String newAddress) {
        this.address = newAddress;
    }
    // @Override for the default toString method
    @Override
    public String toString() {
        return "Owner:" +
                "name=" + name + '\n' +
                ", phoneNumber=" + phoneNumber + '\n' +
                ", address=" + address + '\n';
    }
}
