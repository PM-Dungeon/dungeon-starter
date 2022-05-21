package menu;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import java.util.ArrayList;
import java.util.List;

public class DropDownMenu {
    private final TextButton menuButton;
    private final List<TextButton> entryButtons = new ArrayList<>();

    public DropDownMenu(String menuText, List<String> entries, List<ClickListener> listeners) {
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.font = new BitmapFont();
        style.fontColor = Color.BLUE;
        style.checkedFontColor = Color.YELLOW;
        menuButton = new TextButton(menuText, style);
        for (int i = 0; i < entries.size(); i++) {
            TextButton b = new TextButton(entries.get(i), style);
            b.addListener(listeners.get(i));
            entryButtons.add(b);
        }
        setFontSize(1.1f);
    }

    public void setFontSize(float scaleAmount) {
        TextButton.TextButtonStyle style = menuButton.getStyle();
        style.font.getData().scale(scaleAmount);
        menuButton.setStyle(style);
        for (TextButton b : entryButtons) {
            b.setStyle(style);
        }
    }

    public TextButton getMenuButton() {
        return menuButton;
    }

    public List<TextButton> getEntryButtons() {
        return entryButtons;
    }
}
