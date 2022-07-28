package main.service;

import java.util.List;
import main.repository.DAO.PostDAO;
import main.repository.PostRepository;
import org.springframework.stereotype.Service;

@Service
public class PostService {
  private final PostRepository postRepository;
  private final PostDAO postDAO;
  public PostService(PostRepository postRepository, PostDAO postDAO) {
    this.postRepository = postRepository;
    this.postDAO = postDAO;
  }

  public List getPosts(int offset, int limit, PostMode mode) {
    return postDAO.getPostsBySort(offset, limit, mode);
  }

  public Integer getAllPostsCount() {
    return postRepository.getPostsCount();
  }
}
