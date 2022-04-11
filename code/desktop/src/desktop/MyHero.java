package desktop;

import basiselements.Entity;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Painter;
import tools.Point;

public class MyHero extends Entity {
    private String texturePath;

    public MyHero(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        texturePath="character/knight/knight.png";
    }

    @Override
    public Point getPosition() {
        return null;
    }

    @Override
    public String getTexturePath() {
        return texturePath;
    }
}
