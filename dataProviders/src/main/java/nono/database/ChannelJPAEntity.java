package nono.database;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import nono.entity.Channel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity(name = "RssChannels")
public class ChannelJPAEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int nodeId;

	private String description;

	private String rss;

	private String classCode;

	private boolean leaf;

	private int parentId;

	private String userId;

	/**
	 * Convert to domain entity
	 * 
	 * @return
	 */
	public Channel convertToBean() {
		return new Channel(nodeId, description, rss, classCode, leaf, parentId, userId);
	}
}
