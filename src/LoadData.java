import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.io.WKTReader;

public class LoadData {

	static String areaId = "";
	static String pointId = "";
	static String edgeId = "";
	static String waterAreaId = "";

	public static void loadData(String datasetpath, String [] datasets){

		loadAreasFromFile(datasetpath, datasets);
		//loadWaterAreasFromFile(datasetpath, datasets);
		//loadPointsFromFile(datasetpath, datasets);
		loadEdgesFromFile(datasetpath, datasets);
	}

	public static Connection getConnection4Server(String serverIP) throws SQLException {	

		String connectionUrl = "";
		String login = "";
		String password = "";

		try {
			serverIP = serverIP.replace('.', '_');
			ResourceBundle rb = ResourceBundle.getBundle(getDBConnectionProperties());
			connectionUrl = rb.getString("url_" + serverIP);
			login = rb.getString("user");
			password = rb.getString("password");

			Class.forName(getDriver());
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		// Connect to database.
		System.out.println("Connecting to database: url=" + connectionUrl + " user=" + login + " password=" + password);
		Connection conn = DriverManager.getConnection(connectionUrl, login, password);
		return conn; 
	}

	public static String getDriver() {
		return "org.postgresql.Driver";
	}

	//check
	public static String getDBConnectionProperties() {
		return "connection_postgres_spatial";
	}

	private static void loadAreaFromPostgres(String server_ip){

		long startTime = System.currentTimeMillis();
		WKTReader r = new WKTReader();
		try{

			//Class.forName("org.postgresql.Driver");

			//connect to the database
			//Connection   conn  = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydb", "bigdata", "");
			Connection conn = null;

			try {
				conn = getConnection4Server(server_ip);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return;
			}

			Statement stmt = conn.createStatement();

			//Execute query 
			String queryString = "SELECT ST_AsText(geom) FROM arealm_merge_ca";
			ResultSet rs = stmt.executeQuery (queryString);

			//Print the result

			while(rs.next()){

				Geometry geo = r.read( rs.getString(1) );
				//IgniteMain.areaCache.withKeepBinary().put(areaId++, new Area(geo));
				//IgniteStart.areaCache.put(areaId++, new Area(geo));
				//System.out.println("LOADING " + rs.getString(2) );
			}
			//close resources
			rs.close();
			stmt.close();
			conn.close();  	   
		} 
		catch(Exception e) {
			e.printStackTrace(); 
		}
		//System.out.println("LOADED AREAS " + (--areaId) + " IN " + (System.currentTimeMillis() - startTime) + "ms");
	}

	private static void loadAreasFromFile(String datasetPath, String [] datasets) {

		String fullFilePath = datasetPath + "/" + datasets[0] + ".csv";
		System.out.println("Arealm Path: " + fullFilePath);

		long startTime = System.currentTimeMillis();
		WKTReader rd = new WKTReader();
		int recordId = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(fullFilePath)));
			String line = null;

