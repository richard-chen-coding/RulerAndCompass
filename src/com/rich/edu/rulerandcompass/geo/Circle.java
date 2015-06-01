package com.rich.edu.rulerandcompass.geo;

import java.util.Collection;

import com.rich.edu.rulerandcompass.algo.Edge;
import com.rich.edu.rulerandcompass.algo.Graph;
import com.rich.edu.rulerandcompass.drawable.DrawUtil;
import com.rich.edu.rulerandcompass.drawable.IDrawable;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Circle extends GeoEntity  implements IDrawable
{
	public Circle(float start_x, float start_y, float end_x, float end_y) 
	{
		super(start_x, start_y, end_x, end_y);
		// TODO Auto-generated constructor stub
	}
	public Circle(Point2D start, Point2D end) 
	{
		super(start, end);
		// TODO Auto-generated constructor stub
	}
	
	public Point2D Center()
	{
		return Start();
	}
	
	public float Radius()
	{
		return GeoUtil.Length(Start(), End());
	}
	
	@Override
	public void CalcIntersection(GeoEntity entity) 
	{
		super.RemoveIntersections(entity);
		
		if(entity instanceof Segment)
		{
			Segment line = (Segment)entity;
			Collection<Point2D> pts = GeoUtil.get_circle_line_intersections(Center(), Radius(), line.Start(), line.End());
			for(Point2D pt : pts)
			{
				Edge edge = new Edge(this, entity, new Point2D[]{pt});
				Graph.GetGraph().AddEdge(edge);
			}
		}
		else if(entity instanceof Circle)
		{
			Circle circle = (Circle)entity;
			Collection<Point2D> pts = GeoUtil.get_circle_circle_intersections(Center(), Radius(), circle.Center(), circle.Radius());
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
	public void OnFinishMoving() {
		// TODO Auto-generated method stub
		
	}
	
	public void Draw(Canvas canvas, Paint paint)
	{
		DrawIntersectionPoints(canvas);
		DrawHintPoints(canvas);
		canvas.drawCircle(Center().X, Center().Y, Radius(), paint);
	}
	
	
	public void DrawHintPoints(Canvas canvas) 
	{
		DrawUtil.DrawHintPoint(canvas, Center());
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

}
