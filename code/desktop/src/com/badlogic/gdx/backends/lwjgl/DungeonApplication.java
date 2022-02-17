package com.badlogic.gdx.backends.lwjgl;

import com.badlogic.gdx.ApplicationListener;

/**
 * An extended LwjglApplication which allows to define the Statuscode, which should be returned on
 * forceExit.
 */
public class DungeonApplication extends LwjglApplication {

    protected boolean forceExit = false;
    protected int statusCode = 0;

    /**
     * Creates a new DungeonApplication.
     *
     * @param listener
     * @param config
     * @param statuscode The Exitcode which the Operating System needs. Some macOS versions may need
     *     -1 to successfully shutdown.
     */
    public DungeonApplication(
            ApplicationListener listener, LwjglApplicationConfiguration config, int statuscode) {
        super(listener, config);
        if (config.forceExit) {
            this.graphics.config.forceExit = false;
            this.forceExit = true;
            this.statusCode = statuscode;
        }
    }

    @Override
    protected void mainLoop() {
        super.mainLoop();
        if (forceExit) {
            System.exit(statusCode);
        }
    }
}
