package com.example.side.user.entity;

import com.example.side.comments.entity.Comments;
import com.example.side.common.BaseEntity;
import com.example.side.post.entity.Post;
import com.example.side.techStack.entity.TechStack;
import com.example.side.user.dto.request.UserDto;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(exclude = {"posts", "comments"})
@Table(name = "user")
@EqualsAndHashCode(of = {"username", "password", "role"}, callSuper = false)
public class User extends BaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * username -> 리소스 서버에서 발급 받은 정보로 사용자를 특정할 아이디
     * ex) google_2398472981
     * 자체 회원가입시 id
     */
    @Column(unique = true, nullable = false)
    private String username;
    @Setter
    private String password;

    @Column(unique = true)
    private String email; // 이메일
    private String name; // 실명

    private String birth; // 생일

    /**
     * OAuth2에서 제공받는 이름(실명) -> 추후에 변경
     * OAuth2에서 제공받는 이름 -> 추후에 변경
     */

    @Column(unique = true)
    private String nickname;
    private String description; // 자기소개
    private String job; // 직업


    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Comments> comments = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @Setter
    private boolean enabled;

    @Setter
    private LocalDateTime verificationTokenExpiry;

    @Setter
    private String verificationToken;

    @Builder
    public User(String username, String password, String email, String name, String birth, String nickname, String description, String job, UserRole role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.name = name;
        this.birth = birth;
        this.nickname = nickname;
        this.description = description;
        this.job = job;
        this.role = role;
    }

    public User(UserDto userDto) {
        this.username = userDto.getUsername();
        this.role = UserRole.valueOf(userDto.getRole());
    }

    /**
     * OAuth2 로그인 시 유저 이메일이나 이름이 바뀜을 업데이트
     */
    public void updateUserInfo(String email, String nickname) {
        this.email = email;
        this.nickname = nickname;
    }

}
