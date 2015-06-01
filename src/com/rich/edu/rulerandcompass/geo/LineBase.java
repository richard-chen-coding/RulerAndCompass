package com.rich.edu.rulerandcompass.geo;

import java.util.Collection;

import com.rich.edu.rulerandcompass.algo.Edge;
import com.rich.edu.rulerandcompass.algo.Graph;
import com.rich.edu.rulerandcompass.drawable.DrawUtil;
import com.rich.edu.rulerandcompass.drawable.IDrawable;

import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class LineBase extends GeoEntity implements IDrawable
{
	public LineBase(float start_x, float start_y, float end_x, float end_y) 
	{
		super(start_x, start_y, end_x, end_y);
		_slope = CalcSlope(start_x, start_y, end_x, end_y);
 	}
	
	public LineBase(Point2D start, Point2D end) 
	{
		super(start, end);
		_slope = CalcSlope(start.X, start.Y, end.X, end.Y);
	}
	
	protected float CalcSlope(float start_x, float start_y, float end_x, float end_y)
	{
		return (start_x - end_y) / (start_x - end_x);
		//return Math.abs((start_x - end_y) / (start_x - end_x));
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
		DrawIntersectionPoints(canvas);
		DrawHintPoints(canvas);
		canvas.drawLine(_start.X, _start.Y, _end.X, _end.Y, paint);
	}
	

	public void DrawIntersectionPoints(Canvas canvas)
	{
		Graph graph = Graph.GetGraph();
		Collection<Edge> edges = graph.FindEdges(this);
		for(Edge edge : edges)
		{
        	for(Point2D pt : edge.IntersectionPoints)
        	{
        		DrawUtil.DrawIntersectionPoint(canvas, pt);
        	}
		}
	}
	
	public void DrawHintPoints(Canvas canvas) 
	{
		DrawUtil.DrawHintPoint(canvas, _start);
		DrawUtil.DrawHintPoint(canvas, _end);
	}
	
	protected float _slope;
}
