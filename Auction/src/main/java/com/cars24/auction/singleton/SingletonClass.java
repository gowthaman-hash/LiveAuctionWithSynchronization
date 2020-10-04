package com.cars24.auction.singleton;

public class SingletonClass {
    private static Object lock;
    private static SingletonClass singletonClass;

    private SingletonClass() {

    }

    public static SingletonClass getInstance() {
        if (null == singletonClass) {
            synchronized (SingletonClass.class) {
                if (null == singletonClass) {
                    return new SingletonClass();
                } else {
                    return singletonClass;
                }
            }
        } else {
            return singletonClass;
        }
    }
}
