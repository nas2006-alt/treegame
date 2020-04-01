package com.noah.treegame.game;

import com.noah.treegame.gui.GUIButton;
import com.noah.treegame.gui.MenuButton;
import com.noah.treegame.utils.BigDouble;
import org.newdawn.slick.Input;

import static com.noah.treegame.game.Game.*;

public class UpdateManager {

    public static void addCurrency() {
        unroundedX.add(xRate.Divide(60));
        unroundedY.add(yRate.Divide(60));
        unroundedZ.add(zRate.Divide(60));
        x = unroundedX.Round(2 - unroundedX.getExponent() - 1);
        y = unroundedY.Round(2 - unroundedY.getExponent() - 1);
        z = unroundedZ.Round(2 - unroundedZ.getExponent() - 1);
    }

    public static void checkCertainUpgrades() {
        if (Constants.upgrades.get(Constants.REBIRTH)) {
            Rebirth.rebirth();
        }
    }

    public static void checkMouse() {
        if (game.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
            for (GUIButton b: treeButtons) {
                if (b.mouseOver()) {
                    b.buy();
                }
            }
            for (MenuButton b: menuButtons) {
                if (b.mouseOver()) {
                    b.click();
                }
            }
            for (GUIButton b: rbTreeButtons) {
                if (b.mouseOver()) {
                    b.buy();
                }
            }
        }
    }

    public static void autobuy() {
        for (GUIButton b: treeButtons) {
            if (Constants.rbUpgrades.get(Constants.AUTO_BUY_X)) {

                if (b.cost.x.gt(new BigDouble(0)) || b.id == Constants.BEGIN_X) {
                    b.buy();
                }

                if (Constants.rbUpgrades.get(Constants.AUTO_BUY_Y)) {

                    if (b.cost.y.gt(new BigDouble(0))) {
                        b.buy();
                    }

                    if (Constants.rbUpgrades.get(Constants.AUTO_BUY_Z)) {

                        if (b.cost.z.gt(new BigDouble(0))) {
                            b.buy();
                        }

                        if (Constants.rbUpgrades.get(Constants.GAIN_1PERCENT_RP_PER_SEC)) {
                            RP.add(Rebirth.calcPoints());
                        }

                    }
                }
            }
        }
    }

}
