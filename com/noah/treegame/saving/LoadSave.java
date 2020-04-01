package com.noah.treegame.saving;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadSave {

    public static String[] readSave(String name) {
        List<String> lines = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(Save.getFileOutsideJar(name, false)));
            String l;
            while ((l = br.readLine()) != null) {
                lines.add(l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines.toArray(new String[0]);
    }

}
