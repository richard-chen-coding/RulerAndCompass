package com.rich.edu.rulerandcompass.geo;

import java.util.ArrayList;
import java.util.Collection;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Util 
{
	public final static double M_2PI 	= Math.PI * 2;
	
	public static float Length(Point2D start, Point2D end)
	{
		return (float) Math.hypot((start.X - end.X), (start.Y - end.Y));
	}
	
	public static float horizontalAngle(Point2D p1, Point2D p2) 
	{
		return (float)((Math.atan2(p2.Y - p1.Y, p2.X - p1.X) + M_2PI) % (M_2PI));
	}
	
	public static Collection<Point2D> get_circle_circle_intersections(Point2D c1_center, float c1_radius, Point2D c2_center, float c2_radius)
	{
        ArrayList<Point2D> intersections = new ArrayList<Point2D>(2);
        
        float d = Length(c1_center, c2_center);
        
		if (d < Math.abs(c1_radius - c2_radius) || d > (c1_radius + c2_radius))
		{
			return intersections;
		}
		
		float angle = horizontalAngle(c1_center, c2_center);
		
		float d1 = d / 2 + (c1_radius * c1_radius - c2_radius * c2_radius) / (2 * d);
		
		Point2D tmp = Point2D.createPolar(c1_center, d1, angle);
		
		float h = (float)Math.sqrt(c1_radius * c1_radius - d1 * d1);
		
		intersections.add(Point2D.createPolar(tmp, h, (float)(angle + Math.PI / 2)));
		intersections.add(Point2D.createPolar(tmp, h, (float)(angle - Math.PI / 2)));	
		
        return intersections;
	}
	
	public static Collection<Point2D> get_circle_line_intersections(Point2D center, float radius, Point2D line_start, Point2D line_end)
	{
        ArrayList<Point2D> intersections = new ArrayList<Point2D>();

			
		//TODO
		
		
        return intersections;
	}
	
	
	public static Point2D get_line_line_intersections(Point2D start1, Point2D end1, Point2D start2, Point2D end2)
	{
		Point2D pt = null;

			
		//TODO
		
		
        return pt;
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
	
	public static int ScreenWidth;
	public static int ScreenHeight;
	private static boolean _isInited = false;
	private static Paint _intersectionPaint;
	private static int _intersectionRadius;
	
	private static Paint _hintPaint;
	private static int _hintRadius;
}
