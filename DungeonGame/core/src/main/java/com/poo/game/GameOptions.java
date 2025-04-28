/**
 * ----------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ----------------------------------------------------------------------------
 * Projeto      : Dungeon Game (Projeto Grupo 1)
 * Disciplica   : Programação e Algoritmos (LEI1A2S)
 * Professor    : Nelson Costa
 * Autores      : Affonso Neto | António Neto | Paulo Jadaugy | Tomás Pereira
 * ----------------------------------------------------------------------------
 */

package com.poo.game;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/**
 * Global options of game saved on applications preferences
 */
public class GameOptions {
    private final Preferences prefs;
    private boolean playMusic;
    private float moveTurbo;

    GameOptions() {
        this.prefs = Preferences.userNodeForPackage(GameOptions.class);
        LoadOptions();
    }

    public boolean getPlayMusic() {
        return this.playMusic;
    }

    public void setPlayMusic(boolean value) {
        this.playMusic = value;
    }

    public float getMoveTurbo() {
        return this.moveTurbo;
    }

    public void setMoveTurbo(float value) {
        this.moveTurbo = value;
    }

    /**
     * Load options values from application preferences
     */
    public void LoadOptions() {
        this.playMusic = this.prefs.getBoolean("PlayMusic", true);
        this.moveTurbo = this.prefs.getFloat("MoveTurbo", 4f);
    }

    /**
     * Save options values to application preferences
     */
    public void SaveOptions() throws BackingStoreException {
        this.prefs.putBoolean("PlayMusic", this.playMusic);
        this.prefs.putFloat("MoveTurbo", this.moveTurbo);
        this.prefs.flush();
    }
}
