package desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.DungeonApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import controller.LibgdxSetup;
import controller.MainController;
import tools.Constants;

public final class DesktopLauncher {
    public static void run(MainController mc) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.width = Constants.WINDOW_WIDTH;
        config.height = Constants.WINDOW_HEIGHT;
        config.foregroundFPS = Constants.FRAME_RATE;
        config.title = Constants.WINDOW_TITLE;
        config.addIcon(Constants.LOGO_PATH, Files.FileType.Internal);
        new DungeonApplication(new LibgdxSetup(mc), config, 0);
    }
}
