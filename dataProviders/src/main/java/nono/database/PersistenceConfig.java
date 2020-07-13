package nono.database;

import javax.persistence.EntityManagerFactory;

/**
 *
 * @author chusnaval
 *
 */
public interface PersistenceConfig {

	EntityManagerFactory getFactory();

}
