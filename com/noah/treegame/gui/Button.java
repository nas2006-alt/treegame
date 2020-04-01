package com.noah.treegame.gui;

import com.noah.treegame.game.Game;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.noah.treegame.game.Game.ttf;

public class Button {

    public String label;
    protected List<String> labels;
    public Rectangle rect;
    public Vector2f position;
    public Color keepColor;
    public Color rectColor;
    public Color textColor;
    public Color colorOver;
    public Vector2f dims;
    public boolean visible = true;

    public Button(String label, Vector2f position, Color rectColor, Color textColor, Color over, Vector2f dims) {
        this.label = label;
        this.rectColor = rectColor;
        this.textColor = textColor;
        this.dims = dims;
        this.rect = new Rectangle(position.x, position.y, dims.x, dims.y);
        this.position = position;
        this.colorOver = over;
        this.keepColor = this.rectColor;
        List<String> fakeLabels = Arrays.asList(label.split("\n"));
        labels = new ArrayList<>();
        labels.addAll(fakeLabels);
    }

    public boolean mouseOver() {
        return rect.contains(Game.game.getInput().getMouseX(), Game.game.getInput().getMouseY());
    }

    public void update() {
        if (mouseOver()) {
            Game.graphics.setColor(colorOver);
        }
    }

    public void render(Graphics graphics) {

        if (!visible) {
            return;
        }

        graphics.setLineWidth(3);
        graphics.setColor(this.rectColor);
        update();
        graphics.fill(this.rect);
        graphics.setColor(new Color(0, 0, 0));
        graphics.draw(this.rect);
        graphics.setColor(this.textColor);
        if (labels.size() > 1) {
            for (int x = 0; x < labels.size(); x++) {
                ttf.drawString((this.position.x) + dims.x/2 - ttf.getWidth(labels.get(x))/2f,
                        (this.position.y + dims.y/2) - ttf.getLineHeight()/2f + x * 20 - (labels.size() * 5), labels.get(x));
            }
        } else {
            ttf.drawString((this.position.x + dims.x/2) - ttf.getWidth(label) / 2f, (this.position.y + dims.y/2) - ttf.getLineHeight() / 2f, label);
        }
    }

    public String toString() {
        return this.label + ": (" + this.rect.getX() + ", " + this.rect.getY() + ")";
    }

}
