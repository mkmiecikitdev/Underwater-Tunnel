package com.example.magnet2;

import javax.microedition.khronos.opengles.GL10;

public class Player extends Object
{
	public float speed;
	private float initSpeed = 0.6f;
	
	final int FLY_UP = 1;
	final int FLY_DOWN = -1;
	int val = 0;
	int flyDir = FLY_UP;
	
	float L;
	float R;

	public Player()
    {
		super ( 5, 2, 12, 3 );
		R = 30/32 * width;
		L = 10/32 * width;
    }
	
	void init()
	{
		speed = initSpeed;
		y = 50f - height / 2;
		x = 25f - width / 2;
	}
	
	void move()
	{
				
			if ( Engine.GAME_MODE == Engine.GAME_ON ) 
			{
				if ( Engine.IS_BALL_CHANGE )
				{
					Engine.IS_BALL_CHANGE = false;
					flyDir *= -1.0f;
					speed = initSpeed;
					
				}
				else speed += 0.015f;
			}
			else if ( Engine.GAME_MODE == Engine.NO_START )
			{
				if ( y <= 40f || y + height >= 60f ) flyDir *= -1.0f;
			}
			
			
			
			
			if ( flyDir == -1.0f ) 
			{
				actuallyYFrame = 1;
			}
			else 
			{
				actuallyYFrame = 2;
			}
						
			y += flyDir * speed;
			
			
			
			
	}
	
	@Override
	public void draw( GL10 gl, int[] spriteSheet )
	{
		if ( ++val > 10 ) 
		{
			val = 0;
			actuallyXFrame++;
			if( actuallyXFrame > xFrames ) actuallyXFrame = 1;
		}
		super.draw( gl, spriteSheet );
		
	}
	
	
}
