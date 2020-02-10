import java.io.Serializable;

import org.apache.ignite.cache.query.annotations.QuerySqlField;

import com.vividsolutions.jts.geom.Geometry;

public class Edge implements Serializable {
        /**
	 * 
	 */
	private static final long serialVersionUID = 2L;

		/** Coordinates. */
    	@QuerySqlField(index = true)
        private Geometry coords;

    	/*@QuerySqlField(index = true)
        private String name;*/
        
        /**
         * @param coords Coordinates.
         */
        public Edge(Geometry coords) {
            this.coords = coords;
        }
        
        public Geometry getGeom(){
        	return coords;
        }

    }
