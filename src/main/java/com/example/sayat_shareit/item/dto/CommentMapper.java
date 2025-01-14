package com.example.sayat_shareit.item.dto;

import com.example.sayat_shareit.item.Comment;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public CommentResponseDto toResponse(Comment comment) {
        CommentResponseDto commentResponseDto = new CommentResponseDto();
        commentResponseDto.setId(comment.getId());
        commentResponseDto.setText(comment.getText());
        commentResponseDto.setCreated(comment.getCreated());
        commentResponseDto.setAuthorName(comment.getAuthor().getName());
        return commentResponseDto;
    }

    public Comment fromCreate(CommentCreateDto commentCreate) {
        Comment comment = new Comment();
        comment.setText(commentCreate.getText());
        return comment;
    }
}