			while((line = br.readLine()) != null) {				
				String[] parts = line.split("\\|");
				//System.out.println(parts[1].trim());

				//for grid partition
				//areaId = parts[0].trim() + "." + (++recordId);	//partitionId.recordId			
				//Geometry geo = rd.read(parts[1].trim());				
				
				//for niharika partition
				areaId = parts[0].trim() + "." + parts[1].trim();	//partitionId.gid			
				Geometry geo = rd.read(parts[2].trim());

				IgniteStart_New.areaCache.put(areaId, new Area(geo));
				//IgniteStart.areaCache.put(areaId++, new Area(geo));
				//recordId++; //remove for gird
			}
			//close resources
			br.close(); 	   
		} 
		catch(Exception e) {
			e.printStackTrace(); 
		}
		System.out.println("LOADED AREAS " + (recordId) + " IN " + (System.currentTimeMillis() - startTime) + "ms");
	}


	private static void loadEdge(String server_ip){

		long startTime = System.currentTimeMillis();
		WKTReader r = new WKTReader();
		
		try {

			Class.forName("org.postgresql.Driver");

			//connect to the database
			//Connection   conn  = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydb", "bigdata", "");
			//Connection   conn  = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydb", "dbuser", "");

			Connection conn = null;

			try {
				conn = getConnection4Server(server_ip);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return;
			}

			Statement stmt = conn.createStatement();

			//Execute query 
			String queryString = "SELECT ST_AsText(geom) FROM edges_merge_ca";
			ResultSet rs = stmt.executeQuery(queryString);

			//Print the result
			while(rs.next()){

				Geometry geo = r.read( rs.getString(1) );

				//IgniteStart.edgeCache.put(edgeId++, new Edge(geo));
				//System.out.println("LOADING " + rs.getString(2));
			}
			//close resources
			rs.close();
			stmt.close();
			conn.close();  	   
		} 
		catch(Exception e) {
			e.printStackTrace(); 
		}
		//System.out.println("LOADED EDGES " + (--edgeId) + " IN " + (System.currentTimeMillis() - startTime) + "ms");
	}

	private static void loadEdgesFromFile(String datasetPath, String [] datasets) {
		
		String fullFilePath = datasetPath + "/" + datasets[3] + ".csv";
		System.out.println("Edges Path: " + fullFilePath);

		long startTime = System.currentTimeMillis();
		WKTReader rd = new WKTReader();
		long recordId = 0L;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(fullFilePath)));
			String line = null;

			while((line = br.readLine()) != null) {				
				String[] parts = line.split("\\|");
				//System.out.println(parts[1].trim());

				//for grid partitioning
				//edgeId = parts[0].trim() + "." + (++recordId);	//partitionId.recordId			
				//Geometry geo = rd.read(parts[1].trim());
			
				//for niharika partition
				edgeId = parts[0].trim() + "." + parts[1].trim();	//partitionId.gid			
				Geometry geo = rd.read(parts[2].trim());

				IgniteStart_New.edgeCache.put(edgeId, new Edge(geo));
				//IgniteStart.pointCache.put(edgeId++, new Area(geo));
				/*if(recordId > 100000L) {
					break;
				}*/
				//recordId++;
			}
			//close resources
			br.close(); 	   
		} 
		catch(Exception e) {
			e.printStackTrace(); 
		}
		System.out.println("LOADED EDGES " + (recordId) + " IN " + (System.currentTimeMillis() - startTime) + "ms");
	}

	private static void loadPoint(String server_ip){

		long startTime = System.currentTimeMillis();
		WKTReader r = new WKTReader();
		
		try {
			Class.forName("org.postgresql.Driver");

			// connect to the database
			//Connection   conn  = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydb", "bigdata", "");
			//Connection   conn  = DriverManager.getConnection("jdbc:postgresql://192.168.100.5:5433/mydb", "dbuser", "");

			Connection conn = null;

			try {
				conn = getConnection4Server(server_ip);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return;
			}

			Statement stmt = conn.createStatement();

			//Execute query 
			String queryString = "SELECT ST_AsText(geom) FROM pointlm_merge_ca";
			ResultSet rs = stmt.executeQuery(queryString);

			//Print the result
			while(rs.next()){
				Geometry geo = r.read( rs.getString(1) );
				//IgniteStart.pointCache.put(pointId++, new Point(geo));
				//System.out.println("LOADING " + rs.getString(2) );
			}
			//close resources
			rs.close();
			stmt.close();
			conn.close();  	   
		} 
		catch(Exception e) {
			e.printStackTrace(); 
		}
		//System.out.println("LOADED POINTS " + (--pointId) + " IN " + (System.currentTimeMillis() - startTime) + "ms");
	}

	private static void loadPointsFromFile(String datasetPath, String [] datasets) {	
		
		String fullFilePath = datasetPath + "/" + datasets[2] + ".csv";
		System.out.println("Pointlm Path: " + fullFilePath);

		long startTime = System.currentTimeMillis();
		WKTReader rd = new WKTReader();
		int recordId = 0;
		
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(fullFilePath)));
			String line = null;

			while((line = br.readLine()) != null) {				
				String[] parts = line.split("\\|");
				//System.out.println(parts[1].trim());

				//for grid partitioning
				pointId = parts[0].trim() + "." + (++recordId);	//partitionId.recordId			
				Geometry geo = rd.read(parts[1].trim());
				
				//for niharika partition
				//pointId = parts[0].trim() + "." + parts[1].trim();	//partitionId.gid			
				//Geometry geo = rd.read(parts[2].trim());

				IgniteStart_New.pointCache.put(pointId, new Point(geo));
				//IgniteStart.pointCache.put(pointId++, new Area(geo));		
				//recordId++; //remove for grid
			}
			//close resources
			br.close(); 	   
		} 
		catch(Exception e) {
			e.printStackTrace(); 
		}
		System.out.println("LOADED POINTS " + (recordId) + " IN " + (System.currentTimeMillis() - startTime) + "ms");
	}

	private static void loadWaterAreaFromPostgres(String server_ip){

		long startTime = System.currentTimeMillis();
		WKTReader r = new WKTReader();
		try {

			Class.forName("org.postgresql.Driver");

			//connect to the database
			//Connection   conn  = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydb", "bigdata", "");
			//Connection   conn  = DriverManager.getConnection("jdbc:postgresql://192.168.100.5:5433/mydb", "dbuser", "");

			Connection conn = null;

			try {
				conn = getConnection4Server(server_ip);
			}
			catch(Exception e)
			{
				e.printStackTrace();
				return;
			}

			Statement stmt = conn.createStatement();

			//Execute query 
			String queryString = "SELECT ST_AsText(geom) FROM areawater_merge_ca";
			ResultSet rs = stmt.executeQuery(queryString);

			//Print the result

			while(rs.next()){
				if( rs.getString(1) == null) continue;

				Geometry geo = r.read( rs.getString(1) );
				//IgniteStart.waterAreaCache.put(waterAreaId++, new WaterArea(geo));
				//System.out.println("LOADING " + rs.getString(2));
			}
			//close resources
			rs.close();
			stmt.close();
			conn.close();  	   
		} 
		catch(Exception e) {
			e.printStackTrace(); 
		}//Geometry geo = rd.read(line.trim());
		//System.out.println("LOADED WATER AREAS " + (--waterAreaId) + " IN " + (System.currentTimeMillis() - startTime) + "ms");
	}


	private static void loadWaterAreasFromFile(String datasetPath, String [] datasets) {
		
		String fullFilePath = datasetPath + "/" + datasets[1] + ".csv";
		System.out.println("AreaWater Path: " + fullFilePath);

		long startTime = System.currentTimeMillis();
		WKTReader rd = new WKTReader();
		int recordId = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(new File(fullFilePath)));
			String line = null;

			while((line = br.readLine()) != null) {				
				String[] parts = line.split("\\|");
				//System.out.println(parts[1].trim());

				//for grid partition
				waterAreaId = parts[0].trim() + "." + (++recordId);	//partitionId.recordId			
				Geometry geo = rd.read(parts[1].trim());
				
				//for niharika partition
				//waterAreaId = parts[0].trim() + "." + parts[1].trim();	//partitionId.gid			
				//Geometry geo = rd.read(parts[2].trim());

				IgniteStart_New.waterAreaCache.put(waterAreaId, new WaterArea(geo));
				//IgniteStart.pointCache.put(waterAreaId++, new Area(geo));
				//recordId++; //remove for gird
			}
			//close resources
			br.close(); 	   
		} 
		catch(Exception e) {
			e.printStackTrace(); 
		}
		System.out.println("LOADED WATER AREAS " + (recordId) + " IN " + (System.currentTimeMillis() - startTime) + "ms");
	}
}
