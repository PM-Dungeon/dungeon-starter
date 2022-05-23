package menu;

import basiselements.HUDElement;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import graphic.HUDPainter;
import java.util.ArrayList;
import java.util.List;
import tools.Point;

public class MenuScreen extends HUDElement implements Screen {
    private final Stage stage;
    private final Table table;
    private final List<MenuScreenEntry> menuScreenEntries = new ArrayList<>();

    public MenuScreen(HUDPainter hudPainter, SpriteBatch hudBatch) {
        super(hudPainter, hudBatch);

        stage = new Stage(new ScreenViewport(), hudBatch);

        // Create a Table to group the TextButton. Place the table in the bottom left corner. Each
        // TextButton should have a 20 pixel distance
        // to the next one.
        table = new Table().bottom().left();
        table.defaults().padRight(20);
        table.padLeft(20);

        // Add the table to the stage.
        stage.addActor(table);

        // Register the InputProcessor to the stage and a ClickListener to button1.
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        stage.act(delta);
        stage.draw();
    }

    /*
    This method is not called because it is not registered yet, in LibgdxSetup.
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
    public void dispose() {}

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

    // further methods

    public void addMenuScreenEntry(MenuScreenEntry entry) {
        menuScreenEntries.add(entry);
        updateMenuScreenEntries();
    }

    public void removeMenuScreenEntry(MenuScreenEntry entry) {
        menuScreenEntries.remove(entry);
        updateMenuScreenEntries();
    }

    public void clearMenuScreenEntry() {
        menuScreenEntries.clear();
        updateMenuScreenEntries();
    }

    public void updateMenuScreenEntries() {
        table.clear();
        for (MenuScreenEntry menu : menuScreenEntries) {
            TextButton button = menu.getMenuButton();
            List<MenuScreenDropEntry> entries = menu.getEntryButtons();
            VerticalGroup vg = new VerticalGroup();
            table.add(vg);
            button.addListener(
                    new ClickListener() {
                        @Override
                        public void enter(
                                InputEvent event, float x, float y, int pointer, Actor fromActor) {
                            if (vg.getColumns() == 1) {
                                button.setChecked(true);
                                for (MenuScreenDropEntry entry : entries) {
                                    vg.addActor(entry.getButton());
                                }
                            }
                        }

                        @Override
                        public void exit(
                                InputEvent event, float x, float y, int pointer, Actor toActor) {
                            new Thread(
                                            () -> {
                                                try {
                                                    Thread.sleep(
                                                            MenuScreenEntry.defaultFadeOutTime);
                                                    button.setChecked(false);
                                                    vg.clear();
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                } catch (NullPointerException ignore) {
                                                    // when button was removed
                                                }
                                            })
                                    .start();
                        }
                    });
        }
        table.row();
        for (MenuScreenEntry menu : menuScreenEntries) {
            TextButton button = menu.getMenuButton();
            table.add(button);
        }
    }

    public void setFontSizeRecursive(float scaleXY) {
        for (MenuScreenEntry entry : menuScreenEntries) {
            entry.setFontSize(scaleXY);
            for (MenuScreenDropEntry de : entry.getEntryButtons()) {
                de.setFontSize(scaleXY);
            }
        }
    }
}
