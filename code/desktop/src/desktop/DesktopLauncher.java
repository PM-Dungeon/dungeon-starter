package desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import controller.LibgdxSetup;
import controller.MainController;
import tools.Constants;

public final class DesktopLauncher {
    public static void run(MainController mc) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setWindowSizeLimits(Constants.WINDOW_WIDTH, Constants.WINDOW_HEIGHT, 1000, 1000);
        // maxWidth: 1000 and maxHeight: 1000 affects the fullscreen behavior
        config.setForegroundFPS(Constants.FRAME_RATE);
        config.setTitle(Constants.WINDOW_TITLE);
        config.setWindowIcon(Constants.LOGO_PATH);
        // config.disableAudio(true);
        // uncomment this if you wish no audio
        new Lwjgl3Application(new LibgdxSetup(mc), config);
    }
}
