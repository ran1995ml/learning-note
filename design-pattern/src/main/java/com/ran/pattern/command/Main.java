<<<<<<< HEAD
package com.ran.pattern.command;

/**
 * Main
 *
 * @author rwei
 * @since 2024/8/13 22:48
 */
public class Main {
    public static void main(String[] args) {
        RemoteControl remoteControl = new RemoteControl();
        Light livingRoomLight = new Light("living room");
        Light bathRoomLight = new Light("bath room");
        Door livingRoomDoor = new Door("living room");
        Command livingRoomLightOn = new LightOnCommand(livingRoomLight);
        Command livingRoomLightOff = new LightOffCommand(livingRoomLight);
        Command bathRoomLightOn = new LightOnCommand(bathRoomLight);
        Command bathRoomLightOff = new LightOffCommand(bathRoomLight);
        Command livingRoomDoorOn = new DoorOnCommand(livingRoomDoor);
        Command livingRoomDoorOff = new DoorOffCommand(livingRoomDoor);
        remoteControl.setCommand(0, livingRoomDoorOn, livingRoomDoorOff);
        remoteControl.setCommand(1, livingRoomLightOn, livingRoomLightOff);
        remoteControl.setCommand(2, bathRoomLightOn, bathRoomLightOff);

        System.out.println(remoteControl);
        remoteControl.pressOn(0);
        remoteControl.pressOn(1);
        remoteControl.pressOn(2);
        remoteControl.pressOff(0);
        remoteControl.undo();
    }
=======
package com.ran.pattern.command;/** 
 * Main
 * 
 * @author rwei
 * @since 2024/8/13 22:48
 */public class Main {
>>>>>>> d2268072ead9337b9394f0bca4208b39a9603856
}
