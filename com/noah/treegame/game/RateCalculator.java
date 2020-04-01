package com.noah.treegame.game;

import com.noah.treegame.utils.BigDouble;

public class RateCalculator {

    public static BigDouble calcX() {
        BigDouble res = new BigDouble(0);
        if (Constants.upgrades.get(Constants.RAISE_CORE_X_25)) {
            res.add(new BigDouble(25));
        } if (Constants.upgrades.get(Constants.RAISE_CORE_X_10)) {
            res.add(new BigDouble(10));
        } if (Constants.upgrades.get(Constants.RAISE_X_1POINT5)) {
            res.pow(1.5);
        } if (Constants.upgrades.get(Constants.BEGIN_X)) {
            res.add(new BigDouble(1));
        } if (Constants.upgrades.get(Constants.MULT_X_10)) {
            res.multiply(10);
        } if (Constants.upgrades.get(Constants.SQUARE_X)) {
            res.pow(2);
        } if (Constants.upgrades.get(Constants.RAISE_X_5)) {
            res.add(new BigDouble(5));
        } if (Constants.upgrades.get(Constants.MULT_X_5)) {
            res.multiply(5);
        } if (Constants.upgrades.get(Constants.SQUARE_X_NUM2)) {
            res.pow(2);
        } if (Constants.rbUpgrades.get(Constants.MULT_ALL_4)) {
            res.multiply(5);
        } if (Constants.rbUpgrades.get(Constants.SQUARE_PROD)) {
            res.pow(2);
        } if (Constants.rbUpgrades.get(Constants.CUBE_PROD)) {
            res.pow(3);
        } if (Constants.rbUpgrades.get(Constants.PROD_POW_5)) {
            res.pow(5);
        } if (Constants.upgrades.get(Constants.X_AND_Y_MULT_Z_POWER_POINT1)) {
            res.multiply(Game.z.Pow(0.1));
        } if (Constants.upgrades.get(Constants.X_POWER_Y_POWER_ONE_SIXTH)) {
            res.multiply(Game.unroundedY.Pow(0.16666666667));
        } if (Constants.rbUpgrades.get(Constants.PROD_MULT_UNSPENT_RP)) {
            res.multiply(BigDouble.Max(new BigDouble(1), Game.RP));
        }

        System.out.println(res);

        return res;
    }

    public static BigDouble calcY() {

        BigDouble res = new BigDouble(0);

        if (Constants.upgrades.get(Constants.BEGIN_Y)) {
            res.add(new BigDouble(1));
        } if (Constants.upgrades.get(Constants.MULT_Y_5)) {
            res.multiply(5);
        } if (Constants.upgrades.get(Constants.SQUARE_Y)) {
            res.pow(2);
        } if (Constants.upgrades.get(Constants.MULT_Y_2)) {
            res.multiply(2);
        } if (Constants.upgrades.get(Constants.MULT_Y_7)) {
            res.multiply(7);
        } if (Constants.rbUpgrades.get(Constants.MULT_ALL_4)) {
            res.multiply(5);
        } if (Constants.rbUpgrades.get(Constants.SQUARE_PROD)) {
            res.pow(2);
        } if (Constants.rbUpgrades.get(Constants.CUBE_PROD)) {
            res.pow(3);
        } if (Constants.rbUpgrades.get(Constants.PROD_POW_5)) {
            res.pow(5);
        } if (Constants.upgrades.get(Constants.X_AND_Y_MULT_Z_POWER_POINT1)) {
            res.multiply(Game.z.Pow(0.1));
        } if (Constants.rbUpgrades.get(Constants.PROD_MULT_UNSPENT_RP)) {
            res.multiply(BigDouble.Max(new BigDouble(1), Game.RP));
        }

        return res;
    }

    public static BigDouble calcZ() {

        BigDouble res = new BigDouble(0);

        if (Constants.upgrades.get(Constants.RAISE_CORE_Z_3)) {
            res.add(new BigDouble(3));
        } if (Constants.upgrades.get(Constants.MULT_Z_5)) {
            res.multiply(5);
        } if (Constants.upgrades.get(Constants.MULT_Z_5_NUM2)) {
            res.multiply(5);
        } if (Constants.upgrades.get(Constants.SQUARE_Z)) {
            res.pow(2);
        } if (Constants.upgrades.get(Constants.MULT_Z_10)) {
            res.multiply(10);
        } if (Constants.upgrades.get(Constants.CUBE_Z)) {
            res.pow(3);
        } if (Constants.upgrades.get(Constants.BEGIN_Z)) {
            res.add(new BigDouble(1));
        } if (Constants.upgrades.get(Constants.MULT_Z_2)) {
            res.multiply(2);
        } if (Constants.rbUpgrades.get(Constants.MULT_ALL_4)) {
            res.multiply(5);
        } if (Constants.rbUpgrades.get(Constants.SQUARE_PROD)) {
            res.pow(2);
        } if (Constants.rbUpgrades.get(Constants.CUBE_PROD)) {
            res.pow(3);
        } if (Constants.rbUpgrades.get(Constants.PROD_POW_5)) {
            res.pow(5);
        } if (Constants.rbUpgrades.get(Constants.PROD_MULT_UNSPENT_RP)) {
            res.multiply(BigDouble.Max(new BigDouble(1), Game.RP));
        }

        return res;

    }

}
