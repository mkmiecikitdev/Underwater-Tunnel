package com.example.magnet2;


import java.util.Random;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.Display;



public class Engine 
{
	
	//ZASOBY
	public static final int BACKGROUND_ONE = R.drawable.bg1;
	public static final int MAGNET_IMG = R.drawable.magnets;
	public static final int BALL_IMG = R.drawable.betahorsea2;
	public static final int SCORE_IMG = R.drawable.score;
	public static final int NUMBERS_IMG = R.drawable.numbers;
	public static final int ENEMY_IMG = R.drawable.enemy;
	public static final int TXT_IMG = R.drawable.txt;
	public static final int BUM_IMG = R.drawable.bum;
	public static final int BUBBLES_IMG = R.drawable.bubbles;
	public static final int PIASEK_IMG = R.drawable.piasek;
	public static final int MENU_IMG = R.drawable.menu;
	public static final int TITLE_SCREEN_IMG = R.drawable.title_screen;
	public static final int STARTEXIT_IMG = R.drawable.butons;
	public static final int HIGHSCORE_BUTTON_IMG = R.drawable.highscore;
	public static final int END_SCREEN_IMG = R.drawable.end_screen;
	public static final int SCORE_TXT_IMG = R.drawable.score_txt;
	public static final int SCORE_NUMBERS_IMG = R.drawable.score_numbers;
	public static final int HIGH_TXT_IMG = R.drawable.btt;
	public static final int HIGH_BG = R.drawable.hbg;
	public static final int KONIK_IMG = R.drawable.konik;
	
	
	//TYPY MAGNESÓW

	
	
	
	
	public static boolean IS_BALL_CHANGE;
	


	//W JAKIM STANIE JEST GRA
	public static int GAME_MODE;
	public static final int  TITLE = 0;
	public static final int  MENU = 1;
	public static final int  NO_START = 4;
	public static final int GAME_ON = 5;
	public static final int GAME_END = 7;
	public static final int GAME_INIT = 3;
	public static final int GAME_END_INIT = 6;
	public static final int HIGHSCORE = 2;

	
	
	//GENERATOR LICZB LOSOWYCH
	public static Random random;
	
	//ekran
	public static Display display;
	public static float SCREEN_HEIGHT;
	public static float SCREEN_WIDTH;
	public static float HxW;
	
	
	
	public static boolean IS_CLICKED;
	public static float KX;
	public static float KY;
	public static float xMouse;
	public static float yMouse;
	
	public static float POINTS;
	public static float HIGH_SCORE;
	public static float GAMES_PLAYED;
	
	public static SharedPreferences saveScore;
	public static SharedPreferences saveScore2;
	
	public static final int GAME_THREAD_FPS_SLEEP = (1000 / 60);
	public static Context context;
	


}

