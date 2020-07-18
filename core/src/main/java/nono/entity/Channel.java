package nono.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Channel {

	private int nodeId;

	private String description;

	private String rss;

	private String classCode;

	private boolean leaf;

	private Channel parentId;

	private String userId;

}
