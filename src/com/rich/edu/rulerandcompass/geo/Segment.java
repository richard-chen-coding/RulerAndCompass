package com.rich.edu.rulerandcompass.geo;

import java.util.Collection;

import com.rich.edu.rulerandcompass.algo.Edge;
import com.rich.edu.rulerandcompass.algo.Graph;

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
		super.RemoveIntersections(entity);
		
		if(entity instanceof Segment)
		{
			Segment line = (Segment)entity;
			Point2D pt = GeoUtil.get_line_intersection(Start().X, Start().Y, End().X, End().Y, line.Start().X, line.Start().Y, line.End().X, line.End().Y);
			if(pt != null)
			{
				Edge edge = new Edge(this, entity, new Point2D[]{pt});
				Graph.GetGraph().AddEdge(edge);
			}
		}
		else if(entity instanceof Circle)
		{
			Circle circle = (Circle)entity;
			Collection<Point2D> pts = GeoUtil.get_circle_line_intersections(circle.Center(), circle.Radius(), Start(), End());
			for(Point2D pt : pts)
			{
				Edge edge = new Edge(this, entity, new Point2D[]{pt});
				Graph.GetGraph().AddEdge(edge);
			}
		}
		else
		{
			 throw new IllegalArgumentException("Unsupported GeoEntity Type");
		}
	}

	
	@Override
	public void OnFinishMoving() 
	{
		//Nothing to do in segment		
	}
}
