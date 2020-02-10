
import java.io.Serializable;

import org.apache.ignite.cache.affinity.AffinityKeyMapped;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import com.vividsolutions.jts.geom.Geometry;

public class WaterArea implements Serializable {
        /**
	 * l
	 */
	private static final long serialVersionUID = 4L;

		/** Coordinates. */
    	@QuerySqlField(index = true)
        private Geometry coords;

    	/*
    	@QuerySqlField(index = true)
        private String name;
        */
        
        /**
         * @param coords Coordinates.
         */
        public WaterArea(Geometry coords) {
            this.coords = coords;
        }
        
        public Geometry getGeom(){
        	return coords;
        }
    }
