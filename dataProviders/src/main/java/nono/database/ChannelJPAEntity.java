package nono.database;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nono.entity.Channel;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "rss_channels")
public class ChannelJPAEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "node_id")
	private int nodeId;

	private String description;

	private String rss;

	@Column(name = "class_code")
	private String classCode;

	private boolean leaf;

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "parent_id", nullable = false)
	private ChannelJPAEntity parentId;

	@Column(name = "user_id")
	private String userId;

	@OneToMany(mappedBy = "parentId")
	private Set<ChannelJPAEntity> rsschannels;

	/**
	 * Convert to domain entity
	 *
	 * @return
	 */
	public Channel convertToBean() {
		return new Channel(nodeId, description, rss, classCode, leaf,
				parentId == null ? null : parentId.convertToBean(), userId);
	}
}
