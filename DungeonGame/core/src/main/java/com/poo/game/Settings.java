/* ------------------------------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ------------------------------------------------------------------------------------------------
 * Projeto : Dungeon Game (Projeto Grupo 1)
 * Disciplica : Programação e Algoritmos (LEI1A2S)
 * Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tiago Araújo | Tomás Pereira
 * ------------------------------------------------------------------------------------------------
 */
package com.poo.game;

import com.poo.game.Interfaces.ISettings;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

/** Global settings of game saved on applications preferences */
public class Settings implements ISettings {
  private final Preferences prefs;

  private boolean playMusic;
  private float soundVolume;
  private float moveTurbo;

  Settings() {
    this.prefs = Preferences.userNodeForPackage(Settings.class);
    this.Load();
  }

  public boolean getPlayMusic() {
    return this.playMusic;
  }

  public void setPlayMusic(boolean value) {
    this.playMusic = value;
  }

  public float getSoundVolume() {
    return this.soundVolume;
  }

  public void setSoundVolume(float value) {
    this.soundVolume = value;
  }

  public float getMoveTurbo() {
    return this.moveTurbo;
  }

  public void setMoveTurbo(float value) {
    this.moveTurbo = value;
  }

  /** Load settings values from application preferences */
  public void Load() {
    this.playMusic = this.prefs.getBoolean(Constants.PREF_MUSIC, true);
    this.soundVolume = this.prefs.getFloat(Constants.PREF_SOUND, 0.1f);
    this.moveTurbo = this.prefs.getFloat(Constants.PREF_TURBO, 4f);
  }

  /** Save settings values to application preferences */
  public void Save() throws BackingStoreException {
    this.prefs.putBoolean(Constants.PREF_TURBO, this.playMusic);
    this.prefs.putFloat(Constants.PREF_SOUND, this.soundVolume);
    this.prefs.putFloat(Constants.PREF_TURBO, this.moveTurbo);
    this.prefs.flush();
  }
}
