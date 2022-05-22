package menu;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public abstract class EntryListener extends ClickListener {

    private TextButton button;

    public void setButton(TextButton button) {
        this.button = button;
        this.button.addListener(this);
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        whenClicked(event, x, y);
        new Thread(
                        () -> {
                            try {
                                Thread.sleep(MenuScreenEntry.defaultFadeOutTime);
                                button.setChecked(false);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        })
                .start();
    }

    public abstract void whenClicked(InputEvent event, float x, float y);
}
