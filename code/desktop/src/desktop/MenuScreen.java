package desktop;

import basiselements.HUDElement;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import graphic.HUDPainter;
import tools.Point;

public class MenuScreen extends HUDElement implements Screen {
    private final Stage stage;
    private final Container<Table> tableContainer;

    public MenuScreen(HUDPainter hudPainter, SpriteBatch hudBatch) {
        super(hudPainter, hudBatch);

        stage = new Stage(new ScreenViewport(), hudBatch);

        // Create three TextButton with the same style.
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.CORAL;
        style.checkedFontColor =
                Color.RED; // When the button is checked/highlighted, it appears red.
        TextButton button1 = new TextButton("Datei 1", style);
        TextButton button2 = new TextButton("Datei 2", style);
        TextButton button3 = new TextButton("Datei 3", style);

        // Create a Table to group the TextButton. Each TextButton should have a 10 pixel distance
        // to the next one.
        Table table = new Table();
        table.defaults().padRight(10);
        table.padLeft(10).add(button1);
        table.add(button2);
        table.add(button3);

        // Place the table in a table container in the bottom left corner.
        tableContainer = new Container<>();
        tableContainer.bottom().left();
        tableContainer.setActor(table);
        stage.addActor(tableContainer);

        // Register the InputProcessor to the stage and a ClickListener to button1.
        Gdx.input.setInputProcessor(stage);

        button1.addListener(
                new ClickListener() {
                    @Override
                    public void clicked(InputEvent event, float x, float y) {
                        System.out.println("event = " + event);
                    }
                });
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
}
