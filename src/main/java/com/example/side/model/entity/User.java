package com.example.side.model.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
@ToString(exclude = {})
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
@Getter
@Setter
@EqualsAndHashCode

public class User {

    @Id // 엔티티의 주키(primary key)를 나타냅니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 생성되는 값의 전략을 지정합니다. (예: auto_increment)
    private Long id;
    private String nickname;
    private String email;
    private String password;
    private String phone;
    private String age;
    private String username;
    private Long career;
    private String state;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Community> communities;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Notification> notifications;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Job> jobs;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserPost> userPosts;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserScrap> userScraps;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<UserPostComments> userPostComments;

    public void ifPresent(Object o) {
    }
}
