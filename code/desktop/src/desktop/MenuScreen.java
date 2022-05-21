package desktop;

import basiselements.HUDElement;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import graphic.HUDPainter;
import java.util.ArrayList;
import java.util.List;
import tools.Point;

public class MenuScreen extends HUDElement implements Screen {
    private final Stage stage;
    private final TextButton.TextButtonStyle buttonStyle;
    private final List<TextButton> buttonList = new ArrayList<>();
    private final Table table;

    public MenuScreen(HUDPainter hudPainter, SpriteBatch hudBatch) {
        super(hudPainter, hudBatch);

        stage = new Stage(new ScreenViewport(), hudBatch);

        // Create three TextButton with the same style.
        buttonStyle = new TextButton.TextButtonStyle();
        buttonStyle.font = new BitmapFont();
        buttonStyle.fontColor = Color.CORAL;
        buttonStyle.checkedFontColor =
                Color.RED; // When the button is checked/highlighted, it appears red.
        buttonList.add(new TextButton("Speichern", buttonStyle));
        buttonList.add(new TextButton("Laden", buttonStyle));
        buttonList.add(new TextButton("Zurücksetzen", buttonStyle));

        // Create a Table to group the TextButton. Place the table in the bottom left corner. Each
        // TextButton should have a 20 pixel distance
        // to the next one.
        table = new Table().bottom().left();
        table.defaults().padRight(20);
        table.padLeft(20);
        for (TextButton b : buttonList) {
            table.add(b);
        }

        // Add the table to the stage.
        stage.addActor(table);

        // Register the InputProcessor to the stage and a ClickListener to button1.
        Gdx.input.setInputProcessor(stage);

        buttonList
                .get(0)
                .addListener(
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

    public void setFontSize(float scaleAmount) {
        buttonStyle.font.getData().scale(scaleAmount);
        for (TextButton b : buttonList) {
            b.setStyle(buttonStyle);
        }
    }
}
