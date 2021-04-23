package test.florio.testapp.service;

import test.florio.testapp.common.Exception.BadRequestException;
import test.florio.testapp.model.domain.Comment;
import test.florio.testapp.model.domain.Post;
import test.florio.testapp.model.domain.User;
import test.florio.testapp.model.dto.CommentDto;
import test.florio.testapp.model.dto.PostDto;
import test.florio.testapp.repository.CommentRepository;
import test.florio.testapp.repository.PostRepository;
import test.florio.testapp.security.SecurityUtil;
import test.florio.testapp.security.service.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    public Optional<Comment> findForId(Long id) {
        return commentRepository.findById(id);
    }

    public Optional<Post> findPostForId(Long id) {
        return postRepository.findById(id);
    }

    public Optional<List<Comment>> findCommentsByPostId(Long id) {
        return commentRepository.findByPostId(id);
    }

    public CommentDto registerComment(CommentDto commentDto, CustomUserDetails customUserDetails) {
        Optional<Post> postForId = this.findPostForId(commentDto.getPostId());
        if (postForId.isPresent()) {
            Comment newComment = new Comment();
            newComment.setBody(commentDto.getBody());
            newComment.setPost(postForId.get());
            newComment.setUser(new User(customUserDetails.getId(), customUserDetails.getName()));
            return new CommentDto(commentRepository.saveAndFlush(newComment));
        } else {
            throw new BadRequestException("Not exist post.");
        }
    }

    public Optional<CommentDto> editPost(CommentDto editCommentDto) {
        return this.findForId(editCommentDto.getId())
                .map(comment -> {
                    comment.setBody(editCommentDto.getBody());
                    return comment;
                })
                .map(CommentDto::new);
    }

    public void deletePost(Long id) {
        commentRepository.findById(id).ifPresent(comment -> {
            commentRepository.delete(comment);
        });
    }
}
