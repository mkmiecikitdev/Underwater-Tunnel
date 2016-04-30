package com.example.magnet2;

import javax.microedition.khronos.opengles.GL10;

public class HighscoreScreen
{
	
	private Background bg = new Background( 1f, 1f, false );
	
	Object highscoreBg = new Object ( 2, 1, 7 );
	Object highscoreTxt = new Object ( 1, 2, 20f, 5f, 10 );
	Object gamesTxt = new Object ( 1, 2, 20f, 5f, 10 );
	
	Object scoreNumberJ = new Object ( 1, 10, 20f, 9 );
	Object scoreNumberD = new Object ( 1, 10, 20f, 9 );
	Object scoreNumberS = new Object ( 1, 10, 20f, 9 );
	Object highscoreNumberJ = new Object ( 1, 10, 20f, 9 );
	Object highscoreNumberD = new Object ( 1, 10, 20f, 9 );
	
	
	boolean isNoReset = true;
	
	private Button menu_button = new Button ( 2, 2, 7f, 1.5f, 5 );
	
	HighscoreScreen()
	{
		
	}
	
	void init( GL10 gl )
	{
		bg.loadTexture ( gl, Engine.HIGH_BG, Engine.context );
		
		highscoreBg.setFrameX ( 1 );
		highscoreBg.setWidth( 1.2f );
		highscoreBg.setHeight ( 1.8f );
		highscoreBg.setXY( 50f - ( highscoreBg.width / 2 ), 90f - highscoreBg.height );
		
		highscoreTxt.setFrameY ( 2 );
		gamesTxt.setFrameX( 1 );
		
		highscoreTxt.setXY ( 50f - ( highscoreTxt.width / 2 ), highscoreBg.y + highscoreBg.height - highscoreTxt.height - 10f );
		highscoreNumberD.setXY( 49f - highscoreNumberD.width , highscoreTxt.y - highscoreNumberD.height - 5f );
		highscoreNumberJ.setXY( 51f , highscoreNumberD.y );
		
		gamesTxt.setXY ( 50f - ( gamesTxt.width / 2 ), highscoreNumberD.y - gamesTxt.height - 5f );
		
		scoreNumberD.setXY( 50f - ( scoreNumberD.width / 2 ) , gamesTxt.y - scoreNumberD.height - 5f );
		scoreNumberS.setXY( scoreNumberD.x - scoreNumberS.width - 2f , scoreNumberD.y );
		scoreNumberJ.setXY( scoreNumberD.x + scoreNumberD.width + 2f , scoreNumberD.y );
		
		menu_button.setWidth ( 2f );
		
		menu_button.setFrameX( 2 );
		menu_button.setFrameY( 1 );
		menu_button.setXY( 50f - ( menu_button.width / 2 ), highscoreBg.y - menu_button.height - 5f );
		
		
	}
	
	void reset()
	{
		float games = Engine.GAMES_PLAYED;
		float hScore = Engine.HIGH_SCORE;
		
		
		float j = 0;
		float d = 0;
		float s = 0;
		
		float jH = 0;
		float dH = 0;
		
		s = ( games -  ( games % 100 ) ) / 100;
		
		games -= s * 100;
		
		d = ( games -  ( games % 10 ) ) / 10;
		
		games -= d * 10;
		
		j = games;
		
		dH = ( hScore -  ( hScore % 10 ) ) / 10;
		
		hScore -= dH * 10;
		
		jH = hScore;
		
		j++;
		d++;
		s++;
		jH++;
		dH++;
		
		scoreNumberJ.setFrameY( (int) j );
		scoreNumberD.setFrameY( (int) d );
		scoreNumberS.setFrameY( (int) s );
		highscoreNumberJ.setFrameY( (int) jH );
		highscoreNumberD.setFrameY( (int) dH );
	}
	
	void update()
	{
		if ( isNoReset )
		{
			reset();
		}
		if ( menu_button.isClicked() )
		{
			Engine.GAME_MODE = Engine.MENU;
			Engine.IS_CLICKED = false;
		}
	}
	
	void show( GL10 gl, int[] spriteSheet )
	{
		
		 gl.glMatrixMode ( GL10.GL_MODELVIEW );
		 gl.glLoadIdentity();
		 gl.glPushMatrix();
		 gl.glScalef ( 1f, 1f, 1f );
		 gl.glTranslatef( 0f, 0f, 0f );
		   
		 bg.draw( gl );
		   
		 gl.glPopMatrix();
		 gl.glLoadIdentity();
		
		 highscoreBg.draw ( gl, spriteSheet );
		 gamesTxt.draw( gl, spriteSheet );
		 highscoreTxt.draw( gl, spriteSheet );
		  
		 highscoreNumberJ.draw( gl, spriteSheet );
		 highscoreNumberD.draw( gl, spriteSheet );
		 scoreNumberS.draw( gl, spriteSheet );
		 scoreNumberJ.draw( gl, spriteSheet );
		 scoreNumberD.draw( gl, spriteSheet );
		 
		 menu_button.draw ( gl, spriteSheet );
		
		       
	}
	
	
}
