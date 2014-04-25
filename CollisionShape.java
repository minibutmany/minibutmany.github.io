package com.joec.webwarm;

import java.util.ArrayList;

public class CollisionShape 
{
	float positionX;
	float positionY;
	
	int width;
	int height;
	
	float distanceLeft;
	float distanceRight;
	float distanceTop;
	float distanceBottom;
	
	public float speed;
	public float airSpeed;
	
	private static final int COL_RESET = 256;
	public CollisionShape(float x, float y, int width, int height)
	{
		positionX = x;
		positionY = y;
		
		this.width = width;
		this.height = height;
		
		speed = 90;
		airSpeed = 130;
		
		resetCollision();
	}
	
	public void checkCollision(CollisionShape obj)
	{
		float objX = obj.positionX;
		float objY = obj.positionY;
		int objWidth = obj.width;
		int objHeight = obj.height;
		
		float left = positionX - (objX + objWidth);
		float right = objX - (positionX + width);
		float bottom = positionY - (objY + objHeight);
		float top = objY - (positionY + height);
		
		if(left <= distanceLeft && left >= -5 && withinArea(positionY, height, objY, objHeight))
			distanceLeft = left;
		if(right <= distanceRight && right >= -5 && withinArea(positionY, height, objY, objHeight))
			distanceRight = right;
		if(bottom <= distanceBottom && bottom >= -5  && withinArea(positionX, width, objX, objWidth))
			distanceBottom = bottom;
		if(top <= distanceTop && top >= -5 && withinArea(positionX, width, objX, objWidth))
			distanceTop = top;
	}
	
	//returns if the object is within a collidable area, regardless of orientation
	private boolean withinArea(float pos1, int size1, float pos2, int size2) 
	{
		//using +/- 1 for some wiggle room!
		if(pos1 + size1 < pos2 + 1 || pos2 + size2 < pos1 + 1)
			return false;
		else
			return true;
	}

	public void resetCollision()
	{
		//set to 128 as a value that should generally not be reached
		distanceLeft = COL_RESET;
		distanceRight = COL_RESET;
		distanceTop = COL_RESET;
		distanceBottom = COL_RESET;
	}
	
	public void moveRight(float delta)
	{
		if(speed * delta <= distanceRight)
		{
			positionX += speed * delta;
		}
		else
		{
			positionX += distanceRight;
		}
	}
	
	public void moveLeft(float delta)
	{
		if(speed * delta <= distanceLeft)
		{
			positionX -= speed * delta;
		}
		else
		{
			positionX -= distanceLeft;
		}
	}
	
	public void moveUp(float delta)
	{
		if(speed * delta <= distanceTop)
		{
			positionY += airSpeed * delta;
		}
		else
		{
			positionY += distanceTop;
		}
	}
	
	public void moveDown(float delta)
	{
		if(speed * delta <= distanceBottom)
		{
			positionY -= airSpeed * delta;
		}
		else
		{
			positionY -= distanceBottom;
		}
	}

}