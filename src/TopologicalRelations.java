public class TopologicalRelations {
	/*
	 * Area Equals Area
	 */
	public static void AreaEqualsArea(){
		String query =
				"SELECT a.coords FROM Area a,"
						+ " Area b "
						+ "WHERE a.coords && b.coords AND ST_Equals(a.coords, b.coords) = 1";

		IgniteStart_New.answerQuery(query, "Query 1 Area Equals Area:");
	}
	
	/*public static void AreaEqualsArea(){
		String query =
				"SELECT DISTINCT a.coords FROM Area a,"
						+ " Area b "
						+ "WHERE ST_Equals(a.coords, b.coords)";

		IgniteMain.answerQuery(query, "Query 1 Area Equals Area:");
	}*/

	/*
	 * Point Equals Point
	 */
	public static void PointEqualsPoint(){
		String query =
				"SELECT a.coords FROM Point a,"
						+ " Point b "
						+ "WHERE a.coords && b.coords AND ST_Equals(a.coords, b.coords) = 1";

		IgniteStart_New.answerQuery2(query, "Query 2 Point Equals Point:");
	}
	
	
	/*
	 * Point Intersects Area
	 */
	public static void PointIntersectsArea(){
		String query = 
				"SELECT a.coords FROM Area a,"
						+ " \"" + IgniteStart_New.POINT_CACHE + "\".Point b "
						+ "WHERE a.coords && b.coords AND ST_Intersects(a.coords, b.coords) = 1";

		IgniteStart_New.answerQuery(query, "Query 3 Point Intersects Area:");
	}

	/*
	 * Point Intersects Line
	 */
	public static void PointIntersectsLine(){
		String query = 
				"SELECT a.coords FROM Point a,"
						+ " \"" + IgniteStart_New.EDGE_CACHE + "\".Edge b "
						+ "WHERE a.coords && b.coords AND ST_Intersects(a.coords, b.coords) = 1";

		IgniteStart_New.answerQuery2(query, "Query 4 Point Intersects Line:");
	}
	
	/*
	 * Point Intersects Line
	 */
	public static void PointIntersectsLine2(){
		String query = 
				"SELECT a.coords FROM"
						+ " \"" + IgniteStart_New.POINT_CACHE + "\".Point a,"
						+ " \"" + IgniteStart_New.EDGE_CACHE + "\".Edge b "
						+ "WHERE a.coords && b.coords AND ST_Intersects(a.coords, b.coords) = 1";

		IgniteStart_New.answerQuery(query, "Query 4 Point Intersects Line:");
	}

	/*
	 * Line Intersects Area
	 */
	public static void LineIntersectsArea(){
		String query = 
				"SELECT a.coords FROM Area a,"
						+ " \"" + IgniteStart_New.EDGE_CACHE + "\".Edge b "
						+ "WHERE a.coords && b.coords AND ST_Intersects(b.coords, a.coords) = 1";

		IgniteStart_New.answerQuery(query, "Query 5 Line Intersects Area:");
	}
	
	/*
	 * Area Touches AreaWater
	 */
	public static void AreaTouchesAreaWater(){
		String query = 
				"SELECT a.coords FROM Area a,"
						+ " \"" + IgniteStart_New.WATER_AREA_CACHE + "\".WaterArea b "
						+ "WHERE a.coords && b.coords AND ST_Touches(a.coords, b.coords) = 1";

		IgniteStart_New.answerQuery(query, "Query 6 Area Touches AreaWater:");
	}
	
	/*
	 * Line Touches Area
	 */
	public static void LineTouchesArea(){
		String query = 
				"SELECT a.coords FROM Area a,"
						+ " \"" + IgniteStart_New.EDGE_CACHE + "\".Edge b "
						+ "WHERE a.coords && b.coords AND ST_Touches(a.coords, b.coords) = 1";

		IgniteStart_New.answerQuery(query, "Query 7 Line Touches Area:");
	}

	/*
	 * Area Overlaps AreaWater
	 */
	public static void AreaOverlapsAreaWater(){
		String query = 
				"SELECT a.coords FROM Area a,"
						+ " \"" + IgniteStart_New.WATER_AREA_CACHE + "\".WaterArea b "
						+ "WHERE a.coords && b.coords AND ST_Overlaps(a.coords, b.coords) = 1";

		IgniteStart_New.answerQuery(query, "Query 8 Area Overlaps AreaWater:");
	}

	/*
	 * Area Contains AreaWater
	 */
	public static void AreaContainsAreaWater(){
		String query = 
				"SELECT a.coords FROM Area a,"
						+ " \"" + IgniteStart_New.WATER_AREA_CACHE + "\".WaterArea b "
						+ "WHERE a.coords && b.coords AND ST_Contains(a.coords, b.coords) = 1";

		IgniteStart_New.answerQuery(query, "Query 9 Area Contains AreaWater:");
	}

	/*
	 * AreaWater Within Area
	 */
	public static void AreaWithinAreaWater(){
		String query = 
				"SELECT a.coords FROM Area a,"
						+ " \"" + IgniteStart_New.WATER_AREA_CACHE + "\".WaterArea b "
						+ "WHERE a.coords && b.coords AND ST_Within(a.coords, b.coords) = 1";

		IgniteStart_New.answerQuery(query, "Query 10 AreaWater Within Area:");
	}
	
	/*
	 * Point Within Area
	 */
	public static void PointWithinArea(){
		String query = 
				"SELECT a.coords FROM Area a,"
						+ " \"" + IgniteStart_New.POINT_CACHE + "\".Point b "
						+ "WHERE a.coords && b.coords AND ST_Within(a.coords, b.coords) = 1";

		IgniteStart_New.answerQuery(query, "Query 11 Point Within Area:");
	}
	
	/*
	 * Line Within Area
	 */
	public static void LineWithinArea(){
		String query = 
				"SELECT a.coords FROM Area a,"
						+ " \"" + IgniteStart_New.EDGE_CACHE + "\".Edge b "
						+ "WHERE a.coords && b.coords AND ST_Within(a.coords, b.coords) = 1";

		IgniteStart_New.answerQuery(query, "Query 12 Line Within Area:");
	}
	
	/*
	 * Line Crosses Area
	 */
	public static void LineCrossesArea(){
		String query = 
				"SELECT a.coords FROM Area a,"
						+ " \"" + IgniteStart_New.EDGE_CACHE + "\".Edge b "
						+ "WHERE a.coords && b.coords AND ST_Crosses(a.coords, b.coords) = 1";

		IgniteStart_New.answerQuery(query, "Query 13 Line Crosses Area:");
	}
	
	/*
	 * Lines Crosses Lines
	 */
	/*public static void LineCrossesLine2(){
		String query = 
				"SELECT DISTINCT a.coords FROM "
						+ " \"" + App.EDGE_CACHE + "\".Edge a, "
						+ " \"" + App.EDGE_CACHE + "\".Edge b "
						+ "WHERE a.coords && b.coords AND ST_Crosses(a.coords, b.coords) = 1";

		App.answerQuery(query, "Query 14 Lines Crosses Lines:");
	}*/
	
	public static void LineCrossesLine(){
		String query =
				"SELECT a.coords FROM Edge a,"
						+ " Edge b "
						+ "WHERE a.coords && b.coords AND ST_Crosses(a.coords, b.coords) = 1";
		
		IgniteStart_New.answerQuery3(query, "Query 14 Lines Crosses Lines:");
	}

	/*
	 * Lines Crosses Lines
	 */
	public static void runQueries(){
		System.out.println("Running Topological Relations Queries>>>\n");
		//AreaEqualsArea();
		//PointEqualsPoint();
		//PointIntersectsArea();		
		//PointIntersectsLine();		
		//AreaTouchesAreaWater();
		LineTouchesArea();
		//AreaOverlapsAreaWater();
		//AreaContainsAreaWater();
		//AreaWithinAreaWater();
		//PointWithinArea();
		//LineWithinArea();
		//LineCrossesArea();
		//LineIntersectsArea();
		//LineCrossesLine();	
	}
}
