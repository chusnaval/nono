package nono.database;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import com.blade.ioc.annotation.Inject;

import lombok.extern.slf4j.Slf4j;
import nono.entity.Channel;
import nono.uses.channel.GetAllChannels;

/**
 * @author chusnaval
 *
 */
@Slf4j
public class ChannelsDatabaseProvider implements GetAllChannels {

	public ChannelsDatabaseProvider() {

	}

	@Inject("PersistenceConfig")
	private PersistenceConfig persistenceConfig;

	@SuppressWarnings("unchecked")
	@Override
	public List<Channel> getAll() {
		log.debug("creating entity manager for get all channels ");
		EntityManager em = persistenceConfig.getFactory().createEntityManager();
		List<Channel> result = new ArrayList<>();
		try {
			Query query = em.createQuery("SELECT c FROM " + ChannelJPAEntity.class + " c");
			List<ChannelJPAEntity> list = query.getResultList();
			result = list.stream().map(ChannelJPAEntity::convertToBean).collect(Collectors.toList());
		} catch (Exception e) {
			log.error("Error getting channels list: ", e);
		} finally {
			em.close();
		}
		return result;
	}

	/**
	 * @param persistenceConfig the persistenceConfig to set
	 */
	public void setPersistenceConfig(PersistenceConfig persistenceConfig) {
		this.persistenceConfig = persistenceConfig;
	}

}
