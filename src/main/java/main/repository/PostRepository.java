package main.repository;

import java.util.List;
import main.model.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Integer> {

  @Query(value = "SELECT COUNT(*) FROM posts where is_active = 1 and moderation_status = 'ACCEPTED' and time <= Now () ", nativeQuery = true)
  Integer getPostsCount();
}
