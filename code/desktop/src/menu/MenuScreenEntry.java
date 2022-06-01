package menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.VerticalGroup;
import java.util.ArrayList;
import java.util.List;

public class MenuScreenEntry {
    public static final float defaultScaleXY = 1f;
    private final TextButton menuButton;
    private final List<MenuScreenDropEntry> entryButtons = new ArrayList<>();
    private final VerticalGroup vg = new VerticalGroup();

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

    public void showVg() {
        vg.clear();
        for (MenuScreenDropEntry e : entryButtons) {
            vg.addActor(e.getButton());
        }
    }

    public void hideVg() {
        for (MenuScreenDropEntry e : entryButtons) {
            e.getButton().setChecked(false);
        }
        vg.clear();
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

    public VerticalGroup getVg() {
        return vg;
    }
}
