/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

import java.io.Serializable;

/**
 *
 * @author Le Phuoc Duy
 */
public class RegistrationDTO implements Serializable, Comparable<RegistrationDTO> {

    String registrationID, fullName, email, phone, address;
    int age, numberOfMember, numberOfChildren, numberOfAdults;
    boolean gender;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String registrationID, String fullName, String email, String phone, String address, int age, int numberOfMember, int numberOfChildren, int numberOfAdults, boolean gender) {
        this.registrationID = registrationID;
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.age = age;
        this.numberOfMember = numberOfMember;
        this.numberOfChildren = numberOfChildren;
        this.numberOfAdults = numberOfAdults;
        this.gender = gender;
    }

    public String getRegistrationID() {
        return registrationID;
    }

    public void setRegistrationID(String registrationID) {
        this.registrationID = registrationID;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getNumberOfMember() {
        return numberOfMember;
    }

    public void setNumberOfMember(int numberOfMember) {
        this.numberOfMember = numberOfMember;
    }

    public int getNumberOfChildren() {
        return numberOfChildren;
    }

    public void setNumberOfChildren(int numberOfChildren) {
        this.numberOfChildren = numberOfChildren;
    }

    public int getNumberOfAdults() {
        return numberOfAdults;
    }

    public void setNumberOfAdults(int numberOfAdults) {
        this.numberOfAdults = numberOfAdults;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public int compareTo(RegistrationDTO o) {
        return o.getFullName().compareTo(this.getFullName());
    }
}
