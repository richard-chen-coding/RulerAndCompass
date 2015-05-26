package com.rich.edu.rulerandcompass.algo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.rich.edu.rulerandcompass.geo.GeoEntity;

public class Graph 
{

	private static Graph _grpah;
	
	public static Graph GetGraph()
	{
		if(_grpah == null)
		{
			_grpah = new Graph();
		}
		return _grpah;
	}
	
	private Graph()
	{
		_inEdgeMap = new HashMap<GeoEntity, List<Edge>>();
		_outEdgeMap = new HashMap<GeoEntity, List<Edge>>();
	}
	
	private Map<GeoEntity, List<Edge>> _inEdgeMap ;
	
	private Map<GeoEntity, List<Edge>> _outEdgeMap ;
	
	/*
	public Iterator<GeoEntity> Vertexs() {
		// TODO Auto-generated method stub
		return null;
	}


	public Iterator<Edge> Edges() {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterator<GeoEntity> FindVertexs(GeoEntity vertex) 
	{
		// TODO Auto-generated method stub
		return null;
	}

	*/
	public void RemoveEdge(Edge edge)
	{
		GeoEntity inEdge = edge.Source();
		if(_inEdgeMap.containsKey(inEdge))
		{
			List<Edge> list = _inEdgeMap.get(inEdge);
			list.remove(edge);

			_inEdgeMap.put(inEdge, list);
		}

		GeoEntity outEdge = edge.Target();
		if(_outEdgeMap.containsKey(outEdge))
		{
			List<Edge> list = _outEdgeMap.get(outEdge);
			list.remove(edge);

			_outEdgeMap.put(outEdge, list);
		}

	}
	
	
	public void AddEdge(Edge edge) 
	{
		GeoEntity inEdge = edge.Source();
		if(_inEdgeMap.containsKey(inEdge))
		{
			List<Edge> list = _inEdgeMap.get(inEdge);
			list.add(edge);

			_inEdgeMap.put(inEdge, list);
		}
		else
		{
			List<Edge> list = new ArrayList<Edge>();
			list.add(edge);
			_inEdgeMap.put(inEdge, list);
		}
		
		GeoEntity outEdge = edge.Target();
		if(_outEdgeMap.containsKey(outEdge))
		{
			List<Edge> list = _outEdgeMap.get(outEdge);
			list.add(edge);

			_outEdgeMap.put(outEdge, list);
		}
		else
		{
			List<Edge> list = new ArrayList<Edge>();
			list.add(edge);
			_outEdgeMap.put(outEdge, list);
		}
		
	}


	

	public void RemoveEdges(GeoEntity vertex1, GeoEntity vertex2)
	{
		Iterator<Edge> inEdges = FindInEdges(vertex1);
        while(inEdges.hasNext())
        {  
        	Edge edge = inEdges.next();  
        	if(edge.Target().equals(vertex2))
        	{
        		RemoveEdge(edge);
        	}
        }

		Iterator<Edge> outEdges = FindOutEdges(vertex1);
        while(outEdges.hasNext())
        {  
        	Edge edge = outEdges.next();  
        	if(edge.Source().Id() == vertex2.Id())
        	{
        		RemoveEdge(edge);
        	}
        }
	}


	public Iterator<Edge> FindInEdges(GeoEntity vertex) 
	{
		if(_inEdgeMap.containsKey(vertex))
		{
			return _inEdgeMap.get(vertex).iterator();
		}
		else
		{
			return (new ArrayList<Edge>()).iterator();
		}
	}
	
	public Iterator<Edge> FindOutEdges(GeoEntity vertex) 
	{
		if(_outEdgeMap.containsKey(vertex))
		{
			return _outEdgeMap.get(vertex).iterator();
		}
		else
		{
			return (new ArrayList<Edge>()).iterator();
		}
	}

	public Iterator<Edge> FindEdges(GeoEntity vertex) 
	{
		List<Edge> edges = new ArrayList<Edge>();
		
		if(_inEdgeMap.containsKey(vertex))
		{
			edges.addAll(_inEdgeMap.get(vertex));
		}
		if(_outEdgeMap.containsKey(vertex))
		{
			edges.addAll(_outEdgeMap.get(vertex));
		}
		
		return edges.iterator();
	}

}
