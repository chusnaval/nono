package nono.uses.channel;

import java.util.List;

import nono.entity.Channel;

public interface GetAllChannels {

	/**
	 * Get all channels from the data provider
	 *
	 * @return
	 */
	List<Channel> getAll();
}
