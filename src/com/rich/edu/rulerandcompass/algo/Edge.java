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
	
	public Edge(GeoEntity entity1, GeoEntity entity2)
	{
		 if(entity1 == null || entity2 == null)
		 {
			 throw new IllegalArgumentException("Can not construct an Edge with null Vertex");
		 }
		 
		 if(entity1.Id() == entity2.Id())
		 {
			 throw new IllegalArgumentException("Can not create an edge with two identical entities"); 
		 }
		 
		 if(entity1.Id() < entity2.Id())
		 {
	         _source = entity1;
	         _target = entity2;
		 }
		 else
		 {
	         _source = entity2;
	         _target = entity1;
		 }
		 IntersectionPoints = new Point2D[]{};
	}
	
	 public Edge(GeoEntity entity1, GeoEntity entity2, Point2D[] pts)
     {

		 
		 /*
		 if(pts == null || pts.length == 0)
		 {
			 throw new IllegalArgumentException("Need at least one Intersection Point"); 
		 }
		 		 */



		 this(entity1,entity2);
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
