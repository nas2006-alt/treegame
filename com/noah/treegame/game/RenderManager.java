package com.noah.treegame.game;

import com.noah.treegame.gui.GUIButton;
import com.noah.treegame.gui.MenuButton;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

import static com.noah.treegame.game.Game.*;

public class RenderManager {

    public static void render(Graphics g) {
        for (GUIButton b: treeButtons) {
            b.renderConnection();
        }

        for (GUIButton b: treeButtons) {
            b.render(g);
        }

        for (MenuButton b: Game.menuButtons) {
            b.render(g);
        }

        for (GUIButton b: rbTreeButtons) {
            b.renderConnection();
        }

        for (GUIButton b: rbTreeButtons) {
            b.render(g);
        }
    }

    public static void renderStrings(TrueTypeFont ttf) {
        String tempX = Constants.bigDoubleToString(x);
        String tempY = Constants.bigDoubleToString(y);
        String tempZ = Constants.bigDoubleToString(z);
        ttf.drawString(20, 20, tempX + "x, " + tempY + "y, " + tempZ + "z, " + Constants.bigDoubleToString(RP) + "RP");
        ttf.drawString(900, 20, "Saving in: " + (5 - Math.round((System.currentTimeMillis() - time) / 1000f)) + "s.");
    }

}
