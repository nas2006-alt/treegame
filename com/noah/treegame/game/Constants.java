package com.noah.treegame.game;

import com.noah.treegame.utils.BigDouble;
import com.noah.treegame.utils.Vector3BD;
import org.newdawn.slick.geom.Vector2f;

import java.util.HashMap;
import java.util.Map;
public class Constants {

    public static int RAISE_X_1POINT5 = 0;
    public static int RAISE_CORE_X_25 = 1;
    public static int BEGIN_X = 2;
    public static int RAISE_CORE_X_10 = 3;
    public static int X_AND_Y_MULT_Z_POWER_POINT1 = 4;
    public static int MULT_X_10 = 5;
    public static int SQUARE_X = 6;
    public static int RAISE_X_5 = 7;
    public static int BEGIN_Y = 8;
    public static int MULT_Y_5 = 9;
    public static int MULT_Z_5 = 10;
    public static int RAISE_CORE_Z_3 = 11;
    public static int X_POWER_Y_POWER_ONE_SIXTH = 12;
    public static int MULT_X_5 = 13;
    public static int MULT_Z_5_NUM2 = 14;
    public static int SQUARE_Z = 15;
    public static int SQUARE_Y = 16;
    public static int MULT_Y_2 = 17;
    public static int SQUARE_X_NUM2 = 18;
    public static int MULT_Z_10 = 19;
    public static int CUBE_Z = 20;
    public static int REBIRTH = 21;
    public static int BEGIN_Z = 22;
    public static int MULT_Y_7 = 23;
    public static int MULT_Z_2 = 24;

    public static int TREE_MENU = 1;
    public static int RB_MENU = 2;

    public static int BEGIN_RB_TREE = 0;
    public static int MULT_ALL_4 = 1;
    public static int AUTO_BUY_X = 2;
    public static int PROD_MULT_UNSPENT_RP = 3;
    public static int START_WITH_1e5 = 4;
    public static int SQUARE_PROD = 5;
    public static int AUTO_BUY_Y = 6;
    public static int RP_MULT_50 = 7;
    public static int X_BOOSTS_RP = 8;
    public static int CUBE_PROD = 9;
    public static int AUTO_BUY_Z = 10;
    public static int RP_MULT_100 = 11;
    public static int Y_BOOSTS_RP = 12;
    public static int PROD_POW_5 = 13;
    public static int GAIN_1PERCENT_RP_PER_SEC = 14;
    public static int RP_MULT_1e5 = 15;

    public static BigDouble start_currency = new BigDouble(0);
    public static long start_time = System.currentTimeMillis();

    public static final String[] menu_labels = new String[] {
            "Tree Menu", "Rebirth Menu"
    };

    public static final Vector2f[] menu_points = new Vector2f[] {
            new Vector2f(100, 660),
            new Vector2f(100 + (Game.buttonDims.x + 40), 660)
    };

    public static String bigDoubleToString(BigDouble a) {
        String ret = a.getExponent() < 2 ?
                // Round mantissa to two decimal places
                String.valueOf(Math.round(100 * a.getMantissa() * Math.pow(10, a.getExponent())) / 100)
                : a.toString();
        return ret;
    }

    public static final float[] xPoses = new float[] {
            25, 25 + Game.buttonDims.x + 40,
            25 + (Game.buttonDims.x + 40) * 2,
            25 + (Game.buttonDims.x + 40) * 3,
            25 + (Game.buttonDims.x + 40) * 4,
    };

    public static final float[] yPoses = new float[] {
            50, 50 + Game.buttonDims.y + 40,
            50 + (Game.buttonDims.y + 40) * 2,
            50 + (Game.buttonDims.y + 40) * 3,
            50 + (Game.buttonDims.y + 40) * 4,
    };

