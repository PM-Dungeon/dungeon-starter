package desktop;

import com.badlogic.gdx.Gdx;
import controller.MainController;
import level.generator.LevelLoader.LevelLoader;
import level.generator.dungeong.graphg.NoSolutionException;

public class MyGame extends MainController {
    private int counter = 0;
    private MenuScreen ms;

    @Override
    protected void setup() {
        levelAPI.setGenerator(new LevelLoader());
        // load the first level
        try {
            levelAPI.loadLevel();
        } catch (NoSolutionException e) {
            System.out.println(
                    "Es konnte kein Level geladen werden, bitte den \"assets\" Ordner überprüfen.");
            Gdx.app.exit();
        }

        // Trick: Register a "fake" HUDElement
        ms = new MenuScreen(hudPainter, hudBatch);
        hudController.add(ms);
    }

    @Override
    protected void beginFrame() {
        counter++;
        if (counter == 100) {
            System.out.println("set font size");
            ms.setFontSize(1.5f);
        }
    }

    @Override
    protected void endFrame() {}

    @Override
    public void onLevelLoad() {}

    /**
     * The program entry point to start the dungeon.
     *
     * @param args command line arguments, but not needed.
     */
    public static void main(String[] args) {
        // start the game
        DesktopLauncher.run(new MyGame());
    }
}
