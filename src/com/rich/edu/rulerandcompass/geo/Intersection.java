package com.rich.edu.rulerandcompass.geo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public final class Intersection 
{
	public Point2D IntersectionPoint;
	
	public GeoEntity EntityA;
	
	public GeoEntity EntityB;
	
	public Intersection(Point2D pt, GeoEntity entA, GeoEntity entB)
	{
		IntersectionPoint = pt;
		EntityA = entA;
		EntityB = entB;
	}
		
	public void DrawIntersectionPoint(Canvas canvas)
	{
		canvas.drawCircle(IntersectionPoint.X, IntersectionPoint.Y, _hintRadius, _intersectionPaint);
	}

	{
		_hintRadius = 3;
		_intersectionPaint = new Paint();
		_intersectionPaint.setAntiAlias(true);
		_intersectionPaint.setColor(Color.BLUE);
		_intersectionPaint.setStrokeWidth(1);
		_intersectionPaint.setStyle(Paint.Style.STROKE);
	}
	@SuppressWarnings("unused")
	private Intersection()
	{
	}
	
	private static Paint _intersectionPaint;
	private static int _hintRadius;
}
