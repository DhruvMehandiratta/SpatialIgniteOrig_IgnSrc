import java.io.Serializable;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import com.vividsolutions.jts.geom.Geometry;

public class Point implements Serializable {
        /**
	 * 
	 */
	private static final long serialVersionUID = 3L;

		/** Coordinates. */
    	@QuerySqlField(index = true)
        private Geometry coords;

    	/*@QuerySqlField(index = true)
        private String name;*/
        
        /**
         * @param coords Coordinates.
         */
       	public Point(Geometry coords) {
            this.coords = coords;
        }
        
        public Geometry getGeom(){
        	return coords;
        }

    }
