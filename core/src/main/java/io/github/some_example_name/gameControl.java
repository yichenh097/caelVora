package io.github.some_example_name;

import com.badlogic.gdx.Game;

public class gameControl extends Game {
    @Override
    public void create(){
        setScreen(new gameScreen(this));
    }
}
