package com.rich.edu.rulerandcompass.geo;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class LineBase extends GeoEntity
{
	public LineBase(float start_x, float start_y, float end_x, float end_y) 
	{
		super(start_x, start_y, end_x, end_y);
		_slope = Math.abs((start_y - end_y) / (start_x - end_x));
	}
	
	public LineBase(Point2D start, Point2D end) 
	{
		super(start, end);
		_slope = Math.abs((start.Y - end.Y) / (start.X - end.X));
	}
	

	public float slope()
	{
		return _slope;
	}
	
	public boolean IsParallel(LineBase line)
	{
		return this.slope() == line.slope();
	}
	
	public void Reset(float new_start_x, float new_start_y, float new_end_x, float new_end_y)
	{
		super.Reset(new_start_x, new_start_y, new_end_x, new_end_y);
		_slope = Math.abs((new_start_y - new_end_y) / (new_start_x - new_end_x));
	}
	
	public void Draw(Canvas canvas, Paint paint)
	{
		super.Draw(canvas, paint);
		canvas.drawLine(_start.X, _start.Y, _end.X, _end.Y, paint);
	}
	
	@Override
	public void DrawMovingHintPoints(Canvas canvas, Paint paint) 
	{
		Util.DrawHintPoint(canvas, _start);
		Util.DrawHintPoint(canvas, _end);
	}
	
	protected float _slope;
}
