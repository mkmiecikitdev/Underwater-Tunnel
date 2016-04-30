package com.example.magnet2;


import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.SharedPreferences;
import android.opengl.GLSurfaceView.Renderer;

public class GameRender implements Renderer
{
	
	//title
	//private Background titlebg = new Background( 1.0f, 1.0f, false );
	//private Background titlename = new Background( 1f, 1f, false );
	
	private TitleScreen titleScreen = new TitleScreen();
	private MenuScreen menuScreen = new MenuScreen();
	private GameOverScreen gameOverScreen = new GameOverScreen();
	private HighscoreScreen highscoreScreen = new HighscoreScreen();
	
	
	private Background background1 = new Background( 1.0f, 1.0f, true );
	private Background background2 = new Background( 1.0f, 1.0f, true );
	private Background background3 = new Background( 1.0f, 1.0f, true );
	
	private Player player = new Player();
	private Enemies enemies = new Enemies();
	
	private Background number = new Background( 1f, 0.1f, false );
	
	
	private Txt txt = new Txt();
	private Bum bum = new Bum();
	
	private Bubbles bubble = new Bubbles();
	
	private Textures textureLoader;
	private int[] spriteSheets = new int [ 12 ];
	
	
	private float bgScroll = 0f;
	private float magnetScroll = 0.0f;
	private float  startMagnetScroll = 1.0f;
	
	
	boolean changeMagnet = true;
	
	private long loopStart = 0;
    private long loopEnd = 0;
    private long loopRunTime = 0;
    
    
 
   
   
   private void initGame ( GL10 gl )
   {
	   if ( Engine.GAME_MODE == Engine.GAME_INIT )
	   {
		   Engine.GAME_MODE = Engine.NO_START;
		   
	       Engine.IS_BALL_CHANGE = true;
	       
	       Engine.POINTS = 0;
	       
	       enemies.init();
	       player.init();
	       bubble.init();
	       
	       Engine.GAMES_PLAYED++;
	       if ( Engine.GAMES_PLAYED > 999 ) Engine.GAMES_PLAYED = 1;
	       
	      
	       
	       SharedPreferences.Editor editor = Engine.saveScore.edit();
		    editor.putFloat("GAMES_PLAYED", Engine.GAMES_PLAYED );
		    editor.commit();
	   }
   }
    
	
	private void drawNumbers ( GL10 gl )
	{
		
		if ( Engine.GAME_MODE == Engine.GAME_ON )
		{
			float points = Engine.POINTS;
			float j = 0;
			float d = 0;
			
			d = ( points -  ( points % 10 ) ) / 10;
			
			points -= d * 10;
			
			j = points;

			
			gl.glMatrixMode ( GL10.GL_MODELVIEW );
			gl.glLoadIdentity();
			gl.glPushMatrix();
			gl.glScalef ( Engine.HxW / 15f, 1 / 15f, 1f );
			gl.glTranslatef( 0.25f, 13f, 0f );
			
			gl.glMatrixMode ( GL10.GL_TEXTURE );
			gl.glLoadIdentity();
			gl.glTranslatef( 0.0f, d / 10.0f , 0.0f );
			
			number.draw( gl );
			
			gl.glMatrixMode ( GL10.GL_MODELVIEW );
			gl.glLoadIdentity();
			gl.glPushMatrix();
			gl.glScalef ( Engine.HxW / 15f, 1 / 15f, 1f );
			gl.glTranslatef( 1.25f, 13f, 0f );
			
			gl.glMatrixMode ( GL10.GL_TEXTURE );
			gl.glLoadIdentity();
			gl.glTranslatef( 0.0f, j / 10.0f , 0.0f );
			
			number.draw( gl );
			
			
			gl.glPopMatrix();
			gl.glLoadIdentity();
		}
		
	}
	
	void resetHighScore ()
	{
		Engine.HIGH_SCORE = 0;
		SharedPreferences.Editor editor = Engine.saveScore.edit();
	    editor.putFloat("HIGH_SCORE", Engine.HIGH_SCORE );
	    editor.commit();
	}
	
	void endInit()
	{
		bum.init( player.x, player.y );
		gameOverScreen.setScores();
		Engine.GAME_MODE = Engine.GAME_END;
	}
	
	void drawEnd ( GL10 gl )
	{
		
			bum.show( gl, spriteSheets );
	}
	
