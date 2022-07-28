package main.controller;

import java.util.List;
import lombok.RequiredArgsConstructor;
import main.api.response.PostResponse;
import main.dto.PostDTO;
import main.service.PostMode;
import main.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiPostController {

  private final PostService postService;

  @GetMapping("/post")
  public ResponseEntity<PostResponse> post(
      @RequestParam(required = false, defaultValue = "recent") PostMode mode,
      @RequestParam(required = false, defaultValue = "0") int offset,
      @RequestParam(required = false, defaultValue = "10") int limit) {

    List<PostDTO> posts = postService.getPosts(offset,limit,mode);

    posts.forEach(postDTO -> postDTO.editAnnounce(postDTO.getAnnounce()));

    PostResponse postResponse = new PostResponse(postService.getAllPostsCount(), posts);
    return new ResponseEntity<>(postResponse, HttpStatus.OK);
  }
}

