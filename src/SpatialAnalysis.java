public class SpatialAnalysis {

    /*
     * Runs a query to find ConvexHull for all areas in Arealm.
     */
    public static void ConvexHullArea(){
        String query = "SELECT ST_ConvexHull(a.coords) FROM Area a";
        IgniteStart_New.answerQuery(query, "Query 1 ConvexHull(Arealm):");
    }
    
    /*
     * Runs a query to find ConvexHull for all areas in Arealm.
     */
    public static void ConvexHullPoint(){
        String query = "SELECT ST_ConvexHull(a.coords) FROM point a";
        IgniteStart_New.answerQuery2(query, "Query 1 ConvexHull(Pointlm):");
    }
    
    /*
     * Runs a query to find the Envelopes of all lines in Edges
     */
    /*public static void EnvelopeLine2(){
        String query = 
        		"SELECT ST_Envelope(a.coords) FROM "
                        + " \"" + App.EDGE_CACHE + "\".Edge a";
        App.answerQuery(query, "Query 2 Envelope(Lines):");
    }*/
    
    /*
     * Runs a query to find the Envelopes of all lines in Edges
     */
    public static void EnvelopeLine(){
        String query = "SELECT ST_Envelope(a.coords) FROM Edge a";
        IgniteStart_New.answerQuery3(query, "Query 2 Envelope(Lines):");
    }   
      
    /*
     * Runs a query to find the largest water area in AreaWater
     */
    /*public static void LargestWaterArea(){
        String query = 
        		"SELECT MAX(ST_Area(a.coords)) FROM "
                        + " \"" + App.WATER_AREA_CACHE + "\".WaterArea a ";
        App.answerQuery(query, "Query 3 Largest(WaterArea):");
    }*/
    
    /*
     * Runs a query to find the largest water area in AreaWater
     */
    public static void LargestWaterArea(){
        String query = "SELECT MAX(ST_Area(a.coords)) FROM WaterArea a";
        IgniteStart_New.answerQuery4(query, "Query 3 Largest(WaterArea):");
    }
    
    /*
     * Runs a query to find the total water area in AreaWater
     */
    /*public static void TotalWaterArea(){
        String query = 
        		"SELECT SUM(ST_Area(a.coords)) FROM "
                        + " \"" + App.WATER_AREA_CACHE + "\".WaterArea a ";
        App.answerQuery(query, "Query 4 Total(WaterArea):");
    }*/
    
    /*
     * Runs a query to find the total water area in AreaWater
     */
    public static void TotalWaterArea(){
        String query = "SELECT SUM(ST_Area(a.coords)) FROM WaterArea a";
        IgniteStart_New.answerQuery4(query, "Query 4 Total(WaterArea):");
    }
        
    /*
     * Runs query to find the longest line
     */
    /*public static void LongestLine(){
        String query = 
        		"SELECT MAX(ST_Length(a.coords)) FROM "
                        + " \"" + App.EDGE_CACHE + "\".Edge a ";
        //System.out.println(query);
        App.answerQuery(query, "Query 5 Longest(Line):");
    }*/  
    
    /*
     * Runs query to find the longest line
     */
    public static void LongestLine(){
        String query = "SELECT MAX(ST_Length(a.coords)) FROM Edge a";
        //System.out.println(query);
        IgniteStart_New.answerQuery3(query, "Query 5 Longest(Line):");
    }  
    
    /*
     * Runs a query to find total line length in Edges
     */
    public static void TotalLineLength(){
        String query = "SELECT Sum(ST_Length(a.coords)) FROM Edge a";
        IgniteStart_New.answerQuery3(query, "Query 6 Total Length(Lines):");
    }
    
    /*
     * Runs a query to find Dimension of all polygons in Arealm.
     */
    public static void DimensionArea(){
        String query = "SELECT ST_Dimension(a.coords) FROM Area a";
        IgniteStart_New.answerQuery(query, "Query 7 Dimension(Area):");
    }
    
    
    /*
     * Runs a query to find buffer regions around one mile radius of all polygons in Arealm.
     */
    public static void BufferArea(){
        String query = "SELECT ST_Buffer(a.coords, 5820) FROM Area a";
        IgniteStart_New.answerQuery(query, "Query 8 Buffer(Area):");
    }
    
    /*
	 * Find all polygons in Arealm that are within 1000 distance units from a given point
	 * Point(-97.7,30.30)
	 */
	public static void DistanceSearch(){
		String query = 
				"SELECT a.coords FROM Area a "						
						+ "WHERE ST_Distance(a.coords, 'POINT(-97.7 30.30)') < 1000";

		IgniteStart_New.answerQuery(query, "Query 9 DistanceSearch(Area):");
	}
	
	/*
	 * Find all lines in Edges that are inside the bounding box of a given specification.
	 * 'POLYGON((-97.7 30.30, -92.7 30.30, -92.7 27.30, -97.7 27.30, -97.7 30.30))'
	 */
	public static void BoundingBoxSearch(){
		String query = 
				"SELECT a.coords FROM Edge a "						
						+ "WHERE ST_Within(a.coords, 'POLYGON((-97.7 30.30, -92.7 30.30, -92.7 27.30, -97.7 27.30, -97.7 30.30))')";

		IgniteStart_New.answerQuery3(query, "Query 10 BoundingBoxSearch(Line):");
	}
	
	/*
	 * Range Query(Area)
	 * Intersects
	 */
	public static void RangeQueryOnArea(){
		String query = 
				"SELECT a.coords FROM Area a "						
						+ "WHERE ST_Intersects(a.coords, 'POLYGON((-124.322996 -114.363988, -124.322996 42.001047, 32.664936 42.001047, 32.664936 -114.363988, -124.322996 -114.363988))')";

		IgniteStart_New.answerQuery(query, "Query 11 RangeQuery(Filter Intersects, Area):");
	}
	
	/*
	 * Range Query(AreaWater)
	 * Intersects
	 */
	public static void RangeQueryOnAreaWater(){
		String query = 
				"SELECT a.coords FROM WaterArea a "						
						+ "WHERE ST_Intersects(a.coords, 'POLYGON((-124.322996 -114.363988, -124.322996 42.001047, 32.664936 42.001047, 32.664936 -114.363988, -124.322996 -114.363988))')";

		IgniteStart_New.answerQuery4(query, "Query 12 RangeQuery(Filter Intersects, AreaWater):");
	}
	
	/*
	 * Range Query(Line)
	 * Intersects
	 */
	public static void RangeQueryOnLine(){
		String query = 
				"SELECT a.coords FROM Edge a "						
						+ "WHERE ST_Intersects(a.coords, 'POLYGON((-124.322996 -114.363988, -124.322996 42.001047, 32.664936 42.001047, 32.664936 -114.363988, -124.322996 -114.363988))')";

		IgniteStart_New.answerQuery3(query, "Query 13 RangeQuery(Filter Intersects, Line):");
	}
    
	/*
	 * Range Query(Point)
	 * Intersects
	 */
	public static void RangeQueryOnPoint(){
		String query = 
				"SELECT a.coords FROM Point a "						
						+ "WHERE ST_Intersects(a.coords, 'POLYGON((-124.322996 -114.363988, -124.322996 42.001047, 32.664936 42.001047, 32.664936 -114.363988, -124.322996 -114.363988))')";

		IgniteStart_New.answerQuery2(query, "Query 14 RangeQuery(Filter Intersects, Point):");
	}
	/*
	 * Find all lines in Edges that are inside the bounding box of a given specification.
	 * 'POLYGON((-97.7 30.30, -92.7 30.30, -92.7 27.30, -97.7 27.30, -97.7 30.30))'
	 */
	/*public static void RangeQueryOnArea2(){
		
		String query = 
				"SELECT a.coords FROM Area a "						
						+ "WHERE ST_Intersects(a.coords, 'POLYGON((-121.787053 37.692078,-121.787024 37.692125,-121.787022 37.692241,-121.787019 37.692503,-121.787016 37.692695,-121.787015 37.692756,-121.787015 37.692928,-121.78661 37.692918,-121.786416 37.692914,-121.786433 37.69211,-121.786648 37.692098,-121.787053 37.692078))')";

		App.answerQuery(query, "Query 11 RangeQuery(Area):");
	}*/
	
    /*
     * Runs a query to count all the water areas in AreaWater
     */
   /* public static void CountWaterArea(){
        String query = 
        		"SELECT COUNT(ST_Area(a.coords)) FROM "
                        + " \"" + App.WATER_AREA_CACHE + "\".WaterArea a ";
        App.answerQuery(query, "Query 2 Count(AreaWater): ");
    }*/
    
    /*
     * Runs a query to count all the water areas in AreaWater
     */
    /*public static void CountWaterArea2(){
        String query = 
        		"SELECT COUNT(ST_Area(a.coords)) runQueriesFROM WaterArea a";
        App.answerQuery4(query, "Query 2 Count(AreaWater): ");
    }*/
   
	
    public static void runQueries(){
		System.out.println("\nRunning Spatial Analysis Queries>>>\n");
		//ConvexHullArea();
		EnvelopeLine();
		//LargestWaterArea();
		//TotalWaterArea();
		LongestLine();
		TotalLineLength();
		//DimensionArea();
		//BufferArea();
		//DistanceSearch();
		BoundingBoxSearch();	
		//RangeQueryOnArea();
		//RangeQueryOnAreaWater();
		RangeQueryOnLine();
		//RangeQueryOnPoint();
		//ConvexHullPoint();
	}
}
