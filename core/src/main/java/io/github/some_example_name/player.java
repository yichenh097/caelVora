package io.github.some_example_name;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class player extends Sprite {
    private OrthographicCamera camera;
    private float xVel = 0;
    private Sprite playerSprite;
    private float yVel = 0;
    private Rectangle level;
    private boolean jump = false;
    private double finalx, finaly;

    public void Player(){
        Texture texture = new Texture("player.png");
        playerSprite = new Sprite(texture);
        playerSprite.setPosition(100, 500);

    }
    public void update(float delta, Rectangle level) {
        input(delta, level);
        cameraset();
    } // for if new level im pretty sure, update level, time
    public void initialize(OrthographicCamera camera) {
        this.camera = camera;
    }
    public void draw(SpriteBatch batch){
        playerSprite.draw(batch);
    }

    private void input(float delta, Rectangle level) {
        float yb4 = playerSprite.getY();
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            xVel++;
        } if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            xVel--;
        } if (Gdx.input.isButtonJustPressed(Input.Buttons.RIGHT)){
            dash();
            xVel = (float) finalx;
            yVel = (float) finaly;
        }
        float ogPos = playerSprite.getX();
        playerSprite.translateX(xVel*delta*100);
        if(playerSprite.getBoundingRectangle().overlaps(level)) {
            playerSprite.setX(ogPos);
            xVel = 0;
        }
        xVel *= 0.93F;
        float ogPosY = playerSprite.getY();
        playerSprite.translateY(yVel);
        if(playerSprite.getBoundingRectangle().overlaps(level)){
            if (yVel < 0){
                playerSprite.setY(ogPosY);
                yVel =0;
                jump = true;
            }
            if(Gdx.input.isKeyPressed(Input.Keys.W)){
                if(jump) {
                    yVel = 10;
                    jump = false;
                }
            }
        } else {
            yVel -= 0.35;
        }
        float yaf = playerSprite.getY();
    }
    public void cameraset(){
        float cameraX = playerSprite.getX() + playerSprite.getWidth()/2;
        float cameraY = playerSprite.getY() + playerSprite.getHeight()/2;
        camera.translate((float) ((cameraX - camera.position.x)*0.1), (float) ((cameraY - camera.position.y)*0.01));
    }
    private void dash(){
        Vector3 mousePos = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(mousePos);
        Vector2 playerPos = new Vector2(playerSprite.getX(), playerSprite.getY());
        Vector2 direction = new Vector2(mousePos.x - playerPos.x, mousePos.y - playerPos.y);
        direction.nor();
        finalx = 24*direction.x;
        finaly = 24*direction.y;
//add while dashing, fix it, make better, particles. DASH TIMER!! fix deltatime thing too im too tired im a bum gn
    }
}
