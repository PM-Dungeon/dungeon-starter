package desktop;

import basiselements.Animatable;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import graphic.Animation;
import graphic.Painter;
import level.elements.Level;
import tools.Point;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyHero extends Animatable {
    private final Animation idleAnimation;
    private Point position;
    private Level currentLevel;
    private int lebenspunkte = 100;
    private Sound soundFx;
    private long soundFxId;

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

        // load sound effect
        this.soundFx = Gdx.audio.newSound(Gdx.files.internal("sound/sound_f0" + ".mp3"));
    }

    public void setLevel(Level level) {
        currentLevel = level;
        position = level.getStartTile().getCoordinate().toPoint();
    }

    @Override
    public void update() {
        // Temporären Point um den Held nur zu bewegen, wenn es keine Kollision gab
        Point newPosition = new Point(this.position);
        // Unser Held soll sich pro Schritt um 0.1 Felder bewegen.
        float movementSpeed = 0.2f;
        // Wenn die Taste W gedrückt ist, bewege dich nach oben
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            newPosition.y += movementSpeed;
        }
        // Wenn die Taste S gedrückt ist, bewege dich nach unten
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            newPosition.y -= movementSpeed;
        }
        // Wenn die Taste D gedrückt ist, bewege dich nach rechts
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            newPosition.x += movementSpeed;
        }
        // Wenn die Taste A gedrückt ist, bewege dich nach links
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            newPosition.x -= movementSpeed;
        }
        // Wenn der übergebene Punkt betretbar ist, ist das nun die aktuelle Position
        if (currentLevel.getTileAt(newPosition.toCoordinate()).isAccessible()) {
            this.position = newPosition;
        }

        // Play random soundFx when Keys F is pressed
        if (Gdx.input.isKeyPressed(Input.Keys.F)) {
            Random rand = new Random();
            float max = 2.0f;
            float min = 0.24f;
            if (Gdx.input.isKeyPressed(Input.Keys.F)) {
                this.soundFxId = this.soundFx.play(0f);
                this.soundFx.setPitch(this.soundFxId, min + rand.nextFloat() * (max - min));
                this.soundFx.setPan(
                        this.soundFxId,
                        min + rand.nextFloat() * (max - min),
                        min + rand.nextFloat() * (max - min));
            }
        }
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
        return lebenspunkte == 0;
    }
}
