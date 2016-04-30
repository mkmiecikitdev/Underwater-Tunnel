package com.example.magnet2;




import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.WindowManager;

public class MainActivity extends Activity
{
	
	private GameView gameView;

    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
    	Engine.display = ( ( WindowManager ) getSystemService ( Context.WINDOW_SERVICE ) ).getDefaultDisplay();
        super.onCreate(savedInstanceState);
        Engine.context = getApplicationContext();
        Engine.random = new Random();
        
        Engine.SCREEN_HEIGHT = Engine.display.getHeight();
        Engine.SCREEN_WIDTH = Engine.display.getWidth();
        Engine.HxW = Engine.SCREEN_HEIGHT / Engine.SCREEN_WIDTH;
        Engine.KX = 100f / Engine.SCREEN_WIDTH;
        Engine.KY = 100f / Engine.SCREEN_HEIGHT;
        Engine.IS_CLICKED = false;
        
        Engine.GAME_MODE = Engine.TITLE;
        
        Engine.saveScore = getSharedPreferences( "plik", 0 );
        Engine.HIGH_SCORE = Engine.saveScore.getFloat ( "HIGH_SCORE", 0f );
        Engine.GAMES_PLAYED = Engine.saveScore.getFloat ( "GAMES_PLAYED", 0f );
        
        
        gameView = new GameView ( this );
        setContentView( gameView );
    }
    
    @Override
    protected void onResume() 
    {
    	super.onResume();
    	gameView.onResume();
    	
    }
    protected void onPause() 
    {
    	super.onPause();
    	gameView.onPause();
    	
    }
    
    @Override
    public boolean onTouchEvent ( MotionEvent event )
    {
    	
    	float x = event.getX();
    	float y = event.getY();
    	
    	
    		if ( event.getAction() == MotionEvent.ACTION_DOWN )
        	{
    			if ( !Engine.IS_CLICKED )
    	    	{
    				if ( Engine.GAME_MODE == Engine.NO_START ) Engine.GAME_MODE = Engine.GAME_ON;
    				else if ( Engine.GAME_MODE == Engine.GAME_ON ) Engine.IS_BALL_CHANGE = true;
    				Engine.xMouse = x * Engine.KX;
    				Engine.yMouse = ( Engine.SCREEN_HEIGHT - y ) * Engine.KY;
        			Engine.IS_CLICKED = true;
    	    	}
        	}
    		else if ( event.getAction() == MotionEvent.ACTION_UP ) Engine.IS_CLICKED = false;
    		
    	
    	return false;
    	
    	
    }
}