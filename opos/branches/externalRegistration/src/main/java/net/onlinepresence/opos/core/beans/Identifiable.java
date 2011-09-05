package net.onlinepresence.opos.core.beans;

import java.io.Serializable;

/**
 * Object that have an unique identifier.
 * 
 * @author Dragan Djuric <dragan@dragandjuric.com/>
 * @since Mar 2, 2005
 **/
public interface Identifiable {

    /**
     * Gets the element's unique identifier.
     * @return the identifier
     */
    Serializable get_id();
    void set_id(Serializable id);
    
    String get_uuid();

}
