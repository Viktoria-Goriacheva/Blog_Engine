package main.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.sql.Timestamp;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jsoup.Jsoup;
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "timestamp",
    "user",
    "title",
    "announce",
    "likeCount",
    "dislikeCount",
    "commentCount",
    "viewCount"
})
public class PostDTO {

  public Integer id;
  public Long timestamp;
  public UserDTOForPost user;
  public String title;
  public String announce;
//  public Integer likeCount;
//  public Integer dislikeCount;
  public Integer commentCount;
  public Integer viewCount;

  public PostDTO() {
  }

  public PostDTO(Integer id, Date timestamp, Integer userId, String name, String title, String announce,  Integer commentCount, Integer viewCount) {
    this.id = id;
    this.timestamp = timestamp.getTime() / 1000;
    user = new UserDTOForPost(userId,name);
    this.title = title;
    this.announce = announce;
//    this.likeCount = likeCount;
//    this.dislikeCount = dislikeCount;
    this.commentCount = commentCount;
    this.viewCount = viewCount;
  }
  public void editAnnounce(String text){
    text = Jsoup.parse(text).text();
    int TEXT_LIMIT = 149;
    text = text.length() > TEXT_LIMIT ? text.substring(0, TEXT_LIMIT) : text;
    announce = text + "...";
  }

  public void convertTimeToTimestamp(Date time){
    timestamp = Timestamp.valueOf(String.valueOf(time)).getTime() / 1000;
  }

}

