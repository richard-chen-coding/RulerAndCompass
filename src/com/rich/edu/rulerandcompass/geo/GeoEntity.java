package com.rich.edu.rulerandcompass.geo;

import com.rich.edu.rulerandcompass.algo.Graph;

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

	protected final void RemoveIntersections(GeoEntity entity)
	{
		Graph graph = Graph.GetGraph();
		graph.RemoveEdges(this, entity);
	}
	
	public abstract void CalcIntersection(GeoEntity entity);


	
	public void Reset(float new_start_x, float new_start_y, float new_end_x, float new_end_y)
	{
		_start = new Point2D(new_start_x, new_start_y);
		_end = new Point2D(new_end_x, new_end_y);
		Status = MovingStatus.Moving;
	}
	

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
	
	protected Point2D _start;
	protected Point2D _end;
	protected static int _cid = 0;
	protected int _id;	

	public boolean equals(Object o)
	{
		GeoEntity e = (GeoEntity)o;
		if(e != null)
		{
			return e.Id() == Id();
		}
		return false;			
	}
	
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
