package com.rich.edu.rulerandcompass.geo;

import java.util.Iterator;

import com.rich.edu.rulerandcompass.algo.Edge;
import com.rich.edu.rulerandcompass.algo.Graph;

import android.graphics.Canvas;
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
	}
	
	public GeoEntity(float start_x, float start_y, float end_x, float end_y)
	{
		this(new Point2D(start_x, start_y), new Point2D(end_x, end_y));
	}
	
	
	protected final void DrawIntersectionPoints(Canvas canvas)
	{
		Graph graph = Graph.GetGraph();
		Iterator<Edge> edges = graph.FindEdges(this);
        while(edges.hasNext())
        {  
        	Edge edge = edges.next();  
        	for(Point2D pt : edge.IntersectionPoints)
        	{
        		Util.DrawIntersectionPoint(canvas, pt);
        	}
        }
	}
	
	public void Draw(Canvas canvas, Paint paint)
	{
		DrawIntersectionPoints(canvas);

		DrawHintPoints(canvas, paint);
	}
	

	protected final void RemoveIntersections(GeoEntity entity)
	{
		Graph graph = Graph.GetGraph();
		Iterator<Edge> inEdges = graph.FindInEdges(this);
        while(inEdges.hasNext())
        {  
        	Edge edge = inEdges.next();  
        	if(edge.Target().Id() == entity.Id())
        	{
        		graph.RemoveEdge(edge);
        	}
        }

		Iterator<Edge> outEdges = Graph.GetGraph().FindOutEdges(this);
        while(outEdges.hasNext())
        {  
        	Edge edge = outEdges.next();  
        	if(edge.Source().Id() == entity.Id())
        	{
        		graph.RemoveEdge(edge);
        	}
        }
	}
	
	public abstract void CalcIntersection(GeoEntity entity);


	
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
	protected static int _cid = 0;
	protected int _id;	

	
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
