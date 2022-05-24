package particle;

import basiselements.HUDElement;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import graphic.HUDPainter;
import tools.Constants;
import tools.Point;

public class ParticleScreen extends HUDElement implements Screen {
    private final Stage stage;
    ParticleEffect fire;
    MyParticle fireParticle;
    ParticleEffect heart;
    MyParticle heartParticle;
    ParticleEffect snow;
    MyParticle snowParticle;
    ParticleEffect star;
    MyParticle starParticle;
    int currentActiveParticleIndex = 0;
    Particles currentParticle = Particles.FIRE;

    public ParticleScreen(HUDPainter hudPainter, SpriteBatch hudBatch) {
        super(hudPainter, hudBatch);

        stage = new Stage(new ScreenViewport(), hudBatch);

        // load the fire particle effect
        fire = new ParticleEffect();
        fire.load(
                Gdx.files.internal("effects/torch.p"),
                Gdx.files.internal("effects")); // files.internal loads from

        fireParticle =
                new MyParticle(
                        fire,
                        new Point(
                                ((float) Constants.WINDOW_WIDTH / 2),
                                ((float) Constants.WINDOW_HEIGHT / 2)),
                        hudBatch);

        // load the star particle effect
        star = new ParticleEffect();
        star.load(
                Gdx.files.internal("effects/stars.p"),
                Gdx.files.internal("effects")); // files.internal loads from
        // the

        starParticle =
                new MyParticle(
                        star,
                        new Point(
                                ((float) Constants.WINDOW_WIDTH / 2),
                                ((float) Constants.WINDOW_HEIGHT / 2)),
                        hudBatch);

        // load the heart particle effect
        heart = new ParticleEffect();
        heart.load(
                Gdx.files.internal("effects/hearts.p"),
                Gdx.files.internal("effects")); // files.internal loads from
        // the

        heartParticle =
                new MyParticle(
                        heart,
                        new Point(
                                ((float) Constants.WINDOW_WIDTH / 2),
                                ((float) Constants.WINDOW_HEIGHT / 2)),
                        hudBatch);

        // load the snow particle effect
        snow = new ParticleEffect();
        snow.load(
                Gdx.files.internal("effects/snowflake.p"),
                Gdx.files.internal("effects")); // files.internal loads
        // from the
        snowParticle =
                new MyParticle(
                        snow,
                        new Point(
                                ((float) Constants.WINDOW_WIDTH / 2),
                                ((float) Constants.WINDOW_HEIGHT / 2)),
                        hudBatch);

        stage.addActor(fireParticle);
        stage.addActor(heartParticle);
        stage.addActor(snowParticle);
        stage.addActor(starParticle);

        // Register the InputProcessor to the stage and a ClickListener to button1.
        Gdx.input.setInputProcessor(stage);

        stage.addListener(
                new ClickListener() {
                    public void clicked(InputEvent event, float x, float y) {
                        super.clicked(event, x, y);
                        System.out.println(
                                String.format(
                                        "Clicked on the stage at x: %d and y: %d ",
                                        (int) x, (int) y));

                        System.out.println("Current Active Particle = " + currentParticle);
                        switch (currentParticle) {
                            case FIRE:
                                fireParticle.setPosition(new Point(x, y));
                                activateEffect(currentActiveParticleIndex);
                                break;
                            case HEART:
                                heartParticle.setPosition(new Point(x, y));
                                activateEffect(currentActiveParticleIndex);
                                break;
                            case SNOW:
                                snowParticle.setPosition(new Point(x, y));
                                activateEffect(currentActiveParticleIndex);
                                break;
                            case STAR:
                                starParticle.setPosition(new Point(x, y));
                                activateEffect(currentActiveParticleIndex);
                                break;
                        }
                    }
                });
    }

    public void toggleEffects() {
        System.out.println("Index: " + currentActiveParticleIndex);
        if (currentActiveParticleIndex < 0 || currentActiveParticleIndex >= 3) {
            System.out.println("Resette Index auf 0 weil der Index bei " + currentActiveParticleIndex + " lag.");
            currentActiveParticleIndex = 0;
        } else {
            System.out.println("Erhöhe Index um 1 auf " + ++currentActiveParticleIndex);
//            currentActiveParticleIndex++;
        }

        switch(currentActiveParticleIndex){
            case 0:
                currentParticle = Particles.FIRE;
                break;
            case 1:
                currentParticle = Particles.HEART;
                break;
            case 2:
                currentParticle = Particles.SNOW;
                break;
            case 3:
                currentParticle = Particles.STAR;
                break;

        }

    }



    public void activateEffect(int index) {
        switch (index) {
            case 0:
                fireParticle.setEffectVisebility(true);
                toggleEffects();
                break;
            case 1:
                starParticle.setEffectVisebility(true);
                toggleEffects();
                break;
            case 2:
                heartParticle.setEffectVisebility(true);
                toggleEffects();
                break;
            case 3:
                snowParticle.setEffectVisebility(true);
                toggleEffects();
                break;
        }
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    /*
     * This method is not called because it is not registered yet, in LibgdxSetup.
     */
    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        fireParticle.dispose();
        fire.dispose();
        stage.dispose();
    }

    @Override
    public Point getPosition() {
        return null;
    }

    @Override
    public String getTexturePath() {
        return null;
    }

    @Override
    public void update() {
        // crop super call
        render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void draw() {
        // crop super call
    }

    enum Particles {
        FIRE,
        HEART,
        SNOW,
        STAR
    }

    private final class InputListenerExtension extends InputListener {}

    // further methods

}