    public static final String[] rbTreeLabels = new String[] {
            "Begin.", "Multiply x, y, and z\nproduction by 4.",
            "Automatically buy all\nupgrades that cost x.", "Multiply pre-Rebirth\nproduction by unspent RP.",
            // Row 2
            "Start with 1e5x, y, and z\nupon each rebirth.", "Square all pre-rebirth\nproduction.",
            "Automatically buy all\nupgrades that cost y.", "Gain a 50x multiplier to RP\ngain.",
            // Row 3
            "x boosts RP gain.", "Cube production of x, y, z.",
            "Automatically buy all\nupgrades that cost z.", "Gain an additional 100x\nmultiplier to RP gain.",
            // Row 4
            "y boosts RP gain.", "Raise the production of x, y,\nz to the power of 5.",
            "Gain 1% of RP you would\ngain on rebirth per second.", "Gain 10000x multiplier to\n RP gain."
    };

    public static final Vector2f[] rbTreePoints = new Vector2f[] {
            new Vector2f(xPoses[0], yPoses[0]),
            new Vector2f(xPoses[1], yPoses[0]),
            new Vector2f(xPoses[2], yPoses[0]),
            new Vector2f(xPoses[3], yPoses[0]),
            // Row 2
            new Vector2f(xPoses[0], yPoses[1]),
            new Vector2f(xPoses[1], yPoses[1]),
            new Vector2f(xPoses[2], yPoses[1]),
            new Vector2f(xPoses[3], yPoses[1]),
            // Row 3
            new Vector2f(xPoses[0], yPoses[2]),
            new Vector2f(xPoses[1], yPoses[2]),
            new Vector2f(xPoses[2], yPoses[2]),
            new Vector2f(xPoses[3], yPoses[2]),
            // Row 4
            new Vector2f(xPoses[0], yPoses[3]),
            new Vector2f(xPoses[1], yPoses[3]),
            new Vector2f(xPoses[2], yPoses[3]),
            new Vector2f(xPoses[3], yPoses[3])
    };

    public static final int[][] rbTreeParents =  {
            {-1}, {0}, {1}, {2},
            // Row 2
            {0}, {1}, {2}, {3},
            // Row 3
            {4}, {5}, {6}, {7},
            // Row 4
            {8}, {9}, {10}, {11}
    };

    public static final BigDouble[] rbTreeCosts = {
            // Row 1
            new BigDouble(0),
            new BigDouble(1),
            new BigDouble(1),
            new BigDouble(3),
            // Row 2
            new BigDouble(10),
            new BigDouble(15),
            new BigDouble(9),
            new BigDouble(50),
            // Row 3
            new BigDouble(5, 5),
            new BigDouble(1, 6),
            new BigDouble(1, 3),
            new BigDouble(5, 3),
            // Row 4
            new BigDouble(2.5, 6),
            new BigDouble(1, 8),
            new BigDouble(2.5, 6),
            new BigDouble(1, 7)
    };

    public static final Vector2f[] points = new Vector2f[] {
        new Vector2f(xPoses[0], yPoses[0]),
            new Vector2f(xPoses[1], yPoses[0]),
            new Vector2f(xPoses[2], yPoses[0]),
            new Vector2f(xPoses[3], yPoses[0]),
            new Vector2f(xPoses[4], yPoses[0]),
            // Row 2
            new Vector2f(xPoses[0], yPoses[1]),
            new Vector2f(xPoses[1], yPoses[1]),
            new Vector2f(xPoses[2], yPoses[1]),
            new Vector2f(xPoses[3], yPoses[1]),
            new Vector2f(xPoses[4], yPoses[1]),
            // Row 3
            new Vector2f(xPoses[0], yPoses[2]),
            new Vector2f(xPoses[1], yPoses[2]),
            new Vector2f(xPoses[2], yPoses[2]),
            new Vector2f(xPoses[3], yPoses[2]),
            new Vector2f(xPoses[4], yPoses[2]),
            // Row 4
            new Vector2f(xPoses[0], yPoses[3]),
            new Vector2f(xPoses[1], yPoses[3]),
            new Vector2f(xPoses[2], yPoses[3]),
            new Vector2f(xPoses[3], yPoses[3]),
            new Vector2f(xPoses[4], yPoses[3]),
            // Row 5
            new Vector2f(xPoses[0], yPoses[4]),
            new Vector2f(xPoses[1], yPoses[4]),
            new Vector2f(xPoses[2], yPoses[4]),
            new Vector2f(xPoses[3], yPoses[4]),
            new Vector2f(xPoses[4], yPoses[4]),
    };

