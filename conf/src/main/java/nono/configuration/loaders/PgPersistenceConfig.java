package nono.configuration.loaders;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.blade.ioc.annotation.Bean;

import lombok.extern.slf4j.Slf4j;
import nono.database.PersistenceConfig;

/**
 * The perfect implementation of Singleton design pattern Properly solves all
 * the below mentioned problems in Singleton pattern
 * <p>
 * 1) Attack using Reflection API 2) Problems from serialization/deserialization
 * of your object 3) Problems from cloning your object 4) Uncertainty in a
 * multi-threaded environment
 *
 * Problems with Garbage Collection have already been fixed in prior versions of
 * Java
 * <p>
 * Created by aritraroy on 26/05/16.
 */
@Slf4j
@Bean("PersistenceConfig")
public class PgPersistenceConfig implements PersistenceConfig {

	private static final String PERSISTENCE_UNIT_NAME = "pg_nono_unit";

	private static volatile PgPersistenceConfig singleton;

	private EntityManagerFactory factory;

	public PgPersistenceConfig() {
		// Preventing attack by Reflection APIs
		if (singleton != null) {
			throw new RuntimeException(
					"Cannot instantiate single object using constructor. Use its #getInstance() method");
		}
		log.debug("Configure database init ");
		factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
	}

	/**
	 * A global point of access for the singleton
	 *
	 * @return
	 */
	public static PgPersistenceConfig getInstance() {

		// Implementing double-locking to prevent ambiguity in multi-threaded
		// environment
		if (singleton == null) {
			synchronized (PgPersistenceConfig.class) {
				if (singleton == null) {
					singleton = new PgPersistenceConfig();
				}
			}
		}
		return singleton;
	}

	/**
	 * Ensuring that singleton contract is not violated by
	 * serialization/deserialization
	 *
	 * @return
	 */
	public Object readResolve() {
		return PgPersistenceConfig.getInstance();
	}

	/**
	 * Ideally we should not support cloning functionality as by definition a
	 * singleton provides only a single instance
	 *
	 * @return
	 * @throws CloneNotSupportedException
	 */
	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	/**
	 * @return the factory
	 */
	@Override
	public EntityManagerFactory getFactory() {
		return factory;
	}
}
