package com.rich.edu.rulerandcompass.algo;

import com.rich.edu.rulerandcompass.geo.GeoEntity;
import com.rich.edu.rulerandcompass.geo.Point2D;

public class Edge 
{
	private  GeoEntity _source;
    private  GeoEntity _target;
    
    public Point2D[] IntersectionPoints;

	public GeoEntity Source() 
	{
		return _source;
	}


	public GeoEntity Target() 
	{
		return _target;
	}
	
	 public Edge(GeoEntity source, GeoEntity target, Point2D[] pts)
     {
		 if(source == null || target == null)
		 {
			 throw new IllegalArgumentException("Can not construct an Edge with null Vertex");
		 }
		 
		 if(pts == null || pts.length == 0)
		 {
			 throw new IllegalArgumentException("Need at least one Intersection Point"); 
		 }
		 
         _source = source;
         _target = target;
         IntersectionPoints = pts;
     }
	 
	@SuppressWarnings("unused")
	private Edge()
	{
	
	}
	
	public enum IntersectType
	{
		Vertical
	}
}
