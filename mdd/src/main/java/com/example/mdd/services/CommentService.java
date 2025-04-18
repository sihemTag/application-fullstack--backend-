package com.example.mdd.services;

import com.example.mdd.dto.CommentDTO;
import com.example.mdd.exceptions.NotFoundException;
import com.example.mdd.models.ArticleEntity;
import com.example.mdd.models.CommentEntity;
import com.example.mdd.models.UserEntity;
import com.example.mdd.repositories.ArticleRepository;
import com.example.mdd.repositories.CommentRepository;
import com.example.mdd.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    public CommentService(ArticleRepository articleRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    public void addComment(String message, Integer articllId, Integer userId) throws NotFoundException {
        ArticleEntity article= articleRepository.findById(articllId).orElseThrow(()->new NotFoundException("Artical not found!"));
        UserEntity user = userRepository.findById(userId).orElseThrow(()->new NotFoundException("User not found!"));
        CommentEntity comment = new CommentEntity();
        comment.setCommentaire(message);
        comment.setUser(user);
        comment.setArticle(article);
        comment.setCreatedAt(LocalDateTime.now());

        commentRepository.save(comment);
    }

    public void deleteById(Integer id){
        commentRepository.deleteById(id);
    }

    public List<CommentEntity> getCommentsByArticle(Integer articleId){
        List<CommentEntity> comments = commentRepository.findAll();
        List<CommentEntity> results = new ArrayList<>();
        for(CommentEntity comment : comments){
            if(comment.getArticle().getId()==articleId){
                results.add(comment);
            }
        }
        return results;
    }

}
