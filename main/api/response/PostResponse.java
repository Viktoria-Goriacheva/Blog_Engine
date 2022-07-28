package main.api.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import main.dto.PostDTO;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "count",
    "posts"
})
public class PostResponse {

  public Integer count;
  @JsonProperty("posts")
  public List<PostDTO> posts;
  public PostResponse() {
  }

  public PostResponse(Integer count, List<PostDTO> posts) {
    this.count = count;
    this.posts = posts;
  }
}
