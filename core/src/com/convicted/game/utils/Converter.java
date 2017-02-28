package com.convicted.game.utils;

import com.badlogic.gdx.Gdx;

public class Converter
{
    /**
     * Converti la valeur de l'axe vertical selon les modalités d'affichage
     * @param y La valeur verticale Touch sur le screen
     * @return La nouvelle valeur verticale obtenue après convertion
     */
    public static int convertTouchYAxis(int y)
    {
        return Gdx.graphics.getHeight() - y;
    }

    /**
     * Converti un angle radian en degrees
     * @param radian L'angle radian à convertir
     * @return L'angle en degrées
     */
    public static double convertRadianToDegree(double radian) { return radian * 180 / Math.PI; }
}
