package com.rich.edu.rulerandcompass.geo;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Segment  extends LineBase
{
	public Segment(float start_x, float start_y, float end_x, float end_y) 
	{
		super(start_x, start_y, end_x, end_y);
	}

	public Segment(Point2D start, Point2D end) 
	{
		super(start, end);
	}


	@Override
	public void CalcIntersection(GeoEntity entity) 
	{
		super.CalcIntersection(entity);
		
		if(entity instanceof Segment)
		{
			Segment line = (Segment)entity;
			Point2D pt = Util.get_line_intersection(Start().X, Start().Y, End().X, End().Y, line.Start().X, line.Start().Y, line.End().X, line.End().Y);
			if(pt != null)
			{
				Intersection insPt = new Intersection(pt, this, entity);
				this.IntersectionPoints.add(insPt);
				entity.IntersectionPoints.add(insPt);
			}
		}
		else
		{
			 throw new IllegalArgumentException("Unsupported GeoEntity Type");
		}
	}

	@Override
	public void DrawFinalHintPoints(Canvas canvas, Paint paint) 
	{
		super.DrawHintPoint(_start.X, _start.Y, canvas);
		super.DrawHintPoint(_end.X, _end.Y, canvas);
	}
	
	@Override
	public void OnFinishMoving() 
	{
		//Nothing to do in segment		
	}
}
