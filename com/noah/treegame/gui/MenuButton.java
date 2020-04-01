package com.noah.treegame.gui;

import com.noah.treegame.game.Constants;
import com.noah.treegame.game.Game;
import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Vector2f;

import java.util.List;

public class MenuButton extends Button {

    public int id;
    public boolean enabled = false;
    public List<GUIButton> linked;

    public MenuButton(int id, Color over, Color rectColor, Color textColor, Vector2f dims, List<GUIButton> linked) {
        super(Constants.menu_labels[id], Constants.menu_points[id], rectColor, textColor, over, dims);
        // System.out.println(linked);

        this.id = id;
        this.linked = linked;
        if (id != 0) {
            for (Button b: linked) {
                b.visible = false;
            }
        }
    }

    public void updateColor() {
        if (enabled) {
            for (List<GUIButton> buttons: Game.buttonLists) {
                for (GUIButton button: buttons) {
                    button.visible = false;
                }
            }
            for (Button b : linked) {
                b.visible = true;
            }
            this.rectColor = this.colorOver;
        } else {
            this.rectColor = this.keepColor;
        }
    }

    public void click() {

        for (MenuButton b: Game.menuButtons) {
            if (b.id != this.id) {
                b.enabled = false;
                b.updateColor();
            }
        }
        enabled = true;
        updateColor();
    }
}
