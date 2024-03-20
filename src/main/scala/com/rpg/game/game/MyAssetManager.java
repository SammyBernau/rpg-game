package com.rpg.game.game;

import com.badlogic.gdx.assets.AssetManager;
import games.rednblack.editor.renderer.resources.AsyncResourceManager;
import games.rednblack.editor.renderer.resources.ResourceManagerLoader;

public class MyAssetManager {
    AssetManager assetManager;
    public MyAssetManager() {
        assetManager = new AssetManager();
        assetManager.setLoader(AsyncResourceManager.class, new ResourceManagerLoader(assetManager.getFileHandleResolver()));
    }

    public AssetManager getAssetManager() {return this.assetManager;}


}
