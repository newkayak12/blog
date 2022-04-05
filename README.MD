# BLOG

## API LIST

### User ("/api/user")

| info   | url                      | method | parameter                                                         | return            |
|--------|--------------------------|--------|-------------------------------------------------------------------|-------------------|
| 회원 가입  | /api/user/signUp         | POST   | String userId, String userPassword, String userNickName           | ResponseContainer |
| 로그인    | /api/user/signIn         | GET    | String userId, String userPassword                                | ResponseContainer |
| 로그아웃   | /api/user/signOut        | GET    |                                                                   |                   |
| 비밀번호 변경 | /api/user/changePassword | PATCH  | HEADER_AUTHORIZATION, String userPassword, String newUserPassword | ResponseContainer |
| 닉네임 변경 | /api/user/chageNickname  | PATCH  | HEADER_AUTHORIZATION, String userNickname                         | ResponseContainer |


### Board ("/api/board")
| info         | url                    | method | parameter                                                                     | return            |
|--------------|------------------------|--------|-------------------------------------------------------------------------------|-------------------|
| 게시글 리스트 가져오기 | /api/borad/fetchList   | GET    | HEADER_AUTHORIZATION, Integer page, Integer limit, String searchText          | ResponseContainer |
| 게시글 상세 조회    | /api/board/fetchOne    | GET    | HEADER_AUTHORIZATION, Integer boardNo                                         | ResponseContainer |
| 게시글 작성       | /api/board/writeBoard  | POST   | HEADER_AUTHORIZATION, String boardTitle, String boardContent                  | ResponseContainer |
| 게시글 수정       | /api/board/modifyBoard | PATCH  | HEADER_AUTHORIZATION, Integer boardNo, String boardTitle, String boardContent | ResponseContainer |
| 게시글 삭제       | /api/board/deleteBoard | DELETE | HEADER_AUTHORIZATION, Integer boardNo                                         | ResponseContainer |

### Jandi ("/api/jandi/fetchList)
| info    | url                  | method | parameter                            | return            |
|---------|----------------------|--------|--------------------------------------|-------------------|
| 잔디 가져오기 | /api/jandi/fetchList | GET    | HEADER_AUTHORIZATION, Integer gap    | ResponseContainer |

