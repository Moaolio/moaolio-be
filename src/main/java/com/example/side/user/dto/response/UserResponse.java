package com.example.side.user.dto.response;

import com.example.side.user.entity.User;
import com.example.side.user.entity.UserRole;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"techMappings"})
public class UserResponse {

    private Long id;
    private String uid;
    private String email; // 이메일
    private String name; // 실명
    private String birth; // 생일
    private String nickname;
    private String contactInformation;
    private String description; // 자기소개
    private String experience;
    private String phone;
    private UserRole role;

    public UserResponse(User user) {
        this.id = user.getId();
        this.uid = user.getUid();
        this.email = user.getEmail();
        this.name = user.getName();
        this.birth = user.getBirth();
        this.nickname = user.getNickname();
        this.contactInformation = user.getContactInformation();
        this.description = user.getDescription();
        this.experience = user.getExperience();
        this.phone = user.getPhone();
        this.role = user.getRole();
    }
}
