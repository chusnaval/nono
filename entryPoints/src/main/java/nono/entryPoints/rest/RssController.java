package nono.entryPoints.rest;

import java.util.List;
import java.util.stream.Collectors;

import com.blade.ioc.annotation.Inject;
import com.blade.mvc.annotation.GetRoute;
import com.blade.mvc.annotation.Path;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nono.entity.Channel;
import nono.uses.channel.GetAllChannelsUseCase;

@Slf4j
@AllArgsConstructor
@Path
public class RssController {

	public RssController() {
	}

	@Inject("GetAllChannelsUseCase")
	private GetAllChannelsUseCase useCase;

	@GetRoute("/rss/channel/")
	public List<ChannelDTO> listAllChannels() {
		log.info("List all channels.");
		List<Channel> channels = useCase.execute();

		return channels.stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	/**
	 * Convert to DTO, no userId for security
	 *
	 * @param channel
	 * @return
	 */
	public ChannelDTO convertToDTO(Channel channel) {
		return new ChannelDTO(channel.getNodeId(), channel.getDescription(), channel.getRss(), channel.getClassCode(),
				channel.isLeaf(), channel.getParentId());
	}
}
