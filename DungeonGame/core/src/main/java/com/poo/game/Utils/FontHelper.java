/* ------------------------------------------------------------------------------------------------
 * Licenciatura de Engenharia Informática - IADE - 2024/2025
 * ------------------------------------------------------------------------------------------------
 * Projeto : Dungeon Game (Projeto Grupo 1)
 * Disciplica : Programação e Algoritmos (LEI1A2S)
 * Professor : Nelson Costa
 * Autores : Affonso Neto | António Neto | Paulo Jadaugy | Tiago Araújo | Tomás Pereira
 * ------------------------------------------------------------------------------------------------
 */
package com.poo.game.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontHelper {
  public static BitmapFont FontFromFile(String filename, int fontSize) {
    FreeTypeFontGenerator generator =
        new FreeTypeFontGenerator(Gdx.files.internal("font/" + filename));
    FreeTypeFontGenerator.FreeTypeFontParameter parameter =
        new FreeTypeFontGenerator.FreeTypeFontParameter();
    parameter.size = fontSize;
    BitmapFont font = generator.generateFont(parameter);
    generator.dispose();
    return font;
  }

  public static float CenterScreen(BitmapFont font, String text, float width) {
    GlyphLayout layout = new GlyphLayout(font, text);
    return (width - layout.width + 20) / 2.0f;
  }
}
