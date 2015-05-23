package com.rich.edu.rulerandcompass.geo;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public abstract class GeoEntity 
{	
	public GeoEntity(Point2D start, Point2D end)
	{
		if(start.equals(end))
		{
			 throw new IllegalArgumentException("Can not construct an GeoEntity with two identical points");
		}
		_start = start;
		_end = end;
		_cid++;
		_id = _cid;
		IntersectionPoints = new ArrayList<Intersection>();
	}
	
	public GeoEntity(float start_x, float start_y, float end_x, float end_y)
	{
		this(new Point2D(start_x, start_y), new Point2D(end_x, end_y));
	}
	
	{
		_hintRadius = 3;
		_hintPaint = new Paint();
		_hintPaint.setAntiAlias(true);
		_hintPaint.setColor(Color.GREEN);
		_hintPaint.setStrokeWidth(1);
		_hintPaint.setStyle(Paint.Style.STROKE);
	}
	
	public void Draw(Canvas canvas, Paint paint)
	{
		for(Intersection ins : IntersectionPoints)
		{
			ins.DrawIntersectionPoint(canvas);
		}

		DrawHintPoints(canvas, paint);
	}
	

	
	public void CalcIntersection(GeoEntity entity)
	{
		for(Intersection ins : IntersectionPoints)
		{
			if(ins.EntityA.Id() == this.Id() && ins.EntityB.Id() == entity.Id() || ins.EntityA.Id() == entity.Id() && ins.EntityB.Id() == this.Id())
			{
				
				
				IntersectionPoints.remove(ins);
			}
		}
		
		

		try
		{
			for(Intersection ins : entity.IntersectionPoints)
			{
				if(ins.EntityA.Id() == this.Id() && ins.EntityB.Id() == entity.Id() || ins.EntityA.Id() == entity.Id() && ins.EntityB.Id() == this.Id())
				{
					
					entity.IntersectionPoints.remove(ins);
				}
			}
		}
		catch(java.util.ConcurrentModificationException ex)
		{	
		
		}
		

	}

	
	public void Reset(float new_start_x, float new_start_y, float new_end_x, float new_end_y)
	{
		_start = new Point2D(new_start_x, new_start_y);
		_end = new Point2D(new_end_x, new_end_y);
		Status = MovingStatus.Moving;
	}
	
	public abstract void DrawMovingHintPoints(Canvas canvas, Paint paint);
	public abstract void DrawFinalHintPoints(Canvas canvas, Paint paint);
	public abstract void OnFinishMoving();
	
	public final int Id()
	{
		return _id;	
	}
	
	public final Point2D Start()
	{
		return _start;
	}
	
	public final Point2D End()
	{
		return _end;
	}
	
	public final void FinishMoving()
	{
		this.Status = MovingStatus.Done;
		OnFinishMoving();
	}
	

		
	protected final void DrawHintPoint(float x, float y, Canvas canvas)
	{
		canvas.drawCircle(x, y, _hintRadius, _hintPaint);
	}
	
	private void DrawHintPoints(Canvas canvas, Paint paint)
	{
		if(Status == MovingStatus.Moving)
		{
			DrawMovingHintPoints(canvas, paint);
		}
		else if(Status == MovingStatus.Done)
		{
			DrawFinalHintPoints(canvas, paint);
		}
	}
		
	protected Point2D _start;
	protected Point2D _end;
	private static Paint _hintPaint;
	protected static int _cid = 0;
	protected int _id;	
	protected static int _hintRadius;
	public List<Intersection> IntersectionPoints;
	public MovingStatus Status;
	
	@SuppressWarnings("unused")
	private GeoEntity()
	{
		
	}
	
	
	public enum MovingStatus
	{
		Moving,
		Done
	}
}