	private void scrollBackground2 ( GL10 gl )
	{	
		
			
				magnetScroll += 0.01f;
				
				
				gl.glMatrixMode ( GL10.GL_MODELVIEW );
				gl.glLoadIdentity();
				gl.glPushMatrix();
				gl.glScalef ( 1f, 1 / 20f, 1f );
				gl.glTranslatef( 0f, 0f, 0f );
				
				
				gl.glMatrixMode ( GL10.GL_TEXTURE );
				gl.glLoadIdentity();
				gl.glTranslatef(  magnetScroll, 0.0f , 0.0f );
				
				background2.draw( gl );
				
				
				gl.glMatrixMode ( GL10.GL_MODELVIEW );
				gl.glLoadIdentity();
				gl.glPushMatrix();
				gl.glScalef ( 1f, 1 / 20f, 1f );
				gl.glTranslatef( 0f, 19f, 0f );
				
				gl.glMatrixMode ( GL10.GL_TEXTURE );
				gl.glLoadIdentity();
				gl.glTranslatef( magnetScroll, 0.0f, 0.0f );
				
				
				
				background3.draw( gl );
				
			gl.glPopMatrix();
			gl.glLoadIdentity();
	}
	
	private void scrollBackground1 ( GL10 gl )
	{
		if ( bgScroll == Float.MAX_VALUE ) {bgScroll = 0f;}
		
		gl.glMatrixMode ( GL10.GL_MODELVIEW );
		gl.glLoadIdentity();
		gl.glPushMatrix();
		gl.glScalef ( 1f, 1f, 1f );
		gl.glTranslatef( 0f, 0f, 0f );
		
		gl.glMatrixMode ( GL10.GL_TEXTURE );
		gl.glLoadIdentity();
		gl.glTranslatef(  bgScroll, 0.0f , 0.0f );
		
		
		background1.draw( gl );
		gl.glPopMatrix();
		
		bgScroll += .001f;
		gl.glLoadIdentity();
		
		
	}
	

	
	void drawTxt ( GL10 gl )
	{
		if ( Engine.GAME_MODE == Engine.NO_START )
		{
			gl.glMatrixMode ( GL10.GL_MODELVIEW );
			gl.glLoadIdentity();
			gl.glPushMatrix();
			gl.glScalef ( 0.75f, 0.75f, 1f );
			gl.glTranslatef( 0.166f, 0.166f, 0f );
				
			txt.draw( gl, spriteSheets );
				
			gl.glPopMatrix();
			gl.glLoadIdentity();
		}
	}
		
	
	void detectCollisions ()
	{
		for ( byte a = 0 ; a < Enemies.TOTAL_ENEMIES ; a++ )
		{
			if ( enemies.enemy [ a ].x < player.x + player.width - player.R )
			{
				if ( enemies.enemy [ a ].x + enemies.enemy [ a ].width > player.x + player.L )
				{
					if ( enemies.enemy [ a ].y + enemies.enemy [ a ].height > player.y )
					{
						if ( enemies.enemy [ a ].y < player.y + player.height  )
						{
							Engine.GAME_MODE = Engine.GAME_END_INIT;
						}
					}
				}
			}
		}
		
		if ( player.y + player.height >= 95f )
		{
			Engine.GAME_MODE = Engine.GAME_END_INIT;
		}
		else if ( player.y <= 5f )
		{
			Engine.GAME_MODE = Engine.GAME_END_INIT;
		}
		
	}

	
	@Override
	public void onDrawFrame( GL10 gl )
	{
		loopStart = System.currentTimeMillis();
	    try {
	        if (loopRunTime < Engine.GAME_THREAD_FPS_SLEEP) {
	            Thread.sleep(Engine.GAME_THREAD_FPS_SLEEP - loopRunTime);
	        }
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }
		
		gl.glClear ( GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT );
		
		
	// logic ------------
		if ( Engine.GAME_MODE == Engine.TITLE )
		{
			titleScreen.update();
			titleScreen.show ( gl );
		}
		else
		{
			if ( Engine.GAME_MODE == Engine.MENU )
			{
				menuScreen.update();
				menuScreen.show ( gl, spriteSheets );
			}
			else
			{
				if ( Engine.GAME_MODE == Engine.HIGHSCORE )
				{
					highscoreScreen.update();
					highscoreScreen.show ( gl, spriteSheets );
				}
				else
				{
					initGame( gl );
					
					scrollBackground1 ( gl );
					scrollBackground2 ( gl );
					bubble.draw( gl, spriteSheets, true );
					
					if ( Engine.GAME_MODE < Engine.GAME_END_INIT ) player.move();
					drawTxt ( gl );
					if ( Engine.GAME_MODE >= Engine.GAME_ON ) enemies.move();
					
					if ( Engine.GAME_MODE == Engine.GAME_END_INIT ) endInit();
					
					
					
				// drawing--------------
					if ( Engine.GAME_MODE < Engine.GAME_END_INIT ) player.draw( gl, spriteSheets );
					if ( Engine.GAME_MODE >= Engine.GAME_ON ) enemies.draw ( gl, spriteSheets );
					drawNumbers ( gl );
					
					
					if ( Engine.GAME_MODE == Engine.GAME_END ) 
					{
						drawEnd ( gl );
						gameOverScreen.update();
						gameOverScreen.show( gl, spriteSheets );
					}
					
					
				// collisions-----------
					if ( Engine.GAME_MODE == Engine.GAME_ON ) detectCollisions();
				}
				
			}
			
			
		}
		
		
		
		gl.glEnable ( GL10.GL_BLEND );
		gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE_MINUS_SRC_ALPHA);
		loopEnd = System.currentTimeMillis();
	    loopRunTime = ((loopEnd - loopStart));
		
	}
	
	@Override
	public void onSurfaceChanged ( GL10 gl, int width, int height )
	{
		gl.glViewport( 0, 0, width, height );
		gl.glMatrixMode ( GL10.GL_PROJECTION );
		gl.glLoadIdentity();
		gl.glOrthof ( 0f, 1f, 0f, 1f, -1f, 1f );
	
	}
	
	@Override
	public void onSurfaceCreated ( GL10 gl, EGLConfig config )
	{
		gl.glEnable( GL10.GL_TEXTURE_2D );
		gl.glClearDepthf( 1.0f );
		gl.glEnable ( GL10.GL_DEPTH_TEST );
		gl.glDepthFunc ( GL10.GL_LEQUAL );
		
		gl.glEnable ( GL10.GL_BLEND );
		gl.glBlendFunc(GL10.GL_ONE, GL10.GL_ONE );
		
		background1.loadTexture ( gl, Engine.BACKGROUND_ONE, Engine.context );
		background2.loadTexture ( gl, Engine.PIASEK_IMG, Engine.context );
		background3.loadTexture ( gl, Engine.MAGNET_IMG, Engine.context );
		
		titleScreen.init( gl );
		menuScreen.init( gl );
		gameOverScreen.init();
		highscoreScreen.init( gl );
		
		
		number.loadTexture ( gl, Engine.NUMBERS_IMG, Engine.context );
		
		textureLoader = new Textures ( gl );
		spriteSheets = textureLoader.loadTexture( gl, Engine.ENEMY_IMG, Engine.context, 1 );
		spriteSheets = textureLoader.loadTexture( gl, Engine.TXT_IMG, Engine.context, 2 );
		spriteSheets = textureLoader.loadTexture( gl, Engine.BUM_IMG, Engine.context, 3 );
		spriteSheets = textureLoader.loadTexture( gl, Engine.BALL_IMG, Engine.context, 4 );
		spriteSheets = textureLoader.loadTexture( gl, Engine.BUBBLES_IMG, Engine.context, 5 );
		spriteSheets = textureLoader.loadTexture( gl, Engine.STARTEXIT_IMG, Engine.context, 6 );
		spriteSheets = textureLoader.loadTexture( gl, Engine.HIGHSCORE_BUTTON_IMG, Engine.context, 7 );
		spriteSheets = textureLoader.loadTexture( gl, Engine.END_SCREEN_IMG, Engine.context, 8 );
		spriteSheets = textureLoader.loadTexture( gl, Engine.SCORE_TXT_IMG, Engine.context, 9 );
		spriteSheets = textureLoader.loadTexture( gl, Engine.SCORE_NUMBERS_IMG, Engine.context, 10 );
		spriteSheets = textureLoader.loadTexture( gl, Engine.HIGH_TXT_IMG, Engine.context, 11 );
		spriteSheets = textureLoader.loadTexture( gl, Engine.KONIK_IMG, Engine.context, 12 );
		
		
	}
}
