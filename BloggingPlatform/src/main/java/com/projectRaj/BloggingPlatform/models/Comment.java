package com.projectRaj.BloggingPlatform.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,scope=Comment.class,property="commentId")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer commentId;

    @Column(nullable = false)
    private String comment;

    private LocalDateTime commentCreationTimeStamp;

    private LocalDateTime commentModifiedTimeStamp;


    @ManyToOne
    @JoinColumn(name = "fk_post_id")
    private Post post;

    @ManyToOne
    @JoinColumn(name = "fk_commenter_id")
    private User commenter;
}
