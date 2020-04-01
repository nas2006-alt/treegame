package com.noah.treegame.saving;

import com.noah.treegame.game.Constants;
import com.noah.treegame.game.Game;
import com.noah.treegame.game.RateCalculator;
import com.noah.treegame.utils.BigDouble;

import java.util.ArrayList;
import java.util.List;

public class GetSaveData {

    public static String gameToString() {
        StringBuilder res = new StringBuilder();

        res.append(Game.x).append("\n");
        res.append(Game.y).append("\n");
        res.append(Game.z).append("\n");

        for (int x = 0; x < Constants.upgrades.size(); x++) {
            res.append(Constants.upgrades.get(x)).append(",");
        }
        res.append("\n").append(Game.game.getTime());
        res.append("\n").append(Game.RP).append("\n");


        for (int x = 0; x < Constants.rbUpgrades.size(); x++) {
            res.append(Constants.rbUpgrades.get(x)).append(",");
        }

        //System.out.println(res);

        return res.toString();
    }

    public static void stringToGame(String file) {
        String[] lines = LoadSave.readSave(file);
        lines = testDefaults(lines);
        // Currencies
        Game.unroundedX = new BigDouble(lines[0]);
        Game.unroundedY = new BigDouble(lines[1]);
        Game.unroundedZ = new BigDouble(lines[2]);
        Game.x = Game.unroundedX.Round(2 - Game.unroundedX.getExponent() - 1);
        Game.y = Game.unroundedY.Round(2 - Game.unroundedY.getExponent() - 1);
        Game.z = Game.unroundedZ.Round(2 - Game.unroundedZ.getExponent() - 1);

        // Upgrades
        for (int x = 0; x < Constants.costs.length; x++) {
            boolean value;
            try {
                value = Boolean.parseBoolean(lines[3].split(",")[x]);
            } catch (ArrayIndexOutOfBoundsException e) {
                value = false;
            }
            Constants.upgrades.put(x, value);
        }

        // Rebirth
        try {
            Game.RP = new BigDouble(lines[5]);
        } catch (Exception e) {
            Game.RP = new BigDouble(0);
        }

        // Rebirth Upgrades
        for (int x = 0; x < Constants.rbTreeCosts.length; x++) {
            boolean value;
            try {
                value = Boolean.parseBoolean(lines[6].split(",")[x]);
            } catch (ArrayIndexOutOfBoundsException e) {
                value = false;
            }
            Constants.rbUpgrades.put(x, value);
        }

        // Offline Progress
        Game.xRate = RateCalculator.calcX();
        Game.yRate = RateCalculator.calcY();
        Game.zRate = RateCalculator.calcZ();
        long millisOffline = Game.game.getTime() - Long.parseLong(lines[4]);
        if (millisOffline > 1e8) {
            millisOffline = (long) 1e8;
        }
        Game.unroundedX.add(Game.xRate.Multiply((millisOffline / 1000f)));
        Game.unroundedY.add(Game.yRate.Multiply((millisOffline / 1000f)));
        Game.unroundedZ.add(Game.zRate.Multiply((millisOffline / 1000f)));
    }

    public static String[] testDefaults(String[] lines) {
        List<String> newLines = new ArrayList<>();
        for (int l = 0; l < Constants.defaultFile.length; l++) {
            String line;
            try {
                line = lines[l];
            } catch (ArrayIndexOutOfBoundsException e) {
                line = null;
            }
            if (line == null) {
                line = String.valueOf(Constants.defaultFile[l]);
            }
            newLines.add(line);
        }
        return newLines.toArray(new String[0]);
    }

}
