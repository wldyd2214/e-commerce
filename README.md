# e-커머스 서비스

---

## 1. 기술 스택 (Tech Stack)
* Language: Java (e.g., Java 17)
* Framework: Spring Boot (3.5.0 version)
* Data Access: MyBatis, Spring Data JPA
* Build Tool: Gradle
* Deployment: Docker
* Database: MySQL

## 2. 코드 컨벤션 (Coding Convention)
* 기본 원칙: Google Java Style Guide (https://google.github.io/styleguide/javaguide.html)를 따릅니다.
* 자동 포맷팅: IDE의 Reformat Code 기능을 사용하여 항상 스타일 가이드를 준수하도록 합니다.
   - 자동 포맷팅 적용 참고 사이트: https://systemdata.tistory.com/109
   - Tab szie: 4 / Indent: 4
* IntelliJ IDEA에서 저장 시 액션(Actions on Save) 설정을 활성화해 주시기 바랍니다.
   - 설정 방법
     아래 절차에 따라 설정을 활성화해 주세요. (약 1분 소요)
      1. 설정(Settings/Preferences) 창 열기
          * Windows/Linux: File > Settings (단축키: Ctrl + Alt + S)
          * macOS: IntelliJ IDEA > Settings... (단축키: Cmd + ,)
   
      2. Actions on Save 메뉴로 이동
          * 왼쪽 메뉴에서 Tools > Actions on Save를 선택합니다.
   
      3. 주요 액션 활성화
          * 아래 두 항목의 체크박스를 반드시 활성화해주세요.
              * ✅ Reformat code (코드 재정렬) 활성화
                - All file types 선택 > XML, XML Document Type Definition 체크 비활성화 (MyBatis xml 코드가 깨지는 현상으로 인해)
              * ✅ Optimize imports (임포트 최적화) 활성화
           
## 3. 아키텍처 (Architecture)
- ### 클린 + 레이어드 아키텍처

## Domain
- ####  [도메인 모델](docs/Domain.md)

## Description
- `e-커머스 상품 주문 서비스`를 구현해 봅니다.
- 상품 주문에 필요한 메뉴 정보들을 구성하고 조회가 가능해야 합니다.
- 사용자는 상품을 여러개 선택해 주문할 수 있고, 미리 충전한 잔액을 이용합니다.
- 상품 주문 내역을 통해 판매량이 가장 높은 상품을 추천합니다.

## Requirements
- 아래 4가지 API 를 구현합니다.
    - 잔액 충전 / 조회 API
    - 상품 조회 API
    - 주문 / 결제 API
    - 인기 판매 상품 조회 API
- 각 기능 및 제약사항에 대해 단위 테스트를 반드시 하나 이상 작성하도록 합니다.
- 다수의 인스턴스로 어플리케이션이 동작하더라도 기능에 문제가 없도록 작성하도록 합니다.
- 동시성 이슈를 고려하여 구현합니다.
- 재고 관리에 문제 없도록 구현합니다.

## API Specs
### 기본과제
1️⃣ **`주요`** **잔액 충전 / 조회 API**
- 결제에 사용될 금액을 충전하는 API 를 작성합니다.
- 사용자 식별자 및 충전할 금액을 받아 잔액을 충전합니다.
- 사용자 식별자를 통해 해당 사용자의 잔액을 조회합니다.

2️⃣ **`기본` 상품 조회 API**
- 상품 정보 ( ID, 이름, 가격, 잔여수량 ) 을 조회하는 API 를 작성합니다.
- 조회시점의 상품별 잔여수량이 정확하면 좋습니다.

3️⃣ **`주요`** **주문 / 결제 API**
- 사용자 식별자와 (상품 ID, 수량) 목록을 입력받아 주문하고 결제를 수행하는 API 를 작성합니다.
- 결제는 기 충전된 잔액을 기반으로 수행하며 성공할 시 잔액을 차감해야 합니다.
- 데이터 분석을 위해 결제 성공 시에 실시간으로 주문 정보를 데이터 플랫폼에 전송해야 합니다. ( 데이터 플랫폼이 어플리케이션 `외부` 라는 가정만 지켜 작업해 주시면 됩니다 )
- 데이터 플랫폼으로의 전송 기능은 Mock API, Fake Module 등 다양한 방법으로 접근해 봅니다.

4️⃣ **`기본` 상위 상품 조회 API**
- 최근 3일간 가장 많이 팔린 상위 5개 상품 정보를 제공하는 API 를 작성합니다.
- 통계 정보를 다루기 위한 기술적 고민을 충분히 해보도록 합니다.

### 심화 과제
5️⃣ **`심화` 장바구니 기능**
- 사용자는 구매 이전에 관심 있는 상품들을 장바구니에 적재할 수 있습니다.
- 이 기능을 제공하기 위해 `장바구니에 상품 추가/삭제` API 와 `장바구니 조회` API 가 필요합니다.
- 위 두 기능을 제공하기 위해 어떤 요구사항의 비즈니스 로직을 설계해야할 지 고민해 봅니다.

## 설계 문서
1. 마일스톤: https://github.com/users/wldyd2214/projects/1/views/1
2. DB ERD 문서: https://github.com/wldyd2214/e-commerce/blob/feature/step06/design/db/DB_ERD.png
3. API 명세 (swagger) : http://localhost:{port}/commerce/swagger.html

## 동시성 문제
- [동시성 문제와 극복](https://jiyongpark-dev.tistory.com/113)

## Query 분석 및 캐싱 전략 설계
- [적은 부하로 트래픽 처리하기](https://jiyongpark-dev.tistory.com/116)
