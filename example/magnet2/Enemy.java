package com.example.magnet2;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import com.example.magnet2.Engine;

public class Enemy extends Object
{
	byte tmp = 1;
	byte val = 1;
	byte dir;
	
	float speedX = 1f;
	float speedY = 0.8f;
	
	public Enemy( float yDim )
    {
		super ( 3, 1, yDim, 0 );
    }
	
	public Enemy( float yDim, float xDim )
    {
		super ( 3, 1, yDim, xDim, 0 );
    }
	
	
	public void init ( float x, float y )
	{
		this.x = x + width + 20f;
		this.y = 5f + ( Engine.random.nextFloat() * ( 95f - height - 5f ) );
		
		float r = Engine.random.nextFloat();
		if ( r < 0.5f ) dir = 1;
		else dir = -1;
		
	}
	
	public void init (  float y )
	{
		this.x = 130f;
		this.y = 5f + ( Engine.random.nextFloat() * ( 95f - height - 5f ) );
		
		float r = Engine.random.nextFloat();
		if ( r < 0.5f ) dir = 1;
		else dir = -1;
		
				
	}
	
	void move()
	{
		x -= speedX;
		
		if ( y + ( dir * speedY ) <= 5f )
		{
			dir *= -1;
			y = 5f;
		}
		
		if ( y + ( dir * speedY ) + height >= 95f )
		{
			dir *= -1;
			y = 95f - height;
		}
		
		y += ( dir * speedY );
		
	}
	
	@Override
	public void draw( GL10 gl, int[] spriteSheet )
	{
		super.draw( gl, spriteSheet );
		if ( ++val == 8 )
		{
			val = 1;
			actuallyXFrame += tmp;
			if ( actuallyXFrame == 3 || actuallyXFrame == 1 ) tmp *= -1;
		}
		
		
	}
	
	
}


