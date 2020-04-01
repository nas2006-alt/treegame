package com.noah.treegame.game;

import com.noah.treegame.gui.GUIButton;
import com.noah.treegame.gui.MenuButton;
import com.noah.treegame.saving.GetSaveData;
import com.noah.treegame.saving.Save;
import com.noah.treegame.utils.BigDouble;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Game extends BasicGame {

    public static AppGameContainer game;
    public static Graphics graphics;
    public static List<GUIButton> treeButtons = new ArrayList<>();
    public static List<GUIButton> rbTreeButtons = new ArrayList<>();
    public static List<MenuButton> menuButtons = new ArrayList<>();
    public static List<List<GUIButton>> buttonLists = new ArrayList<>();

    public static BigDouble x, y, z, RP;
    public static BigDouble unroundedX, unroundedY, unroundedZ;
    public static BigDouble xRate, yRate, zRate;
    private static List<BigDouble> currencies = new ArrayList<>();


    public static java.awt.Font f = new java.awt.Font("Arial", java.awt.Font.PLAIN, 16);
    public static TrueTypeFont ttf;


    public static Vector2f buttonDims = new Vector2f(175, 87.5f);
    public static Vector2f menuButtonDims = new Vector2f(100, 50);

    public static long time;

    private static String saveFileName = "save.txt";

    public Game(String title) {
        super(title);
    }

    public void run() throws SlickException {
        game = new AppGameContainer(this);
        game.setDisplayMode(1080, 720, false);

        game.setVSync(true);
        game.setTargetFrameRate(60);
        game.setShowFPS(false);
        game.setUpdateOnlyWhenVisible(false);
        game.start();
    }

    public static void setDefaultsNoRB() {
        unroundedX = new BigDouble(0);
        unroundedY = new BigDouble(0);
        unroundedZ = new BigDouble(0);
        xRate = new BigDouble(0);
        yRate = new BigDouble(0);
        zRate = new BigDouble(0);
        x = new BigDouble(0);
        y = new BigDouble(0);
        z = new BigDouble(0);
    }

    public static void setDefaults() {
        // Default currencies
        if (!Constants.rbUpgrades.get(Constants.START_WITH_1e5)) {
            unroundedX = new BigDouble(0);
            unroundedY = new BigDouble(0);
            unroundedZ = new BigDouble(0);
        } else {
            unroundedX = new BigDouble(1, 5);
            unroundedY = new BigDouble(1, 5);
            unroundedZ = new BigDouble(1, 5);
        }
        xRate = new BigDouble(0);
        yRate = new BigDouble(0);
        zRate = new BigDouble(0);
        x = new BigDouble(0);
        y = new BigDouble(0);
        z = new BigDouble(0);
    }

    @Override
    public void init(GameContainer gameContainer) throws SlickException {

        // Initialize Stuff
        graphics = game.getGraphics();
        graphics.setBackground(new Color(0.7f, 0.7f, 0.7f, 1.0f));
        ttf = new TrueTypeFont(f, false);
        Color over = new Color(0.4f, 0.4f, 0.4f);

        // Add buttons to lists
        for (int x = 0; x < 25; x++) {
            treeButtons.add(new GUIButton(x, over,
                    new Color(0.2f, 0.2f, 0.2f, 1.0f),  new Color(1, 1, 1, 1),
                    buttonDims, 1));
        }
        for (int x = 0; x < Constants.rbTreeLabels.length; x++) {
            rbTreeButtons.add(new GUIButton(x,
                    new Color(255, 0, 255), new Color(191, 0, 191), new Color(1, 1, 1, 1),
                    buttonDims, 2));
        }
        buttonLists.add(treeButtons);
        buttonLists.add(rbTreeButtons);
        for (int x = 0; x < 2; x++) {
            menuButtons.add(new MenuButton(x, over,
                    new Color(0.2f, 0.2f, 0.2f, 1.0f),  new Color(1, 1, 1, 1),
                    menuButtonDims, buttonLists.get(x)));
            menuButtons.get(0).enabled = true;
            menuButtons.get(0).rectColor = menuButtons.get(0).colorOver;
        }

        // Connect button with parent
        for (GUIButton button: treeButtons) {
            button.connect();
        }

        for (GUIButton b: rbTreeButtons) {
            b.connect();
        }
        // If save file exists, do save stuff
        if (Save.getFileOutsideJar(saveFileName, false).exists()) {
            GetSaveData.stringToGame(saveFileName);
            for (GUIButton b: treeButtons) {
                b.updateBought();
            }
            for (GUIButton b: rbTreeButtons) {
                b.updateBought();
            }
        // Otherwise set to starting game, initialize upgrades to all be false
        } else {
            RP = new BigDouble(0);
            setDefaultsNoRB();
            Constants.initUpgrades();
        }

        // All currencies in the game

        currencies.add(x);
        currencies.add(y);
        currencies.add(z);
        currencies.add(unroundedX);
        currencies.add(unroundedY);
        currencies.add(unroundedZ);
        currencies.add(RP);
        treeButtons.get(Constants.REBIRTH).rebirthButtonSync();
    }

    public static void calcRates() {
        xRate = RateCalculator.calcX();
        yRate = RateCalculator.calcY();
        zRate = RateCalculator.calcZ();
        System.out.println(xRate);
    }

    public static void fixCurrency() {
        for (BigDouble c: currencies) {
            if (Double.isNaN(c.getMantissa())) {
                currencies.set(currencies.indexOf(c), new BigDouble(1, c.getExponent()));
            }
        }
    }

    @Override
    public void update(GameContainer gameContainer, int i) throws SlickException {
        treeButtons.get(Constants.REBIRTH).rebirthButtonSync();
        fixCurrency();

        for (GUIButton b: treeButtons) {
            b.update();
        }

        for (MenuButton b: menuButtons) {
            b.update();
        }

        for (GUIButton b: rbTreeButtons) {
            b.update();
        }

        UpdateManager.checkMouse();
        UpdateManager.autobuy();

        // Add stuff to unrounded variables and round those to their shown forms
        UpdateManager.addCurrency();

        // If you have rebirth upgrade, rebirth
        UpdateManager.checkCertainUpgrades();

        // Every 5000 milliseconds or 5 seconds, save
        if (System.currentTimeMillis() - time >= 5000) {
            File f = Save.getFileOutsideJar(saveFileName, true);
            Save.save(f, GetSaveData.gameToString());
            time = System.currentTimeMillis();
        }
    }

    public static void save() {
        // Get save file, has special method to get it in the directory of the jar
        File f = Save.getFileOutsideJar(saveFileName, true);
        // Write to that file
        Save.save(f, GetSaveData.gameToString());
        // Time changing
        time = System.currentTimeMillis();
    }

    @Override
    public void render(GameContainer gameContainer, Graphics graphics) throws SlickException {
        // Render Connection then Render

        RenderManager.render(graphics);

        // Currency and Time

        RenderManager.renderStrings(ttf);
    }
}
