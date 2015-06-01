package com.rich.edu.rulerandcompass.drawable;

import com.rich.edu.rulerandcompass.geo.Point2D;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class DrawUtil 
{
	public static int ScreenWidth;
	public static int ScreenHeight;
	private static boolean _isInited = false;
	private static Paint _intersectionPaint;
	private static int _intersectionRadius;
	
	private static Paint _hintPaint;
	private static int _hintRadius;
	
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
	
	public static void DrawIntersectionPoint(Canvas canvas, Point2D pt)
	{
		canvas.drawCircle(pt.X, pt.Y, _intersectionRadius, _intersectionPaint);
	}
	
	public static void DrawHintPoint(Canvas canvas, Point2D pt)
	{
		canvas.drawCircle(pt.X, pt.Y, _hintRadius, _hintPaint);
	}
}
