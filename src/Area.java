
import java.io.Serializable;

import org.apache.ignite.cache.affinity.AffinityKeyMapped;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import com.vividsolutions.jts.geom.Geometry;

public class Area implements Serializable {
    
	private static final long serialVersionUID = 1L;

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
     Area(Geometry coords) {
         this.coords = coords;
     }
        
     public Geometry getGeom(){
      	return coords;
     }
}
