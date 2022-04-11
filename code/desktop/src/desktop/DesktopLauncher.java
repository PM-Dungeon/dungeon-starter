package desktop;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import controller.LibgdxSetup;
import controller.MainController;
import tools.Constants;

public final class DesktopLauncher {
    public static void run(MainController mc) {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(Constants.FRAME_RATE);
        config.setWindowIcon(Constants.LOGO_PATH);
        // config.disableAudio(true);
        new Lwjgl3Application(new LibgdxSetup(mc), config);
    }
}
