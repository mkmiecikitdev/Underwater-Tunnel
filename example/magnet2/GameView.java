package com.example.magnet2;


import android.content.Context;
import android.opengl.GLSurfaceView;

public class GameView extends GLSurfaceView
{
	
	private GameRender renderer;
	
	public GameView ( Context context )
	{
		super ( context );
		
		
		
		renderer = new GameRender();
		this.setRenderer( renderer );
	}
}
