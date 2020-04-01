package com.noah.treegame.gui;

import com.noah.treegame.game.Constants;
import com.noah.treegame.game.Game;
import com.noah.treegame.game.Rebirth;
import com.noah.treegame.game.UpdateManager;
import com.noah.treegame.utils.BigDouble;
import com.noah.treegame.utils.Vector3BD;
import org.newdawn.slick.Color;
import org.newdawn.slick.geom.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class GUIButton extends Button {

    public int id;
    public Vector3BD cost;
    public String costString;

    public List<GUIButton> parents = new ArrayList<>();
    public List<Vector2f> parentPos = new ArrayList<>();

    public boolean bought;
    public int menu;

    public GUIButton(int id, Color over, Color rectColor, Color textColor, Vector2f dims, int menu) {
        super(menu == 1 ? Constants.labels[id] : Constants.rbTreeLabels[id], menu == 1 ? Constants.points[id] : Constants.rbTreePoints[id], rectColor, textColor, over, dims);
        this.id = id;
        this.menu = menu;
        this.cost = menu == 1 ? Constants.costs[id] : new Vector3BD(Constants.rbTreeCosts[id], new BigDouble(0), new BigDouble(0));
        costString = getCostString();
        if (costString.equals("")) {
            costString = "Free";
        }
        costString = "Cost: " + costString;
        this.labels.add(costString);
    }
    public void selfParent() {
        parents.add(this);
        parentPos.add(new Vector2f(dims.x / 2, dims.y / 2).add(this.position));
    }

    public boolean parented() {
        if (parents.contains(this)) {
            return true;
        }
        if (menu == Constants.TREE_MENU) {
            for (int x : Constants.parents[id]) {
                if (!Game.treeButtons.get(x).bought) {
                    return false;
                }
            }
            return true;
        } else if (menu == Constants.RB_MENU) {
            for (int x: Constants.rbTreeParents[id]) {
                if (!Game.rbTreeButtons.get(x).bought) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public List<GUIButton> parents() {
        List<GUIButton> toRet = new ArrayList<>();

        if (menu == Constants.TREE_MENU) {
            for (int x: Constants.parents[id]) {
                toRet.add(Game.treeButtons.get(x));
            }
        } else if (menu == Constants.RB_MENU) {
            for (int x: Constants.rbTreeParents[id]) {
                toRet.add(Game.rbTreeButtons.get(x));
            }
        }

        return toRet;
    }

    public List<Vector2f> getParentPoses() {
        List<Vector2f> toRet = new ArrayList<>();

        for (GUIButton parent: parents) {
            toRet.add(new Vector2f(dims.x / 2, dims.y / 2).add(parent.position));
        }

        return toRet;
    }

    public void connect() {
        if (parents.size() == 0) {
            boolean condition = Constants.parents[id][0] != -1;
            if (menu == Constants.RB_MENU) {
                condition = Constants.rbTreeParents[id][0] != -1;
            }
            if (condition) {
                if (menu == 1) {
                    parents = parents();
                } else if (menu == 2) {
                    if (Constants.rbTreeParents[id][0] != -1) {
                        parents = parents();
                    } else {
                        selfParent();
                    }
                }
                assert parents != null;
                parentPos = getParentPoses();
            } else {
                selfParent();
            }
        }
    }

    public String getCostString() {
        String tempCostString = "";
        tempCostString += (this.cost.x.gt(new BigDouble(0)) ? Constants.bigDoubleToString(this.cost.x) + (menu == 1 ? "x" : "RP") : "");
        tempCostString += (this.cost.y.gt(new BigDouble(0)) ? Constants.bigDoubleToString(this.cost.y) + "y" : "");
        tempCostString += (this.cost.z.gt(new BigDouble(0)) ? Constants.bigDoubleToString(this.cost.z) + "z" : "");
        return tempCostString;
    }

    public void rebirthButtonSync() {
        if (Game.z.lt(new BigDouble(1, 11))) {
            this.labels.remove(1);
            this.labels.add(1, "Can't rebirth: z < 1e11");
            this.labels.remove(2);
            costString = "Cost: 1e11z";
            this.labels.add(2, costString);
            this.cost = new Vector3BD(new BigDouble(0), new BigDouble(0), new BigDouble(1, 11));
        } else {
            this.labels.remove(1);
            this.labels.add(1, "Rebirth for " + Constants.bigDoubleToString(Rebirth.calcPoints()) + "RP");
            this.labels.remove(2);
            this.labels.add(2, "Cost: " + Game.z + "z");
            this.cost = new Vector3BD(new BigDouble(0), new BigDouble(0), Game.z.copy());
        }
    }

    public void updateBought() {
        if (menu == Constants.TREE_MENU) {
            if (Constants.upgrades.get(id)) {
                bought = true;
                this.rectColor = colorOver;
            } else {
                bought = false;
                this.rectColor = this.keepColor;
            }
        } else if (menu == Constants.RB_MENU) {
            if (Constants.rbUpgrades.get(id)) {
                bought = true;
                this.rectColor = colorOver;
            } else {
                bought = false;
                this.rectColor = keepColor;
            }
        }
    }

    public void renderConnection() {
        if (visible) {
            Game.graphics.setLineWidth(30);
            Game.graphics.setColor(Color.white);
            for (Vector2f pP: parentPos) {
                Vector2f pos = new Vector2f(dims.x / 2, dims.y / 2).add(this.position);
                Game.graphics.drawLine(pos.x, pos.y, pP.x, pP.y);
            }
        }
    }

    public void buy() {
        boolean condition = this.cost.x.lte(Game.x) && this.cost.y.lte(Game.y) && this.cost.z.lte(Game.z);
        if (menu == Constants.RB_MENU) {
            condition = this.cost.x.lte(Game.RP);
        }
        if (condition && !this.bought && parented() && visible) {
            bought = true;
            this.rectColor = colorOver;
            if (menu == Constants.TREE_MENU) {
                Game.unroundedX.subtract(this.cost.x);
                Game.unroundedY.subtract(this.cost.y);
                Game.unroundedZ.subtract(this.cost.z);
                Constants.upgrades.put(id, true);
            } else if (menu == Constants.RB_MENU) {
                Game.RP.subtract(this.cost.x);
                Constants.rbUpgrades.put(id, true);
            }
            Game.calcRates();
            UpdateManager.addCurrency();
        }
    }
}