package io.github.some_example_name;

import com.badlogic.gdx.backends.LwjglApplication;
import com.badlogic.gdx.backends.LwjglApplicationConfiguration;

public class Main {
    public static void main(String[] args) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = "Your Game";
        config.width = 1920;
        config.height = 1080;
        new LwjglApplication(new gameControl(), config);
    }
}
