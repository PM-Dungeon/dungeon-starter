package menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

public class MenuScreenDropEntry {
    private final TextButton button;

    public MenuScreenDropEntry(String text, EntryListener listener) {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.BLUE;
        style.checkedFontColor = Color.YELLOW;
        button = new TextButton(text, style);
        listener.setButton(button);
        setFontSize(MenuScreenEntry.defaultScaleXY);
    }

    public TextButton getButton() {
        return button;
    }

    public void setFontSize(float scaleXY) {
        TextButton.TextButtonStyle style = button.getStyle();
        style.font.getData().setScale(scaleXY);
        button.setStyle(style);
    }
}
