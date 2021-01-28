package ru.job4j.ood.ocp;

public class Music {
    private final ClassicMusic classicMusic;

    public Music(ClassicMusic classicMusic) {
        this.classicMusic = classicMusic;
    }

    public void playMusic() {
        classicMusic.sing();
    }
}
