package net.onlinepresence.opos.core.hibernate;

import net.sf.ehcache.CacheManager;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.cache.Cache;
import org.hibernate.cache.CacheException;
import org.hibernate.cache.CacheProvider;
import org.hibernate.cache.Timestamper;

import java.util.Properties;

/**
 * @author Les Hazlewood
 */
public class ExternalEhCacheProvider implements CacheProvider {

	protected transient final Log log = LogFactory.getLog(getClass());

	private static CacheManager cacheManager = null;

	/**
	 * This is the method that is called by an external framework (e.g. Spring) to set the
	 * constructed CacheManager for all instances of this class. Therefore, when
	 * Hibernate instantiates this class, the previously statically injected CacheManager
	 * will be used for all hibernate calls to build caches.
	 * @param cacheManager the CacheManager instance to use for a HibernateSession factory using
	 * this class as its cache.provider_class.
	 */
	public static void setCacheManager(CacheManager cacheManager) {
		ExternalEhCacheProvider.cacheManager = cacheManager;
	}

	public Cache buildCache(String name, Properties properties) throws CacheException {
		try {
			net.sf.ehcache.Ehcache cache = cacheManager.getEhcache(name);
			if (cache == null) {
				if ( log.isWarnEnabled() ) {
					log.warn( "Unable to find EHCache configuration for cache named [" + name + "]. Using defaults.");
				}
				cacheManager.addCache( name );
				cache = cacheManager.getEhcache(name);
				if (log.isDebugEnabled()) {
					log.debug("Started EHCache region '" + name + "'");
				}
			}
			return new net.sf.ehcache.hibernate.EhCache(cache);
		} catch (net.sf.ehcache.CacheException e) {
			throw new CacheException(e);
		}
	}

	public long nextTimestamp() {
		return Timestamper.next();
	}

	public void start(Properties properties) throws CacheException {
		//ignored, CacheManager lifecycle handled by the IoC container
	}

	public void stop() {
		//ignored, CacheManager lifecycle handled by the IoC container
	}

	public boolean isMinimalPutsEnabledByDefault() {
		return false;
	}
}
