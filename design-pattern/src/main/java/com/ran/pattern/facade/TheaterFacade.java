package com.ran.pattern.facade;

/**
 * TheaterFacade
 *
 * @author rwei
 * @since 2024/8/17 19:04
 */
public class TheaterFacade {
    private DvdPlayer dvdPlayer;

    private Light light;

    public TheaterFacade(DvdPlayer dvdPlayer, Light light) {
        this.dvdPlayer = dvdPlayer;
        this.light = light;
    }

    public void watchMovie() {
        light.on();
        dvdPlayer.on();
        dvdPlayer.play();
    }

    public void endMovie() {
        dvdPlayer.off();
        light.off();
    }
}