    public static final String[] labels = new String[] {
        "Raise x production to\nthe power of 1.5.",
            "Increase core production\nof x by 25.",
            "Begin production of x.",
            "Increase core production\nof x by 10.",
            "Multiply x and y\nproduction by z^0.1.",
            // Row 2
            "Multiply x production\nby 10.",
            "Square x production.",
            "Increase x production\nby 5.",
            "Enter the era of y.",
            "Multiply y production\nby 5.",
            // Row 3
            "Multiply z production\nby 5.",
            "Increase core z\nproduction by 3",
            "x production is boosted\nby y^1/6.",
            "Multiply x production\nby 5.",
            "Multiply z production\nby 5.",
            // Row 4
            "Square z production.",
            "Square y production.",
            "Multiply y production\nby 2.",
            "Square x production.",
            "Multiply z production\nby 10.",
            // Row 5
            "Cube z production",
            "Rebirth\n?",
            "Unlock z.",
            "Multiply y production\nby 7.",
            "Multiply z production\nby 2."
    };

    public static final BigDouble z = new BigDouble(0);

    public static final Vector3BD[] costs = new Vector3BD[] {
        new Vector3BD(new BigDouble(2, 4), z,z),
            new Vector3BD(new BigDouble(2.5, 3), z, z),
            new Vector3BD(z, z, z),
            new Vector3BD(new BigDouble(100), z, z),
            new Vector3BD(z, z, new BigDouble(1, 3)),
            // Row 2
            new Vector3BD(new BigDouble(1, 6), z, z),
            new Vector3BD(new BigDouble(250), z, z),
            new Vector3BD(new BigDouble(5), z, z),
            new Vector3BD(new BigDouble(1, 8), z, z),
            new Vector3BD(new BigDouble(1, 8), z,z),
            // Row 3
            new Vector3BD(z, z, new BigDouble(400)),
            new Vector3BD(z, z, new BigDouble(15)),
            new Vector3BD(z, new BigDouble(10), z),
            new Vector3BD(z, new BigDouble(250), z),
            new Vector3BD(z, new BigDouble(5, 4), z),
            // Row 4
            new Vector3BD(z, z, new BigDouble(1, 3)),
            new Vector3BD(new BigDouble(1, 9),z,z),
            new Vector3BD(z, new BigDouble(100),z),
            new Vector3BD(z, new BigDouble(3.3, 3),z),
            new Vector3BD(new BigDouble(7.1, 17),z,z),
            // Row 5
            new Vector3BD(z,z, new BigDouble(1, 7)),
            new Vector3BD(z,z, new BigDouble(1, 11)),
            new Vector3BD(new BigDouble(5, 16), z, z),
            new Vector3BD(z, new BigDouble(1, 4), z),
            new Vector3BD(z, new BigDouble(1, 5), z)
    };

    /* Map
    * 0  1  2  3  4
    * 5  6  7  8  9
    * 10 11 12 13 14
    * 15 16 17 18 19
    * 20 21 22 23 24
     */

    public static int[][] parents = {
        // Row 1
            {1}, {6}, {-1}, {2}, {9},
        // Row 2
            {6}, {7}, {2}, {2}, {8},
        // Row 3
            {11}, {16}, {8}, {12}, {13},
        // Row 4
            {10}, {17}, {12}, {13}, {14},
        // Row 5
            {15}, {20}, {18}, {22}, {23}
    };

    public static Map<Integer, Boolean> upgrades = new HashMap<>();
    public static Map<Integer, Boolean> rbUpgrades = new HashMap<>();

    public static void initUpgrades() {
        for (int x = 0; x < costs.length; x++) {
            upgrades.put(x, false);
        }

        for (int x = 0; x < rbTreeCosts.length; x++) {
            rbUpgrades.put(x, false);
        }
    }

    public static Object[] defaultFile = new Object[] {
            start_currency, start_currency, start_currency, upgrades, start_time, start_currency, rbUpgrades
    };
}
