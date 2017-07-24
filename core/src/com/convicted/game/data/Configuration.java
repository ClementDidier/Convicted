package com.convicted.game.data;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.convicted.game.drawable.ui.screen.ConvictedScreen;

public class Configuration
{
    private final static int DEFAULT_INT_VALUE = 0;
    private final static float DEFAULT_FLOAT_VALUE = 0f;
    private final static long DEFAULT_LONG_VALUE = 0;
    private final static boolean DEFAULT_BOOLEAN_VALUE = false;

    public final static String PREFS_NAME = "ConvictedPrefs";
    public final static String PREFS_MOVE_JOYSTICK_ALIGN_X = "MoveJoystickAlignX";
    public final static String PREFS_MOVE_JOYSTICK_ALIGN_Y = "MoveJoystickAlignY";
    public final static String PREFS_SPLASH_PROGRESS_BAR_ALIGN_X = "SplashProgressBarAlignX";
    public final static String PREFS_SPLASH_PROGRESS_BAR_ALIGN_Y = "SplashProgressBarAlignY";
    public final static String PREFS_MAIN_BUTTON_START_ALIGN_X = "MainButtonStartAlignX";
    public final static String PREFS_MAIN_BUTTON_START_ALIGN_Y = "MainButtonStartAlignY";

    private static Configuration CONFIG;

    private Preferences prefs;

    public Configuration()
    {
        this.prefs = Gdx.app.getPreferences(PREFS_NAME);
        this.initialize();
    }

    public static Configuration getInstance()
    {
        if(CONFIG == null)
            CONFIG = new Configuration();
        return CONFIG;
    }

    public int getInteger(String content)
    {
        return this.prefs.getInteger(content, DEFAULT_INT_VALUE);
    }

    public boolean getBoolean(String content)
    {
        return this.prefs.getBoolean(content, DEFAULT_BOOLEAN_VALUE);
    }

    public float getFloat(String content)
    {
        return this.prefs.getFloat(content, DEFAULT_FLOAT_VALUE);
    }

    public long getLong(String content)
    {
        return this.prefs.getLong(content, DEFAULT_LONG_VALUE);
    }

    public void initialize()
    {
        //if(!this.prefs.contains(PREFS_MOVE_JOYSTICK_ALIGN_X))
            this.prefs.putInteger(PREFS_MOVE_JOYSTICK_ALIGN_X, (int)(ConvictedScreen.VIEWPORT.x / 7f));

        //if(!this.prefs.contains(PREFS_MOVE_JOYSTICK_ALIGN_Y))
            this.prefs.putInteger(PREFS_MOVE_JOYSTICK_ALIGN_Y, (int)(ConvictedScreen.VIEWPORT.y / 5f));

        //if(!this.prefs.contains(PREFS_SPLASH_PROGRESS_BAR_ALIGN_X))
            this.prefs.putInteger(PREFS_SPLASH_PROGRESS_BAR_ALIGN_X, (int)(ConvictedScreen.VIEWPORT.x / 2f - 655f / 2f));

        //if(!this.prefs.contains(PREFS_SPLASH_PROGRESS_BAR_ALIGN_Y))
            this.prefs.putInteger(PREFS_SPLASH_PROGRESS_BAR_ALIGN_Y, 260);

        //if(!this.prefs.contains(PREFS_MAIN_BUTTON_START_ALIGN_X))
            this.prefs.putInteger(PREFS_MAIN_BUTTON_START_ALIGN_X, (int)(ConvictedScreen.VIEWPORT.x / 2.));

        //if(!this.prefs.contains(PREFS_MAIN_BUTTON_START_ALIGN_Y))
            this.prefs.putInteger(PREFS_MAIN_BUTTON_START_ALIGN_Y, (int)(ConvictedScreen.VIEWPORT.y / 2.));

        this.prefs.flush();
    }
}
