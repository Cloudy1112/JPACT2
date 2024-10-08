package vn.iotstar.entity;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "Videos")
@NamedQuery(name = "Video.findAll", query = "SELECT v FROM Video v")
public class Video {
	@Id
	@Column(name = "VideoId")
	private String videoId;
	@Column(name = "Active")
	private int active;
	@Column(name = "Description", columnDefinition = "nvarchar(50) null")
	private String description;
	@Column(name = "Poster", columnDefinition = "nvarchar(50) null")
	private String poster;
	@Column(name = "Title", columnDefinition = "nvarchar(50) null")
	private String title;
	@Column(name = "Views")
	private int views;

	//bi-directional many-to-one association to Category


	 @ManyToOne
	 @JoinColumn(name="CategoryId")
	 private Category category;


	
}
