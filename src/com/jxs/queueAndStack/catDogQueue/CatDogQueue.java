package com.jxs.queueAndStack.catDogQueue;

import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 猫狗队列
 * 实现方法就是使用一个新的类封装Pet类和时间戳count，然后分别使用dogQueue和catQueue来分别装狗
 * 和猫，装的时候使用时间戳计数来判断装入的先后顺序
 * */
public class CatDogQueue {

    private Queue<PetEnterQueue> dogQueue;
    private Queue<PetEnterQueue> catQueue;
    private long count;

    public CatDogQueue() {

        dogQueue = new LinkedList<>();
        catQueue = new LinkedList<>();
        count = 0;
    }

    public void add(Pet pet) {

        if (pet.getType().equals("Dog")) {
            dogQueue.add(new PetEnterQueue(pet, count++));
        } else if (pet.getType().equals("Cat")) {
            catQueue.add(new PetEnterQueue(pet, count++));
        } else {
            throw new RuntimeException("加入队列的类型不是猫和狗！");
        }
    }

    public Pet pollAll() {

        if (!dogQueue.isEmpty() && !catQueue.isEmpty()) {
            if (dogQueue.peek().getCount() < catQueue.peek().getCount()) {
                return dogQueue.poll().getPet();
            } else {
                return catQueue.poll().getPet();
            }
        } else if (!dogQueue.isEmpty() && catQueue.isEmpty()) {
            return dogQueue.poll().getPet();

        } else if (!catQueue.isEmpty() && dogQueue.isEmpty()) {
            return catQueue.poll().getPet();
        }
        return null;
    }

    public Dog pollDog() {

        if (this.isDogEmpty()) {
            throw new RuntimeException("狗队列为空！");
        } else if (!this.isDogEmpty()) {
            return (Dog) this.dogQueue.poll().getPet();
        }
        return null;
    }

    public Cat pollCat() {

        if (catQueue.isEmpty()) {
            throw new RuntimeException("猫队列为空！");
        } else if (!catQueue.isEmpty()) {
            return (Cat) catQueue.poll().getPet();
        }
        return null;
    }

    public boolean isEmpty() {

        return dogQueue.isEmpty() && catQueue.isEmpty();
    }

    public boolean isDogEmpty() {

        return dogQueue.isEmpty();
    }

    public boolean isCatEmpty() {

        return catQueue.isEmpty();
    }

    @Test
    public void test() {

        CatDogQueue catDogQueue = new CatDogQueue();

        catDogQueue.add(new Dog());
        catDogQueue.add(new Cat());
        catDogQueue.add(new Dog());
        catDogQueue.add(new Dog());
        catDogQueue.add(new Cat());
        catDogQueue.add(new Cat());

//        while (!catDogQueue.isEmpty()) {
//            System.out.println(catDogQueue.pollAll().getType());
//        }
        while (!catDogQueue.isDogEmpty()) {
            System.out.println(catDogQueue.pollDog().getType());
        }
    }
}
