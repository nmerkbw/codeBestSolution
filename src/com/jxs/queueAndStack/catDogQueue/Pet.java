package com.jxs.queueAndStack.catDogQueue;

public class Pet {

    private String type;

    public Pet(String type) {

        this.type = type;
    }

    public String getType() {

        return this.type;
    }
}

class Dog extends Pet {

    public Dog() {
        super("Dog");
    }
}

class Cat extends Pet {

    public Cat() {
        super("Cat");
    }
}
