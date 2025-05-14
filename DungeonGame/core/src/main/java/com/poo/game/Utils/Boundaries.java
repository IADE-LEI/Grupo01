package com.poo.game.Utils;

public class Boundaries {
    public static boolean IsValidIndex(int Value, int Min, int Max)
    {
        return Value >= Min && Value <= Max;
    }
}
