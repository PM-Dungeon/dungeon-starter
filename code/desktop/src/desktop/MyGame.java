package desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import controller.MainController;
import level.generator.LevelLoader.LevelLoader;
import level.generator.dungeong.graphg.NoSolutionException;
import menu.EntryListener;
import menu.MenuScreen;
import menu.MenuScreenDropEntry;
import menu.MenuScreenEntry;

public class MyGame extends MainController {
    private MenuScreen menuScreen;

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

        // optional, uncomment this if you want a start menu:
        // createMenuScreen();
        // addDemoMenuEntries();
    }

    @Override
    protected void beginFrame() {}

    @Override
    protected void endFrame() {}

    @Override
    public void onLevelLoad() {}

    private void createMenuScreen() {
        // Trick: Register a "fake" HUDElement
        menuScreen = new MenuScreen(hudPainter, hudBatch);
        hudController.add(menuScreen);
    }

    private void addDemoMenuEntries() {
        EntryListener listener =
                new EntryListener() {
                    @Override
                    public void whenClicked(InputEvent event, float x, float y) {
                        System.out.println("An item clicked.");
                    }
                };
        MenuScreenEntry demoEntry = new MenuScreenEntry("Demo 1");
        MenuScreenDropEntry item1 = new MenuScreenDropEntry("Item 1", listener);
        MenuScreenDropEntry item2 = new MenuScreenDropEntry("Item 2", listener);
        demoEntry.add(item1);
        demoEntry.add(item2);
        menuScreen.addMenuScreenEntry(demoEntry);
    }

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
