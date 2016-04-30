package com.example.magnet2;

import javax.microedition.khronos.opengles.GL10;

public class GameOverScreen 
{
	
	Object gameOverBg = new Object ( 2, 1, 7 );
	Object scoreTxt = new Object ( 1, 2, 20f, 5f, 8 );
	Object highscoreTxt = new Object ( 1, 2, 20f, 5f, 8 );
	
	Object scoreNumberJ = new Object ( 1, 10, 20f, 9 );
	Object scoreNumberD = new Object ( 1, 10, 20f, 9 );
	Object highscoreNumberJ = new Object ( 1, 10, 20f, 9 );
	Object highscoreNumberD = new Object ( 1, 10, 20f, 9 );
	
	private Button again_button = new Button ( 2, 2, 7f, 1.5f, 5 );
	private Button menu_button = new Button ( 2, 2, 7f, 1.5f, 5 );
	
	
	
	GameOverScreen()
	{
		
	}
	
	
	void setScores()
	{
		float points = Engine.POINTS;
		float hScore = Engine.HIGH_SCORE;
		
		
		float j = 0;
		float d = 0;
		
		float jH = 0;
		float dH = 0;
		
		d = ( points -  ( points % 10 ) ) / 10;
		
		points -= d * 10;
		
		j = points;
		
		dH = ( hScore -  ( hScore % 10 ) ) / 10;
		
		hScore -= dH * 10;
		
		jH = hScore;
		
		j++;
		d++;
		jH++;
		dH++;
		
		scoreNumberJ.setFrameY( (int) j );
		scoreNumberD.setFrameY( (int) d );
		highscoreNumberJ.setFrameY( (int) jH );
		highscoreNumberD.setFrameY( (int) dH );
	}
	
	
	void init()
	{
		gameOverBg.setFrameX ( 2 );
		gameOverBg.setWidth( 1.2f );
		gameOverBg.setHeight ( 2f );
		gameOverBg.setXY( 50f - ( gameOverBg.width / 2 ), 40f );
		
		scoreTxt.setFrameY ( 2 );
		highscoreTxt.setFrameY ( 1 );
		
		scoreNumberJ.setFrameY( 1 );
		scoreNumberD.setFrameY( 1 );
		highscoreNumberJ.setFrameY( 1 );
		highscoreNumberD.setFrameY( 1 );
		
		highscoreTxt.setXY( gameOverBg.x + 5f, gameOverBg.y + 5f );
		scoreTxt.setXY( gameOverBg.x + 5f, gameOverBg.y + 15f );
		
		highscoreNumberJ.setXY( gameOverBg.x + gameOverBg.width - highscoreNumberJ.width - 5f, highscoreTxt.y );
		highscoreNumberD.setXY( highscoreNumberJ.x - highscoreNumberD.width - 2f, highscoreTxt.y );
		
		scoreNumberJ.setXY( highscoreNumberJ.x, scoreTxt.y );
		scoreNumberD.setXY( highscoreNumberD.x, scoreTxt.y );
		
		
		menu_button.setWidth ( 2.5f );
		again_button.setWidth ( 2.5f );
		
		
		menu_button.setFrameX( 2 );
		menu_button.setFrameY( 1 );
		menu_button.setXY( 75f - ( menu_button.width / 2 ), gameOverBg.y - menu_button.height - 5f );
		
		again_button.setFrameX( 1 );
		again_button.setFrameX( 1 );
		again_button.setXY( 25f - ( again_button.width / 2 ), menu_button.y );
		
	}
	
	void update()
	{
		if ( again_button.isClicked() )
		{
			Engine.GAME_MODE = Engine.GAME_INIT;
			Engine.IS_CLICKED = false;
		}
		
		if ( menu_button.isClicked() )
		{
			Engine.GAME_MODE = Engine.MENU;
			Engine.IS_CLICKED = false;
		}
	}
	
	void show( GL10 gl, int[] spriteSheet )
	{
		  gameOverBg.draw ( gl, spriteSheet );
		  scoreTxt.draw( gl, spriteSheet );
		  highscoreTxt.draw( gl, spriteSheet );
		  
		  highscoreNumberJ.draw( gl, spriteSheet );
		  highscoreNumberD.draw( gl, spriteSheet );
		  scoreNumberJ.draw( gl, spriteSheet );
		  scoreNumberD.draw( gl, spriteSheet );
		  
		  menu_button.draw ( gl, spriteSheet );
		  again_button.draw ( gl, spriteSheet );
		       
	}

}
