package nono.uses.channel;

import java.util.List;

import lombok.AllArgsConstructor;
import nono.entity.Channel;
import nono.uses.UseCase;

@AllArgsConstructor
@UseCase("GetAllChannelsUseCase")
public class GetAllChannelsUseCase implements CoreUseCase {

	/**
	 * Default constructor
	 */
	public GetAllChannelsUseCase() {
	}

	private GetAllChannels getAllChannels;

	@Override
	public List<Channel> execute() {

		return getAllChannels.getAll();
	}

	/**
	 * @param getAllChannels the getAllChannels to set
	 */
	public void setGetAllChannels(GetAllChannels getAllChannels) {
		this.getAllChannels = getAllChannels;
	}

}
