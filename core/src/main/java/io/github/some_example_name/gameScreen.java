package io.github.some_example_name;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class gameScreen implements Screen {
    private final gameControl game;
    private final SpriteBatch batch;
    private final player player;
    OrthographicCamera camera;
    Sprite plat;
    FitViewport viewport;
    Rectangle level;
    Texture bg;
    public gameScreen(gameControl game){
        this.game = game;
        this.batch = new SpriteBatch();
        this.player = new player();
    }

    @Override
    public void show() {
        Texture platform = new Texture("platform.png");
        bg = new Texture("Ptest.png");

        plat = new Sprite(platform);
        plat.setPosition(100,200);
        plat.setSize(6000,200);

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 1920, 1080);
        viewport = new FitViewport(1920,1080); //make set to display or smth

        level = plat.getBoundingRectangle();
        player.initialize(camera);
    }

    @Override
    public void render(float delta){
        Gdx.gl.glClearColor(255,255,255,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        player.update(delta, level);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        player.draw(batch);
        plat.draw(batch);
        batch.end();
    }

    @Override
    public void resize(int width, int height){
        viewport.update(width,height,true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        bg.dispose();
    }

}
