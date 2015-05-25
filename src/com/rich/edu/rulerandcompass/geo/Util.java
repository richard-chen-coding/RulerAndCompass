package com.rich.edu.rulerandcompass.geo;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Util 
{
	public static float Length(Point2D start, Point2D end)
	{
		return (float) Math.hypot((start.X - end.X), (start.Y - end.Y));
	}

	public static Point2D get_line_intersection(float p0_x, float p0_y, float p1_x, float p1_y, 
		    float p2_x, float p2_y, float p3_x, float p3_y)
		{
		    float s1_x, s1_y, s2_x, s2_y;
		    s1_x = p1_x - p0_x;     s1_y = p1_y - p0_y;
		    s2_x = p3_x - p2_x;     s2_y = p3_y - p2_y;

		    float s, t;
		    s = (-s1_y * (p0_x - p2_x) + s1_x * (p0_y - p2_y)) / (-s2_x * s1_y + s1_x * s2_y);
		    t = ( s2_x * (p0_y - p2_y) - s2_y * (p0_x - p2_x)) / (-s2_x * s1_y + s1_x * s2_y);

		    if (s >= 0 && s <= 1 && t >= 0 && t <= 1)
		    {
		        // Collision detected
		        float i_x = p0_x + (t * s1_x);
		        float i_y = p0_y + (t * s1_y);
		        return new Point2D(i_x, i_y);
		    }

		    return null; // No collision
		}
	
	public static void DrawIntersectionPoint(Canvas canvas, Point2D pt)
	{
		canvas.drawCircle(pt.X, pt.Y, _intersectionRadius, _intersectionPaint);
	}
	
	public static void DrawHintPoint(Canvas canvas, Point2D pt)
	{
		canvas.drawCircle(pt.X, pt.Y, _hintRadius, _hintPaint);
	}
	
	public static void Init()
	{
		if(_isInited)
		{
			return;
		}
		_isInited = true;
		_intersectionRadius = 3;
		_intersectionPaint = new Paint();
		_intersectionPaint.setAntiAlias(true);
		_intersectionPaint.setColor(Color.BLUE);
		_intersectionPaint.setStrokeWidth(1);
		_intersectionPaint.setStyle(Paint.Style.STROKE);
		
		
		_hintRadius = 3;
		_hintPaint = new Paint();
		_hintPaint.setAntiAlias(true);
		_hintPaint.setColor(Color.GREEN);
		_hintPaint.setStrokeWidth(1);
		_hintPaint.setStyle(Paint.Style.STROKE);
	}
	
	private static boolean _isInited = false;
	private static Paint _intersectionPaint;
	private static int _intersectionRadius;
	
	private static Paint _hintPaint;
	private static int _hintRadius;
}
