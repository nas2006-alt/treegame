package com.noah.treegame.game;

import com.noah.treegame.gui.GUIButton;
import com.noah.treegame.utils.BigDouble;

public class Rebirth {

    public static BigDouble calcPoints() {
        if (Game.z.lt(new BigDouble(1, 11))) {
            return new BigDouble(0);
        } else {
            BigDouble rp;
            rp = new BigDouble(Game.z.Divide(new BigDouble(1, 10)).log10()).Pow(0.5f).Floor();

            if (Constants.rbUpgrades.get(Constants.RP_MULT_50)) {
                rp.multiply(50);
            } if (Constants.rbUpgrades.get(Constants.RP_MULT_100)) {
                rp.multiply(100);
            } if (Constants.rbUpgrades.get(Constants.RP_MULT_1e5)) {
                rp.multiply(10000);
            } if (Constants.rbUpgrades.get(Constants.X_BOOSTS_RP)) {
                BigDouble mult = BigDouble.Max(new BigDouble(1), new BigDouble(
                        Game.x.Divide(new BigDouble(1, 20)).log10()).pow(1/4f).floor()
                );
                rp.multiply(mult);
            } if (Constants.rbUpgrades.get(Constants.Y_BOOSTS_RP)) {
                BigDouble mult = BigDouble.Max(new BigDouble(1), new BigDouble(
                        Game.y.Divide(new BigDouble(1, 15)).log10()).pow(1/3f).floor()
                );
                rp.multiply(mult);
            }

            return rp;
        }
    }

    public static void rebirth() {
        Game.RP.add(calcPoints());
        for (int x = 0; x < Constants.upgrades.size(); x++) {
            Constants.upgrades.replace(x, false);
        }
        for (GUIButton b: Game.treeButtons) {
            b.updateBought();
        }
        Game.setDefaults();
        Game.save();
    }

}
