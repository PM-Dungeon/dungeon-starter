package particle;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import tools.Point;

public class MyParticle extends Actor {
    ParticleEffect effect;
    Point effectPosition;
    SpriteBatch sp;

    Sound fireEffectSound;
    Sound starEffectSound;
    Sound heartEffectSound;
    Sound snowflakeEffectSound;

    long fireEffectSoundId;
    long starEffectSoundId;
    long heartEffectSoundId;
    long snowflakeEffectSoundId;

    boolean effectVisebility = false;

    public MyParticle(ParticleEffect effect, Point effectPosition, SpriteBatch sp) {
        this.effect = effect;
        this.effectPosition = effectPosition;
        this.sp = sp;
        effect.scaleEffect(0.38f);

        // this.fireEffectSound =
        // Gdx.audio.newSound(Gdx.files.internal("sound/fire.mp3"));
        // this.fireEffectSoundId = this.fireEffectSound.play();
        // this.fireEffectSound.setLooping(this.fireEffectSoundId, true);
        // this.fireEffectSound.setPitch(this.fireEffectSoundId, 0.4f);

        // // this.starEffectSound =
        // Gdx.audio.newSound(Gdx.files.internal("sound/star.mp3"));
        // this.starEffectSoundId = this.starEffectSound.play();
        // this.starEffectSound.setLooping(this.starEffectSoundId, true);
        // this.starEffectSound.setPitch(this.starEffectSoundId, 0.4f);

        // // this.fireEffectSound =
        // Gdx.audio.newSound(Gdx.files.internal("sound/heart.mp3"));
        // this.heartEffectSoundId = this.heartEffectSound.play();
        // this.heartEffectSound.setLooping(this.heartEffectSoundId, true);
        // this.heartEffectSound.setPitch(this.heartEffectSoundId, 0.4f);

        // // this.fireEffectSound =
        // Gdx.audio.newSound(Gdx.files.internal("sound/snow.mp3"));
        // this.snowflakeEffectSoundId = this.snowflakeEffectSound.play();
        // this.snowflakeEffectSound.setLooping(this.snowflakeEffectSoundId, true);
        // this.snowflakeEffectSound.setPitch(this.snowflakeEffectSoundId, 0.4f);

    }

    public void draw(SpriteBatch batch, float parentAlpha) {
        // System.out.println("MyParticle Draw was called...");
        effect.draw(batch); // define behavior when stage calls Actor.draw()
        if (effect.isComplete()) {
            System.out.println("Effect was reset...");
            effect.reset();
        }
    }

    public void setEffectVisebility(boolean visebility) {
        this.effectVisebility = visebility;
    }

    public void draw(SpriteBatch batch) {
        // System.out.println("MyParticle Draw was called...");
        effect.draw(batch); // define behavior when stage calls Actor.draw()
        // effect.reset(false);
        // effect.reset();
        // effect.allowCompletion();
        effect.setDuration(3);

        if (effect.isComplete()) {
            System.out.println("Effect was reset...");
            effect.reset();
        }
    }

    public void setPosition(Point effectPosition) {
        System.out.println(
                "set Positon was called. with x: "
                        + effectPosition.x
                        + " and y: "
                        + effectPosition.y);
        this.effectPosition = effectPosition;
        // this.fireEffectSound.stop();
        effect.setPosition(effectPosition.x, effectPosition.y);
        // this.fireEffectSoundId = this.fireEffectSound.play();
        // this.fireEffectSound.setLooping(this.fireEffectSoundId, true);
        // this.fireEffectSound.setPitch(this.fireEffectSoundId, 0.4f);
    }

    public void act(float delta) {

        super.act(delta);
//        effect.setPosition(
//                this.effectPosition.x, this.effectPosition.y); // set to whatever x/y you prefer

        sp.begin();

        if (effectVisebility == true) {
            effect.start(); // need to start the particle spawning
            draw(sp);
            effect.update(delta); // update it
        }
        sp.end();
    }

    public ParticleEffect getEffect() {
        return effect;
    }

    public void dispose() {
        effect.dispose();
    }
}
