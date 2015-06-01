package com.rich.edu.rulerandcompass.geo;

public class Ray  extends LineBase
{
	public Ray(float start_x, float start_y, float end_x, float end_y) 
	{
		super(start_x, start_y, end_x, end_y);
		// TODO Auto-generated constructor stub
	}


	public Ray(Point2D start, Point2D end) 
	{
		super(start, end);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void CalcIntersection(GeoEntity entity) 
	{
		// TODO Auto-generated method stub
		
	}





	@Override
	public void OnFinishMoving() {
		// TODO Auto-generated method stub
		
	}


}
