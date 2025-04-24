package com.example.mdd.services.implementations;

import com.example.mdd.dto.responses.CommentDTO;
import com.example.mdd.exceptions.NotFoundException;
import com.example.mdd.mappers.CommentMapper;
import com.example.mdd.models.ArticleEntity;
import com.example.mdd.models.CommentEntity;
import com.example.mdd.models.UserEntity;
import com.example.mdd.repositories.ArticleRepository;
import com.example.mdd.repositories.CommentRepository;
import com.example.mdd.repositories.UserRepository;
import com.example.mdd.services.interfaces.CommentInterface;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService implements CommentInterface {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final CommentMapper commentMapper;

    public CommentService(ArticleRepository articleRepository, UserRepository userRepository, CommentRepository commentRepository, CommentMapper commentMapper) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
    }

    @Override
    public CommentDTO addComment(String message, Integer articleId, Integer userId) throws NotFoundException {
        ArticleEntity article = articleRepository.findById(articleId)
                .orElseThrow(() -> new NotFoundException("Article not found!"));
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found!"));

        CommentEntity comment = new CommentEntity();
        comment.setCommentaire(message);
        comment.setUser(user);
        comment.setArticle(article);
        comment.setCreatedAt(LocalDateTime.now());

        CommentEntity savedComment = commentRepository.save(comment);

        return commentMapper.toDTO(savedComment);
    }

    @Override
    public void deleteById(Integer id){
        commentRepository.deleteById(id);
    }

    @Override
   public List<CommentDTO> getCommentsByArticle(Integer articleId) {
        return commentRepository.findAll().stream()
                .filter(comment -> comment.getArticle().getId().equals(articleId))
                .map(commentMapper::toDTO)
                .collect(Collectors.toList());
    }

}
