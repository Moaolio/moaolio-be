# 포트폴리오 종합관리 웹사이트 (Moaolio)

![img.png](img.png)
## :smile: 개발자소개 :smile:
|     | 박성근                            | 김민서                        |
|-----|--------------------------------|----------------------------|
| 깃허브 | https://github.com/p-seonggeun | https://github.com/minseid |
| 이메일 | minstandup@naver.com           |                            |
| 닉네임 | @p-seonggeun                   | @minseid                   |

## :sparkles: TechStack :sparkles:

![spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white) ![java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white) ![Mysql](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)

## :point_up: 개발파트 :point_up:
### 박성근
- [x] 유저관리부분 (회원가입, 로그인, 회원정보수정, 회원탈퇴)
- [x] 프로필부분 (프로필수정, 프로필확인)
- [x] 댓글부분 (댓글작성, 댓글수정, 댓글삭제)
- [x] 알림부분 (알림목록, 알림확인)
### 김민서
- [x] 게시글부분 (게시글작성, 게시글수정, 게시글삭제)
- [x] 좋아요부분 (좋아요작성, 좋아요삭제)
- [x] 이메일인증부분 (이메일인증, 이메일인증확인)
- [x] 검색부분 (검색기능)

## :bulb: 프로젝트 소개 :bulb:
- Moaolio는 모아서, 올려라 라는 뜻으로 포트폴리오를 모아서 올리는 사이트입니다.
- 개발자들이 자신의 포트폴리오를 모아서 올리고, 다른 개발자들의 포트폴리오를 보며 소통할 수 있습니다.
- 추가적으로 분야별 커뮤니티를 통해 다양한 정보를 얻을 수 있습니다.
- 노션,PPT,웹사이트 등 다양한 형태의 포트폴리오를 올릴 수 있습니다.
- url : http://moaolio.kro.kr:8081/
## :mag: 프로젝트 구조 :mag:
```
├─build
│  ├─classes
│  │  └─java
│  │      ├─main
│  │      │  └─com
│  │      │      └─example
│  │      │          └─side
│  │      │              ├─auth
│  │      │              │  ├─config
│  │      │              │  ├─dto
│  │      │              │  ├─handler
│  │      │              │  └─jwt
│  │      │              ├─banner
│  │      │              │  ├─controller
│  │      │              │  ├─entitiy
│  │      │              │  ├─repository
│  │      │              │  └─service
│  │      │              ├─comments
│  │      │              │  ├─controller
│  │      │              │  ├─dto
│  │      │              │  │  ├─request
│  │      │              │  │  └─response
│  │      │              │  ├─entity
│  │      │              │  ├─repository
│  │      │              │  └─service
│  │      │              ├─common
│  │      │              │  └─exception
│  │      │              ├─config
│  │      │              ├─Dto
│  │      │              ├─Exception
│  │      │              ├─notification
│  │      │              │  ├─controller
│  │      │              │  ├─dto
│  │      │              │  │  ├─request
│  │      │              │  │  └─response
│  │      │              │  ├─entity
│  │      │              │  ├─repository
│  │      │              │  └─service
│  │      │              ├─post
│  │      │              │  ├─controller
│  │      │              │  ├─dto
│  │      │              │  │  ├─request
│  │      │              │  │  └─response
│  │      │              │  ├─entity
│  │      │              │  ├─file
│  │      │              │  │  ├─controller
│  │      │              │  │  ├─Dto
│  │      │              │  │  │  ├─request
│  │      │              │  │  │  └─response
│  │      │              │  │  ├─entity
│  │      │              │  │  ├─repository
│  │      │              │  │  └─service
│  │      │              │  ├─like
│  │      │              │  │  ├─controller
│  │      │              │  │  ├─Dto
│  │      │              │  │  │  ├─request
│  │      │              │  │  │  └─response
│  │      │              │  │  ├─entity
│  │      │              │  │  ├─repository
│  │      │              │  │  └─service
│  │      │              │  ├─repository
│  │      │              │  ├─service
│  │      │              │  └─tag
│  │      │              │      └─entity
│  │      │              ├─techStack
│  │      │              │  ├─controller
│  │      │              │  ├─entity
│  │      │              │  ├─repository
│  │      │              │  └─service
│  │      │              └─user
│  │      │                  ├─controller
│  │      │                  ├─dto
│  │      │                  │  ├─request
│  │      │                  │  └─response
│  │      │                  ├─entity
│  │      │                  ├─repository
│  │      │                  └─service

```
### :calendar: Development period :calendar:
- 2024.05.03 ~ ing

