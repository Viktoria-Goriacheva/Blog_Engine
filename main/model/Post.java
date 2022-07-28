package main.model;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import main.dto.PostDTO;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@SqlResultSetMapping(
    name = "PostsMapping",
    classes = @ConstructorResult(
        targetClass = PostDTO.class,
        columns = {
            @ColumnResult(name = "id", type = Integer.class),
            @ColumnResult(name = "timestamp", type = Date.class),
            @ColumnResult(name = "userId", type = Integer.class),
            @ColumnResult(name = "name", type = String.class),
            @ColumnResult(name = "title", type = String.class),
            @ColumnResult(name = "announce", type = String.class),
//            @ColumnResult(name = "likeCount", type = Integer.class),
//            @ColumnResult(name = "dislikeCount", type = Integer.class),
            @ColumnResult(name = "commentCount", type = Integer.class),
            @ColumnResult(name = "viewCount", type = Integer.class)
        }
    )
)
@Table(name = "posts")
public class Post {

  @Id
  @NotNull
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  @NotNull
  @Column(name = "is_active", nullable = false)
  private byte isActive;
  @Column(name = "moderation_status", nullable = false)
  @Enumerated(EnumType.STRING)
  private ModerationStatus moderationStatus;
  @Column(name = "moderator_id")
  private int moderatorId;
  @NotNull
  private Date time;
  @NotNull
  private String title;
  @NotNull
  private String text;
  @Column(name = "view_count", nullable = false)
  private int viewCount;
  @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
  @JoinColumn(name = "user_id")
  private User user;
  @OneToMany(mappedBy = "post", cascade = CascadeType.PERSIST)
  private List<PostVote> postVotes;
  @ManyToMany
  @JoinTable(name = "tag2post",
      joinColumns = {@JoinColumn(name = "post_id")},
      inverseJoinColumns = {@JoinColumn(name = "tag_id")})
  private List<Tag> tags;
  @OneToMany(mappedBy = "post")
  private List<PostComment> comments;

}
