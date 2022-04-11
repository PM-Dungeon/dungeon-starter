package desktop;

import basiselements.Animatable;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Animation;
import graphic.Painter;
import level.elements.Level;
import tools.Point;
import java.util.ArrayList;
import java.util.List;

public class MyHero extends Animatable {
    private Animation idleAnimation;
    private Point position;
    private Level currentLevel;
    private int lebenspunkte = 100;

    public MyHero(Painter painter, SpriteBatch batch) {
        super(painter, batch);
        // Erstellen einer ArrayList
        List<String> animation = new ArrayList<>();
        // Laden der Texturen für die Animation (relativen Pfad angeben)
        animation.add("character/knight/knight_m_idle_anim_f0.png");
        animation.add("character/knight/knight_m_idle_anim_f1.png");
        // Erstellen einer Animation, als Parameter wird die Liste mit den Texturen
        // und die Wartezeit (in Frames) zwischen den Wechsel der Texturen angegeben
        idleAnimation = new Animation(animation, 8);
    }

    public void setLevel(Level level) {
        currentLevel = level;
        position = level.getStartTile().getCoordinate().toPoint();
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public Animation getActiveAnimation() {
        return idleAnimation;
    }

    @Override
    public boolean removable() {
        return lebenspunkte==0;
    }
}
