package com.example.magnet2;

import javax.microedition.khronos.opengles.GL10;

import android.content.SharedPreferences;

public class Enemies 
{
	
	public static final byte TOTAL_ENEMIES = 10;
	public static byte LAST_ENEMY;
	
	public Enemy[] enemy = new Enemy [ TOTAL_ENEMIES ];
	
	public Enemies()
	{
		for ( byte a = 0 ; a < TOTAL_ENEMIES ; a++ ) enemy [ a ] = new Enemy ( 7.5f );
	}
	
	
	void init()
	{
		   LAST_ENEMY = 10;
		   enemy [ 0 ].init(  0 );
		   
		   for ( int a = 1 ; a < TOTAL_ENEMIES ; a++ )
		   {
			   float x = enemy [ a-1 ].x;
			   float y = enemy [ a-1 ].y;
			   enemy [ a ].init( x,y );
		   }
	}
	
	void move()
	{
		for ( int a = 0; a < TOTAL_ENEMIES ; a++ )
		{
			if ( enemy[a].x <= -enemy [ a ].width )
			{
				if ( Engine.GAME_MODE == Engine.GAME_ON ) Engine.POINTS++;
				if ( Engine.POINTS > Engine.HIGH_SCORE )
				{
					Engine.HIGH_SCORE = Engine.POINTS;
					SharedPreferences.Editor editor = Engine.saveScore.edit();
				    editor.putFloat("HIGH_SCORE", Engine.HIGH_SCORE );
				    editor.commit();
				}
				
				float x = enemy [ LAST_ENEMY - 1 ].x;
				float y = enemy [ LAST_ENEMY - 1 ].y;
				if ( LAST_ENEMY++ == 10 ) LAST_ENEMY = 1;

				enemy[a].init( x, y );
			}
		
		enemy[a].move();
		
		
		
		}
	}
	
	
	void draw ( GL10 gl, int[] spriteSheet )
	{
		for ( byte a = 0 ; a < TOTAL_ENEMIES ; a++ )
		{
			enemy[a].draw( gl, spriteSheet );
		}
	}
	
	
	
}
