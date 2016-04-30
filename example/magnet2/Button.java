package com.example.magnet2;

public class Button extends Object
{
	Button ( int width, int height, float  yDim, float xDim, int amt )
	{
		super ( width, height, yDim, xDim, amt );
	}

	
	
	
	
	
	boolean isClicked()
	{
		if ( Engine.IS_CLICKED )
		{
			if ( Engine.xMouse >= x )
			{
				if ( Engine.xMouse <= x + width )
				{
					if ( Engine.yMouse >= y )
					{
						if ( Engine.yMouse <= y + height )
						{
							return true;
						}
						else return false;
					}
					else return false;
				}
				else return false;
			}
			else return false;
		}
		else return false;
	}

}
