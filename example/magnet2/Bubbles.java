package com.example.magnet2;

import javax.microedition.khronos.opengles.GL10;

public class Bubbles extends Object
{
	public Bubbles()
	{
		super ( 4, 1, 9, 4 );
	}
	
	byte val = 0;
	
	void init()
	{
		val = 0;
		y = 0.0f;
		x = Engine.random.nextFloat() * 200;
		actuallyXFrame = 1;
	}
	
	
	
	public void draw( GL10 gl, int[] spriteSheet, boolean move )
	{
		
		y += 0.65f;
		if ( move ) x -= 0.3f;
		
		
		
		
		
			if ( y >= 70 )
			{
				if ( ++val > 10 ) 
				{
					val = 0;
					actuallyXFrame++;
					if( actuallyXFrame > xFrames ) 
					{
						init();
					}
				}
				
			}
			
		
		
		
		super.draw( gl, spriteSheet );
		
	}
	
	
	
}
