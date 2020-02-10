import org.apache.ignite.cache.query.annotations.QuerySqlFunction;

import com.vividsolutions.jts.geom.Geometry;

public class GeometryFunctions{
	
    	@QuerySqlFunction
    	public static int ST_Contains(Geometry A, Geometry B){    		
    		if( A.contains(B))
    			return 1;
    		return 0;    				
    	}
    	
    	@QuerySqlFunction
    	public static Geometry ST_ConvexHull(Geometry A){
    		return A.convexHull();
    	}
    	
    	@QuerySqlFunction
    	public static Geometry ST_Buffer(Geometry A, double buffer_radius){
    		return A.buffer(buffer_radius);
    	}
    	
    	@QuerySqlFunction
    	public static double ST_Area(Geometry A){
    		return A.getArea();
    	}
    	
    	@QuerySqlFunction
    	public static double ST_Distance(Geometry A, Geometry B){
    		return A.distance(B);
    	}
    	
    	@QuerySqlFunction
    	public static int ST_Dimension(Geometry A){
    		return A.getDimension();
    	}
    	
    	@QuerySqlFunction 
    	public static int ST_Touches(Geometry A, Geometry B){
    		if(A.touches(B))
    			return 1;
    		return 0;
    	}
    	
    	@QuerySqlFunction
    	public static int ST_Equals(Geometry A, Geometry B){
    		if(A.equals(B))
    			return 1;
    		return 0;
    	}
    	
    	@QuerySqlFunction
    	public static int ST_Disjoint(Geometry A, Geometry B){
    		if(A.disjoint(B))
    			return 1;
    		return 0;
    	}
    	
    	@QuerySqlFunction
    	public static int ST_Crosses(Geometry A, Geometry B){
    		if(A.crosses(B))
    			return 1;
    		return 0;
    	}
    	
    	@QuerySqlFunction
    	public static int ST_Within(Geometry A, Geometry B){
    		if(A.within(B))
    			return 1;
    		return 0;
    	}
    	
    	@QuerySqlFunction
    	public static double ST_Length(Geometry A){
    		return A.getLength();
    	}
    	
    	@QuerySqlFunction
    	public static Geometry ST_Envelope(Geometry A){
    		return A.getEnvelope();
    	}
    	
    	@QuerySqlFunction
    	public static int ST_Intersects(Geometry A, Geometry B){
    		if( A.intersects(B) )
    			return 1;    		
    		return 0;
    	}
    	
    	@QuerySqlFunction
    	public static int ST_Overlaps(Geometry A, Geometry B){
    		if( A.overlaps(B))
    			return 1;    		
    		return 0;    				
    	}    	
    	
    	@QuerySqlFunction
    	public static int ST_Covers(Geometry A, Geometry B){
    		if( A.covers(B))
    			return 1;    		
    		return 0;    				
    	} 
    }