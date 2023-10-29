package com.projectRaj.BloggingPlatform.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Validated
@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,scope= Post.class,property="postId")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @NotBlank
    @Size(max = 255)
    private String title;

    @Enumerated(EnumType.STRING)
    private PostType postType;

    @NotBlank
    private String content;

    private LocalDateTime creationDateTime;

    private LocalDateTime lastModifiedDateTime;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private User author;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments;

}


