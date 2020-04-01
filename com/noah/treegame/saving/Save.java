package com.noah.treegame.saving;

import com.noah.treegame.Main;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Collections;

public class Save {

    public static void save(File file, String data) {
        try {
            Files.write(file.toPath(), Collections.singleton(data), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static File getFileOutsideJar(String name, boolean create) {
        File f = null;
        try {
            File jar = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            File parent = jar.getParentFile();
            File res = new File(parent.getPath() + "/" + name);
            if (create) {
                res.createNewFile();
            }
            f = res;
        } catch (URISyntaxException | IOException e) {
            e.printStackTrace();
        }
        return f;
    }

}
