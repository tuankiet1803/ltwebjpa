package ltwebjpa.entity;

import java.io.Serializable;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "videos")
@NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v")
public class Video implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "VideoId")
	private int videoId;
	@Column(name = "Active")
	private int active;
	@Column(name = "Videoname", columnDefinition = "VARCHAR(255)")
	private String videoname;
	@Column(name = "Description", columnDefinition = "VARCHAR(255)")
	private String description;
	@Column(name = "Poster")
	private String poster;
	@Column(name = "Title", columnDefinition = "VARCHAR(255)")
	private String title;
	@Column(name = "Views")
	private int views;
	@ManyToOne
	@JoinColumn(name = "CategoryId")
	private Category category;

}
