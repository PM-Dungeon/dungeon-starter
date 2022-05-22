package menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import java.util.ArrayList;
import java.util.List;

public class MenuScreenEntry {
    public static final float defaultScaleXY = 1.5f;
    public static final long defaultFadeOutTime = 2500;
    private final TextButton menuButton;
    private final List<MenuScreenDropEntry> entryButtons = new ArrayList<>();

    public MenuScreenEntry(String menuText) {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.BLUE;
        style.checkedFontColor = Color.YELLOW;
        menuButton = new TextButton(menuText, style);
        setFontSize(defaultScaleXY);
    }

    public void add(MenuScreenDropEntry entry) {
        entryButtons.add(entry);
    }

    public void remove(MenuScreenDropEntry entry) {
        entryButtons.remove(entry);
    }

    public void clear() {
        entryButtons.clear();
    }

    public void setFontSize(float scaleXY) {
        TextButton.TextButtonStyle style = menuButton.getStyle();
        style.font.getData().setScale(scaleXY);
        menuButton.setStyle(style);
    }

    public TextButton getMenuButton() {
        return menuButton;
    }

    public List<MenuScreenDropEntry> getEntryButtons() {
        return entryButtons;
    }
}
