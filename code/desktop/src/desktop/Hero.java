package desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Animation;
import graphic.Painter;
import interfaces.IAnimatable;
import java.util.ArrayList;
import java.util.List;
import level.elements.Level;
import tools.Point;

public class Hero implements IAnimatable {
    private SpriteBatch batch;
    private Painter painter;

    private Point position;

    private Level level;

    private Animation idleAnimation;

    /**
     * a
     *
     * @param batch b
     * @param painter c
     */
    public Hero(SpriteBatch batch, Painter painter) {
        List<String> idle = new ArrayList<>();

        idle.add("character/knight/knight_m_idle_anim_f0.png");
        idle.add("character/knight/knight_m_idle_anim_f1.png");

        idleAnimation = new Animation(idle, 8);

        this.batch = batch;
        this.painter = painter;
    }

    @Override
    public Animation getActiveAnimation() {
        return this.idleAnimation;
    }

    @Override
    public void update() {

        Point newPosition = new Point(this.position);

        float movementSpeed = 0.1f;

        if (Gdx.input.isKeyPressed(Input.Keys.W)) newPosition.y += movementSpeed;

        if (Gdx.input.isKeyPressed(Input.Keys.S)) newPosition.y -= movementSpeed;

        if (Gdx.input.isKeyPressed(Input.Keys.D)) newPosition.x += movementSpeed;

        if (Gdx.input.isKeyPressed(Input.Keys.A)) newPosition.x -= movementSpeed;

        this.position = newPosition;

        this.draw();
    }

    @Override
    public Point getPosition() {
        return this.position;
    }

    @Override
    public SpriteBatch getBatch() {
        return batch;
    }

    @Override
    public Painter getGraphicController() {
        return painter;
    }

    @Override
    public boolean removable() {
        return false;
    }

    public void setLevel(Level level) {
        this.level = level;
        this.position = level.getStartTile().getGlobalPosition();
    }
}
