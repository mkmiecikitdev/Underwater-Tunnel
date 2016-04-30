package com.example.magnet2;

import javax.microedition.khronos.opengles.GL10;

public class MenuScreen
{
	
	private Background menu = new Background( 1f, 1f, false );
	private Button start_button = new Button ( 2, 2, 8f, 1.5f, 5 );
	private Button exit_button = new Button ( 2, 2, 8f, 1.5f, 5 );
	private Button highscore_button = new Button ( 1, 1, 8f, 1f, 6 );
	
	private Object horse = new Object ( 1, 1, 5f, 11 );
	
	int val = 0;
	int dir = 1;
	
	private Bubbles bubble = new Bubbles();
	
	
	MenuScreen()
	{
		
	}
	
	
	void init( GL10 gl )
	{
		menu.loadTexture ( gl, Engine.MENU_IMG, Engine.context );
		
		start_button.setWidth( 2.5f );
		exit_button.setWidth( 2.5f );
		highscore_button.setWidth( 1.3f );
		
		
		start_button.setXY ( 25f - ( start_button.width / 2 ), 25f );
		exit_button.setXY( 75f - ( exit_button.width / 2 ), 25f );
		
		start_button.setFrameX( 1 );
		exit_button.setFrameX ( 2 );
		
		start_button.setFrameY( 2 );
		exit_button.setFrameY ( 2 );
		
		highscore_button.setXY( 50f - ( highscore_button.width / 2 ), start_button.y - 5f - highscore_button.height );
		
		horse.setXY( 5f, 50f);
		horse.setWidth( 5f );
		
		bubble.init();
			
	}
	
	void update()
	{
		if ( start_button.isClicked() )
		{
			Engine.GAME_MODE = Engine.GAME_INIT;
			Engine.IS_CLICKED = false;
		}
		
		if ( highscore_button.isClicked() )
		{
			Engine.GAME_MODE = Engine.HIGHSCORE;
			Engine.IS_CLICKED = false;
		}
		
		if ( exit_button.isClicked() )
		{
			System.exit(1);
		}
		
		
		
		
		val += dir;
		
		if ( val == 10 || val == -10 ) dir *= -1;
		
		horse.y += val * 0.05f;
		
		
	}
	
	void show( GL10 gl, int[] spriteSheet )
	{
		 
		   gl.glMatrixMode ( GL10.GL_MODELVIEW );
		   gl.glLoadIdentity();
		   gl.glPushMatrix();
		   gl.glScalef ( 1f, 1f, 1f );
		   gl.glTranslatef( 0f, 0f, 0f );
		   
		   menu.draw( gl );
		   
		   gl.glPopMatrix();
		   gl.glLoadIdentity();
		   
		   start_button.draw ( gl, spriteSheet );
		   exit_button.draw ( gl, spriteSheet );
		   highscore_button.draw ( gl, spriteSheet );
		   horse.draw( gl, spriteSheet );
		   bubble.draw( gl, spriteSheet, false );
	}
	
	
}
