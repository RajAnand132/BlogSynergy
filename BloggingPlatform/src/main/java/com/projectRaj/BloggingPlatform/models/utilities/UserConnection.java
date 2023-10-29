package com.projectRaj.BloggingPlatform.models.utilities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.projectRaj.BloggingPlatform.models.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@JsonIdentityInfo(generator= ObjectIdGenerators.PropertyGenerator.class,scope= UserConnection.class,property="UserConnectionId")
public class UserConnection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long UserConnectionId;

    @ManyToOne
    private User follower;

    @ManyToOne
    private User following;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

}

