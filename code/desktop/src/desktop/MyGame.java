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
    private int counter = 0;
    private MenuScreen ms;
    private MenuScreenEntry entry1, entry2, entry3;

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

        createMenuScreen();
    }

    @Override
    protected void beginFrame() {
        counter++;
        if (counter == 1000) {
            System.out.println("do something 1");
            ms.removeMenuScreenEntry(entry2);
        }
        if (counter == 2000) {
            System.out.println("do something 2");
            ms.clearMenuScreenEntry();
            ms.addMenuScreenEntry(entry1);
            ms.addMenuScreenEntry(entry2);
            ms.addMenuScreenEntry(entry3);
        }
    }

    @Override
    protected void endFrame() {}

    @Override
    public void onLevelLoad() {}

    public void createMenuScreen() {
        // Trick: Register a "fake" HUDElement

        entry1 = new MenuScreenEntry("Speichern");
        entry1.add(
                new MenuScreenDropEntry(
                        "Foo",
                        new EntryListener() {
                            @Override
                            public void whenClicked(InputEvent event, float x, float y) {
                                System.out.println("Foo clicked ...");
                            }
                        }));
        entry1.add(
                new MenuScreenDropEntry(
                        "Bar",
                        new EntryListener() {
                            @Override
                            public void whenClicked(InputEvent event, float x, float y) {
                                System.out.println("Bar clicked ...");
                            }
                        }));
        entry1.add(
                new MenuScreenDropEntry(
                        "Baz",
                        new EntryListener() {
                            @Override
                            public void whenClicked(InputEvent event, float x, float y) {
                                System.out.println("Baz clicked ...");
                            }
                        }));
        entry2 = new MenuScreenEntry("Laden");
        entry2.add(
                new MenuScreenDropEntry(
                        "qux",
                        new EntryListener() {
                            @Override
                            public void whenClicked(InputEvent event, float x, float y) {
                                System.out.println("Qux clicked ...");
                            }
                        }));
        entry2.add(
                new MenuScreenDropEntry(
                        "quux",
                        new EntryListener() {
                            @Override
                            public void whenClicked(InputEvent event, float x, float y) {
                                System.out.println("Quux clicked ...");
                            }
                        }));
        entry2.add(
                new MenuScreenDropEntry(
                        "corge",
                        new EntryListener() {
                            @Override
                            public void whenClicked(InputEvent event, float x, float y) {
                                System.out.println("Corge clicked ...");
                            }
                        }));
        entry3 = new MenuScreenEntry("Zurücksetzen");
        entry3.add(
                new MenuScreenDropEntry(
                        "grault",
                        new EntryListener() {
                            @Override
                            public void whenClicked(InputEvent event, float x, float y) {
                                System.out.println("Grault clicked ...");
                            }
                        }));
        entry3.add(
                new MenuScreenDropEntry(
                        "gar ply",
                        new EntryListener() {
                            @Override
                            public void whenClicked(InputEvent event, float x, float y) {
                                System.out.println("Gar ply clicked ...");
                            }
                        }));
        entry3.add(
                new MenuScreenDropEntry(
                        "Spam",
                        new EntryListener() {
                            @Override
                            public void whenClicked(InputEvent event, float x, float y) {
                                System.out.println("Spam clicked ...");
                            }
                        }));

        ms = new MenuScreen(hudPainter, hudBatch);
        ms.addMenuScreenEntry(entry1);
        ms.addMenuScreenEntry(entry2);
        ms.addMenuScreenEntry(entry3);

        hudController.add(ms);
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
