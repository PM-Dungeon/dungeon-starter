package desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import controller.MainController;
import level.generator.LevelLoader.LevelLoader;
import level.generator.dungeong.graphg.NoSolutionException;
import tools.Point;

public class MyGame extends MainController {
    private MyHero hero;
    private Label levelLabel;
    private int levelCounter = 0;

    public static void main(String[] args) {
        // start the game
        DesktopLauncher.run(new MyGame());
    }

    @Override
    protected void setup() {
        levelAPI.setGenerator(new LevelLoader());
        hero = new MyHero(painter, batch);
        try {
            levelAPI.loadLevel();
        } catch (NoSolutionException e) {
            System.out.println(
                    "Es konnte kein Level geladen werden, bitte den \"assets\" Ordner überprüfen.");
            Gdx.app.exit();
        }
        camera.follow(hero);
        entityController.add(hero);
        // hinzufügen eines Elementes zum HUD
        hudController.add(new MyIcon(hudPainter, hudBatch, new Point(0f, 0f)));
        // so entfernt man ein Element
        // hud.remove(OBJECT);
    }

    @Override
    protected void beginFrame() {}

    @Override
    protected void endFrame() {
        if (levelAPI.getCurrentLevel().isOnEndTile(hero)) {
            try {
                levelAPI.loadLevel();
            } catch (NoSolutionException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onLevelLoad() {
        hero.setLevel(levelAPI.getCurrentLevel());

        levelCounter++;
        if (levelCounter == 1) {
            levelLabel =
                    hudController.drawText(
                            "Level " + levelCounter,
                            "C:\\USERS\\JEDI\\APPDATA\\LOCAL\\MICROSOFT\\WINDOWS\\FONTS\\DIABLO.TTF",
                            Color.RED,
                            30,
                            50,
                            50,
                            30,
                            30);
        } else {
            levelLabel.setText("Level " + levelCounter);
        }
    }
}
