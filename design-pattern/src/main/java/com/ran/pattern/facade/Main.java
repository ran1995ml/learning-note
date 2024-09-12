<<<<<<< HEAD
package com.ran.pattern.facade;

/**
 * Main
 *
 * @author rwei
 * @since 2024/8/17 19:41
 */
public class Main {
    public static void main(String[] args) {
        Light light = new Light();
        DvdPlayer dvdPlayer = new DvdPlayer();
        TheaterFacade theaterFacade = new TheaterFacade(dvdPlayer, light);
        theaterFacade.watchMovie();
        System.out.println();
        theaterFacade.endMovie();
    }
=======
package com.ran.pattern.facade;/** 
 * Main
 * 
 * @author rwei
 * @since 2024/8/17 19:41
 */public class Main {
>>>>>>> d2268072ead9337b9394f0bca4208b39a9603856
}
