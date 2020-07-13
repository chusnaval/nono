package nono.configuration.providers;

import com.blade.ioc.annotation.Bean;

import nono.configuration.Factory;
import nono.database.ChannelsDatabaseProvider;
import nono.uses.channel.GetAllChannels;

@Factory
public class ProvidersFactory {

	@Bean("GetAllChannels")
	public GetAllChannels getAllChannels() {
		return new ChannelsDatabaseProvider();
	}
}
