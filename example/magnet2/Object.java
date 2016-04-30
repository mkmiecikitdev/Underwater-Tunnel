package com.example.magnet2;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Object 
{
	private FloatBuffer vertexBuffer;
    private FloatBuffer textureBuffer;
    private ByteBuffer indexBuffer;
    
    

    private float vertices[] = {
            0.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,
            1.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
    };
   
    private float texture[] = {
            0.0f, 0.0f,
            1f, 0.0f,
            1f, 1f,
            0.0f, 1f,
    };
    private byte indices[] = {
            0, 1, 2,
            0, 2, 3,
    };
	
	public float x;
	public float y;
	
	public float xDim;
	public float yDim;
	
	public float width;
	public float height;
	
	protected float xFrames;
	protected float yFrames;
	
	protected int actuallyXFrame = 1;
	protected int actuallyYFrame = 1;
	
	protected int objectsAmount;
	
	private void setBuffers()
	{
		ByteBuffer byteBuf = ByteBuffer.allocateDirect(vertices.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        vertexBuffer = byteBuf.asFloatBuffer();
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        byteBuf = ByteBuffer.allocateDirect(texture.length * 4);
        byteBuf.order(ByteOrder.nativeOrder());
        textureBuffer = byteBuf.asFloatBuffer();
        textureBuffer.put(texture);
        textureBuffer.position(0);

        indexBuffer = ByteBuffer.allocateDirect(indices.length);
        indexBuffer.put(indices);
        indexBuffer.position(0);
	}
	
	public Object( int width, int height, int objectsAmount )
    {
		
		xFrames = width;
		yFrames = height;
		
		this.yDim = 0;
		this.xDim = 0;
		
		this.width = 0;
		this.height = 0;
		
		this.objectsAmount = objectsAmount;
		
		texture [ 2 ] = 1/xFrames;
		texture [ 4 ] = 1/xFrames;
		texture [ 5 ] = 1/yFrames;
		texture [ 7 ] = 1/yFrames;
		
		setBuffers();
    }
	
	
	public Object( int width, int height, float yDim, int objectsAmount )
    {
		
		xFrames = width;
		yFrames = height;
		
		this.yDim = yDim;
		this.xDim = yDim / Engine.HxW;
		
		this.width = 100f / this.xDim;
		this.height = 100f / this.yDim;
		
		this.objectsAmount = objectsAmount;
		
		texture [ 2 ] = 1/xFrames;
		texture [ 4 ] = 1/xFrames;
		texture [ 5 ] = 1/yFrames;
		texture [ 7 ] = 1/yFrames;
		
		setBuffers();
    }
	
	public Object( int width, int height, float yDim, float xDim, int objectsAmount )
    {
		
		xFrames = width;
		yFrames = height;
		
		this.yDim = yDim;
		this.xDim = yDim / ( Engine.HxW * xDim );
		
		this.width = 100f / this.xDim;
		this.height = 100f / this.yDim;
		
		this.objectsAmount = objectsAmount;
		
		texture [ 2 ] = 1/xFrames;
		texture [ 4 ] = 1/xFrames;
		texture [ 5 ] = 1/yFrames;
		texture [ 7 ] = 1/yFrames;
		
		setBuffers();
    }
	
	void setWidth ( float width )
	{
		xDim = width;
		this.width = 100f / this.xDim;
	}
	
	void setHeight ( float height )
	{
		yDim = height;
		this.height = 100f / this.yDim;
	}
	
	public void draw( GL10 gl, int[] spriteSheet )
	{
		
		gl.glMatrixMode ( GL10.GL_MODELVIEW );
		gl.glLoadIdentity();
		gl.glPushMatrix();
		gl.glScalef ( 1f / xDim, 1f / yDim, 1f );
		gl.glTranslatef( ( x / 100 ) * xDim, ( y / 100 ) * yDim, 0f );
		
		gl.glMatrixMode ( GL10.GL_TEXTURE );
		gl.glLoadIdentity();
		gl.glTranslatef( ( actuallyXFrame - 1 ) / xFrames, ( actuallyYFrame - 1 ) / yFrames, 0.0f );
		
        gl.glBindTexture(GL10.GL_TEXTURE_2D, spriteSheet[ objectsAmount ] );
        gl.glFrontFace(GL10.GL_CCW);
        gl.glEnable(GL10.GL_CULL_FACE);
        gl.glCullFace(GL10.GL_BACK);
        gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, textureBuffer);
        gl.glDrawElements(GL10.GL_TRIANGLES, indices.length,
                GL10.GL_UNSIGNED_BYTE, indexBuffer);
        gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
        gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);
        gl.glDisable(GL10.GL_CULL_FACE);
        
        gl.glPopMatrix();
		gl.glLoadIdentity();
    }
	
	 
	 public void setXY ( float x, float y )
	 {
		 this.x = x;
		 this.y = y;
	 }
	 
	 void setFrameY ( int a )
	{
			actuallyYFrame = a;
	}
		
	void setFrameX ( int a )
	{
			actuallyXFrame = a;
	}
}
