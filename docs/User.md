### 회원(User)
_Entity_
#### 속성
- `id`: `Long`
- `email`: 이메일 - Natural ID
- `nickname`: 닉네임
- `passwordHash`: 비밀번호 해시
- `status`: `MemberStatus` 회원 상태
- `point`: 회원 포인트 잔액
#### 행위
- `static register()`: 회원 등록: email, nickname, password, passwordEncoder
- `activate()`: 등록을 완료 시킨다.
- `deactivate()`: 탈퇴시킨다.
- `verifyPassword()`: 비밀번호를 검증한다.
- `changeNickname()`: 닉네임을 변경한다.
- `changePassword()`: 비밀번호를 변경한다.
#### 규칙
- 회원 생성후 상태는 등록 완료
- 등록 완료 상태에서는 탈퇴할 수 있다.

### 회원 상태(UserStatus)
_Enum_
#### 상수
- `ACTIVE`: 등록 완료
- `DEACTIVATED`: 탈퇴

### DuplicateEmailException
_Exception_

### 비밀번호 인코더(PasswordEncoder)
_Domain Service_
#### 행위
- `encode()`: 비밀번호 암호화하기
- `matches()`: 비밀번호가 일치하는지 확인

### Eamil
_Value Object_
#### 속성
- address: 이메일 주소