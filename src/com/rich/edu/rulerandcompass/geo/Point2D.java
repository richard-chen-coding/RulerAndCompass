package com.rich.edu.rulerandcompass.geo;

public final class Point2D 
{
	public float X;
	public float Y;
	
	public Point2D(float _x, float _y)
	{
		X = _x;
		Y = _y;
	}
	
	public boolean equals(Object o)
	{
		Point2D pt = (Point2D)o;
		if(pt == null)
		{
			return false;
		}
		
		return this.X == pt.X && this.Y == pt.Y;
	}
}
