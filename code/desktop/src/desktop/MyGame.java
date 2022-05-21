package desktop;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import controller.MainController;
import java.util.List;
import javax.swing.*;
import level.generator.LevelLoader.LevelLoader;
import level.generator.dungeong.graphg.NoSolutionException;
import menu.DropDownMenu;
import menu.MenuScreen;

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

        createMenuScreen();
    }

    @Override
    protected void beginFrame() {
        counter++;
        if (counter == 100) {
            System.out.println("do something");
        }
    }

    @Override
    protected void endFrame() {}

    @Override
    public void onLevelLoad() {}

    public void createMenuScreen() {
        // Trick: Register a "fake" HUDElement
        ms =
                new MenuScreen(
                        hudPainter,
                        hudBatch,
                        List.of(
                                new DropDownMenu(
                                        "Speichern",
                                        List.of("Foo", "Bar", "Baz"),
                                        List.of(
                                                new ClickListener() {
                                                    @Override
                                                    public void clicked(
                                                            InputEvent event, float x, float y) {
                                                        System.out.println("Foo clicked ...");
                                                    }
                                                },
                                                new ClickListener() {
                                                    @Override
                                                    public void clicked(
                                                            InputEvent event, float x, float y) {
                                                        System.out.println("Bar clicked ...");
                                                    }
                                                },
                                                new ClickListener() {
                                                    @Override
                                                    public void clicked(
                                                            InputEvent event, float x, float y) {
                                                        System.out.println("Baz clicked ...");
                                                    }
                                                })),
                                new DropDownMenu(
                                        "Laden",
                                        List.of("qux", "quux", "corge"),
                                        List.of(
                                                new ClickListener() {
                                                    @Override
                                                    public void clicked(
                                                            InputEvent event, float x, float y) {
                                                        System.out.println("Qux clicked ...");
                                                    }
                                                },
                                                new ClickListener() {
                                                    @Override
                                                    public void clicked(
                                                            InputEvent event, float x, float y) {
                                                        System.out.println("Quux clicked ...");
                                                    }
                                                },
                                                new ClickListener() {
                                                    @Override
                                                    public void clicked(
                                                            InputEvent event, float x, float y) {
                                                        System.out.println("Corge clicked ...");
                                                    }
                                                })),
                                new DropDownMenu(
                                        "Zurücksetzen",
                                        List.of("grault", "gar ply", "spam"),
                                        List.of(
                                                new ClickListener() {
                                                    @Override
                                                    public void clicked(
                                                            InputEvent event, float x, float y) {
                                                        System.out.println("Grault clicked ...");
                                                    }
                                                },
                                                new ClickListener() {
                                                    @Override
                                                    public void clicked(
                                                            InputEvent event, float x, float y) {
                                                        System.out.println("gar ply clicked ...");
                                                    }
                                                },
                                                new ClickListener() {
                                                    @Override
                                                    public void clicked(
                                                            InputEvent event, float x, float y) {
                                                        System.out.println("Spam clicked ...");
                                                    }
                                                }))));
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
