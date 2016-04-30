package com.example.magnet2;

import javax.microedition.khronos.opengles.GL10;

public class TitleScreen 
{
	
	private Background title_screen = new Background( 1f, 0.5f, false );

	
	
	
	TitleScreen()
	{
		
	}
	
	void init( GL10 gl )
	{
		title_screen.loadTexture ( gl, Engine.TITLE_SCREEN_IMG, Engine.context );		
	}
	
	void update()
	{
		if ( Engine.IS_CLICKED )
		{
			Engine.GAME_MODE = Engine.MENU;
			Engine.IS_CLICKED = false;
		}
	}
	
	void show( GL10 gl )
	{
		   gl.glMatrixMode ( GL10.GL_MODELVIEW );
		   gl.glLoadIdentity();
		   gl.glPushMatrix();
		   gl.glScalef ( 1f, 1f, 1f );
		   gl.glTranslatef( 0f, 0f, 0f );
		   
		   gl.glMatrixMode ( GL10.GL_TEXTURE );
		   gl.glLoadIdentity();
		   gl.glTranslatef( 0.0f, 0.0f , 0.0f );
		   
		   title_screen.draw( gl );
		   
		   gl.glMatrixMode ( GL10.GL_MODELVIEW );
		   gl.glLoadIdentity();
		   gl.glPushMatrix();
		   gl.glScalef ( 0.5f, 0.1f, 1f );
		   gl.glTranslatef( 0.5f, 5f, 0f );;
		   
		   gl.glMatrixMode ( GL10.GL_TEXTURE );
		   gl.glLoadIdentity();
		   gl.glTranslatef( 0.0f, 0.5f , 0.0f );
		   
		   title_screen.draw( gl );
		   
		   
		   gl.glPopMatrix();
		   gl.glLoadIdentity();
		   
		   
	}
}
