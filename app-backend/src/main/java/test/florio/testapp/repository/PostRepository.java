package test.florio.testapp.repository;

import test.florio.testapp.model.domain.Post;
import test.florio.testapp.model.domain.User;
import test.florio.testapp.model.dto.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findByUserOrderByCreatedDateDesc(User user, Pageable pageable);

    Page<Post> findAllByOrderByCreatedDateDesc(Pageable pageable);

    Optional<Post> findById(Long id);

    void delete(Post post);
}