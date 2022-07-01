package com.se701.cat.dto;

public class CreateUserDTO {
    int numOfUsers;

    public CreateUserDTO(int numOfUsers) {
        this.numOfUsers = numOfUsers;
    }

    public CreateUserDTO(){
    }

    public int getNumOfUsers() {
        return numOfUsers;
    }

    public void setNumOfUsers(int numOfUsers) {
        this.numOfUsers = numOfUsers;
    }
}
