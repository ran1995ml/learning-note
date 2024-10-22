package com.ran.pattern.memento;

/**
 * Main
 *
 * @author rwei
 * @since 2024/9/24 15:17
 */
public class Main {
    public static void main(String[] args) {
        Originator originator = new Originator();
        CareTaker careTaker = new CareTaker();
        originator.setState("State 1");
        originator.setState("State 2");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State 3");
        careTaker.add(originator.saveStateToMemento());
        originator.setState("State 4");
        System.out.println(originator.getState());
        originator.getStateFromMemento(careTaker.get(0));
        System.out.println(originator.getState());
        originator.getStateFromMemento(careTaker.get(1));
        System.out.println(originator.getState());
    }
}
