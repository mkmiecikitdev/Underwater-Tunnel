package com.example.magnet2;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Bum extends Object
{
    boolean on = false;

	public Bum()
    {
        super ( 5, 1, 15, 2 );
    }
	
	void init ( float x, float y )
    {
		this.x = x;
		this.y = y;
 	    actuallyXFrame = 1;
 	    on = true;
 	    
    }
	
	void show( GL10 gl, int[] spriteSheet )
	{
		if ( on )
		{
			draw ( gl, spriteSheet );
			actuallyXFrame++;
			if ( actuallyXFrame >= xFrames ) on = false;
		}
	}
	
	
}
