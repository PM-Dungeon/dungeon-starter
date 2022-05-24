package asset_manager;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.ParticleEffectLoader.ParticleEffectParameter;
import com.badlogic.gdx.assets.loaders.SkinLoader.SkinParameter;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class MyAssetManager {
    public final AssetManager myAssetManager = new AssetManager();

    // Textures
    private final String imageTexturePack = "images/texture.pack";

    // Sounds
    private final String torcheTwinkleSound = "sounds/torcheTwinkle.wav";

    // Fonts
    private final String pixelFont = "fonts/pixelFont.fnt";

    // Particles
    private final String particlePack = "particles/particle.pack";
    private final String torchParticleEffect = "particles/torch.p";

    // Skins
    private final String skinJson = "ui/skin.json";
    private final String skinParameterAtlas = "ui/skin.atlas";

    /**
     * Load the textures from the given texture pack which is created by a texture
     * atlas.
     */
    public void loadImages() {
        myAssetManager.load(imageTexturePack, TextureAtlas.class);
    }

    /**
     * Load the sounds from the given sound pack.
     */
    public void loadSounds() {
        myAssetManager.load(torcheTwinkleSound, Sound.class);
    }

    /**
     * Load the Bitmap font from the .fnt Font file
     */
    public void loadFonts() {
        myAssetManager.load(pixelFont, BitmapFont.class);
    }

    public void loadParticleEffects() {
        ParticleEffectParameter myParticleEffectParameter = new ParticleEffectParameter();
        myParticleEffectParameter.atlasFile = particlePack;

        myAssetManager.load(torchParticleEffect, ParticleEffect.class, myParticleEffectParameter);

    }

    public void loadSkin() {
        SkinParameter mySkinParameter = new SkinParameter(skinParameterAtlas);
        myAssetManager.load(skinJson, Skin.class, mySkinParameter);
    }

}
