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
import java.util.List;
import tools.Point;

public class MenuScreen extends HUDElement implements Screen {
    private final Stage stage;
    private final Table table;
    private final List<DropDownMenu> dropDownMenus;

    public MenuScreen(
            HUDPainter hudPainter, SpriteBatch hudBatch, List<DropDownMenu> dropDownMenus) {
        super(hudPainter, hudBatch);

        this.dropDownMenus = dropDownMenus;

        stage = new Stage(new ScreenViewport(), hudBatch);

        // Create a Table to group the TextButton. Place the table in the bottom left corner. Each
        // TextButton should have a 20 pixel distance
        // to the next one.
        table = new Table().bottom().left();
        table.defaults().padRight(20);
        table.padLeft(20);
        for (DropDownMenu menu : dropDownMenus) {
            TextButton b = menu.getMenuButton();
            List<TextButton> entries = menu.getEntryButtons();
            table.add(b);
            VerticalGroup vg = new VerticalGroup();
            table.add(vg);
            b.addListener(
                    new ClickListener() {
                        @Override
                        public void enter(
                                InputEvent event, float x, float y, int pointer, Actor fromActor) {
                            if (vg.getColumns() == 1) {
                                b.setChecked(true);
                                for (TextButton entry : entries) {
                                    vg.addActor(entry);
                                }
                            }
                        }

                        @Override
                        public void exit(
                                InputEvent event, float x, float y, int pointer, Actor toActor) {
                            new Thread(
                                            () -> {
                                                try {
                                                    Thread.sleep(3000);
                                                    for (TextButton entry : entries) {
                                                        entry.setChecked(false);
                                                    }
                                                    b.setChecked(false);
                                                    vg.clear();
                                                } catch (InterruptedException e) {
                                                    e.printStackTrace();
                                                }
                                            })
                                    .start();
                        }
                    });
        }

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

    public void setFontSize(float scaleAmount) {
        for (DropDownMenu menu : dropDownMenus) {
            menu.setFontSize(scaleAmount);
        }
    }
}
