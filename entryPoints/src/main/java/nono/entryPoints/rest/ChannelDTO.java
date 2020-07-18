package nono.entryPoints.rest;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChannelDTO {

	private int nodeId;

	private String description;

	private String rss;

	private String classCode;

	private boolean leaf;

	private ChannelDTO parentId;

}
