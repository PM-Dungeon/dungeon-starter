package desktop;

import controller.MainController;

public class MyGame extends MainController {
    private Hero hero;

    @Override
    public void setup() {
        // Test
        hero = new Hero(batch, painter);

        entityController.add(hero);

        camera.follow(hero);

        levelAPI.loadLevel();
    }

    @Override
    protected void beginFrame() {}

    @Override
    protected void endFrame() {}

    @Override
    public void onLevelLoad() {
        hero.setLevel(levelAPI.getCurrentLevel());
    }

    public static void main(String[] args) {
        DesktopLauncher.run(new MyGame());
    }
}
