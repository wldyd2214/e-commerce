e-커머스 서비스

---

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

---

### 심화 과제
5️⃣ **`심화` 장바구니 기능**
- 사용자는 구매 이전에 관심 있는 상품들을 장바구니에 적재할 수 있습니다.
- 이 기능을 제공하기 위해 `장바구니에 상품 추가/삭제` API 와 `장바구니 조회` API 가 필요합니다.
- 위 두 기능을 제공하기 위해 어떤 요구사항의 비즈니스 로직을 설계해야할 지 고민해 봅니다.

---

## 설계 문서 Info
1. 마일스톤: https://github.com/users/wldyd2214/projects/1/views/1
2. 시퀀스 다이어그램: https://github.com/wldyd2214/e-commerce/tree/feature/step05/design/sequence
3. DB ERD 문서: https://github.com/wldyd2214/e-commerce/blob/feature/step06/design/db/DB_ERD.png
4. API 명세서: https://jiyong.gitbook.io/e/

---

## 동시성 문제
- [동시성 문제와 극복](https://jiyongpark-dev.tistory.com/113)

---

## Query 분석 및 캐싱 전략 설계
- [적은 부하로 트래픽 처리하기](https://jiyongpark-dev.tistory.com/116)

---

## 서비스 확장을 위한 트랜잭션 관리와 마이크로서비스 설계 방법
재미있는 사실 하나! 대규모 서비스에서는 작은 기능이 전체 시스템에 어떤 영향을 미칠 수 있는지 생각해본 적 있으신가요? 오늘은 그 중 하나로, 주문 생성 로직을 다루면서 서비스 규모 확장 시의 관심사 분리와 트랜잭션 처리 방법에 대해 이야기해볼까 합니다.

### 주문 생성 로직 이해하기
먼저, 기본적인 주문 생성 로직을 살펴볼게요. 가장 기본적인 예로, 사용자가 주문을 요청하면, 이를 서비스가 받아들여 처리한 뒤 데이터 플랫폼에 전송합니다. 다음은 그 예제 코드입니다.

```java
@Transactional
public Order createOrder(CreateOrderRequest request) {
    // 사용자 조회
    User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new UserNotFoundException("User not found"));

    // 재고 확인 및 차감
    Product product = productRepository.findById(request.getProductId())
            .orElseThrow(() -> new ProductNotFoundException("Product not found"));
    
    if (product.getStock() < request.getQuantity()) {
        throw new OutOfStockException("Insufficient stock");
    }
    product.decreaseStock(request.getQuantity());

    // 주문 생성 및 저장
    Order order = new Order(user, product, request.getQuantity());
    orderRepository.save(order);
    
    return order;
}
```

이 코드는 간단해 보일 수 있지만, 실질적으로 다양한 단계와 의존성이 얽혀 있습니다. 사용자를 확인하고, 재고를 차감하고, 주문을 생성한 뒤 데이터 플랫폼에 전송까지 하는 단일 책임 원칙(Single Responsibility Principle)을 위배하는 코드입니다.

### 이벤트 기반 설계로 관심사 분리
주문 생성 로직에 이벤트를 도입함으로써 관심사를 분리하고 더 관리하기 쉽게 만들 수 있습니다. 다음은 이벤트 개념을 활용한 코드 예제입니다:

먼저, `OrderEvent` 클래스를 정의합니다. 이 클래스는 주문이 생성될 때 필요한 데이터를 담습니다.

```java
@Getter
@AllArgsConstructor
public class OrderEvent {

    private Long userId;
    private Long orderId;
}
```

이제 주문 생성 로직에서 이벤트를 발행합니다:

```java
@Transactional
public Order createOrder(CreateOrderRequest request) {
    User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new UserNotFoundException("User not found"));

    Product product = productRepository.findById(request.getProductId())
            .orElseThrow(() -> new ProductNotFoundException("Product not found"));

    product.decreaseStock(request.getQuantity());

    Order order = new Order(user, product, request.getQuantity());
    orderRepository.save(order);

    // 이벤트 발행
    eventPublisher.publishEvent(new OrderEvent(user.getId(), order.getId()));

    return order;
}
```

이제 우리는 `OrderEventListener` 라는 이벤트 리스너를 통해 데이터 플랫폼 전송 로직을 분리할 수 있습니다.

```java
@RequiredArgsConstructor
@Component
@Slf4j
public class OrderEventListener {

    private final DataPlatformClient dataPlatformClient;

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void sendOrderData(OrderEvent orderEvent) {
        boolean dataResult = dataPlatformClient.sendOrderData(orderEvent.getUserId(), orderEvent.getOrderId());
        log.info("데이터 플랫폼 전송 결과 : {} ", dataResult);
    }
}
```

### 이해하고 넘어가기
이제 주문 생성 로직이 이벤트 발행과 이벤트 처리로 나뉘어 보다 깔끔하게 정리되었습니다. 핵심은 비동기적으로 이벤트를 처리하며, 트랜잭션이 성공적으로 커밋된 이후에만 데이터 플랫폼에 전송되도록 설계된 것입니다. 이렇게 하면 주문 생성 로직에서 데이터 플랫폼 전송 로직을 분리할 수 있어 각 로직의 유연성과 독립성이 증가합니다.

---

### 서비스 분리의 장점
- 각 서비스가 독립적으로 관리되고, 책임이 분리됩니다.
- 개별 서비스의 확장이 용이해집니다.
- 재사용성이 높아지고, 테스트가 쉬워집니다.

### 실서비스에서의 확장에서의 한계
- 서비스 분리로 인해 각 서비스의 트랜잭션이 독립적으로 처리되므로, 분산 트랜잭션 관리가 필요합니다.
- 네트워크 장애나 다른 이유로 서비스 간의 트랜잭션 일관성이 깨질 수 있습니다.

### 실서비스에서의 확장에서의 한계의 해결 방안
서비스가 확장되면서 각각의 로직을 독립적으로 관리하고 성능을 최적화하기 위해 마이크로서비스 아키텍처를 고려할 수 있습니다. 이때, 트랜잭션 관리에 한계가 생기는데요. 이는 분산 트랜잭션이나 Sage 패턴 등을 활용하여 해결할 수 있습니다.

1. **분산 트랜잭션:** 두 개 이상의 데이터 소스나 서비스가 함께 사용하는 트랜잭션으로, 트랜잭션 관리자(Coordinator)가 전체 트랜잭션을 관리합니다.
2. **Saga 패턴:** 긴 트랜잭션을 여러 개의 작은 트랜잭션으로 나누고, 각 단계를 로컬 트랜잭션으로 처리하며 실패 시 보상 작업을 수행해 일관성을 유지하는 패턴입니다.
3. **보상 트랜잭션:** 실패한 트랜잭션을 보상하는 방법으로, 각 서비스의 작업을 원래 상태로 되돌립니다.
4. **이벤트 기반 아키텍처:** 서비스 간의 통신을 이벤트 기반으로 처리하고, 각 서비스는 이벤트를 발행하고, 다른 서비스는 해당 이벤트를 구독하여 처리합니다.

### 서비스 확장 및 분리
서비스 분리
서비스의 규모가 확장됨에 따라, 각 기능을 별도의 서비스로 분리하여 유지보수성과 확장성을 높일 수 있습니다. 주문 생성 로직을 다음과 같은 서비스들로 분리할 수 있습니다.
- UserService: 사용자 조회 및 관련 로직 처리
- ProductService: 재고 확인 및 차감 로직 처리
- OrderService: 주문 생성 및 저장 로직 처리

### 주문 생성 Saga 패턴 적용시 필요한 이벤트 예시
아래의 이벤트들을 사용하여 주문 처리 밒 주문 실패시 보상 트랜잭션에 대한 기능을 수행한다.
- OrderCreatedEvent: 주문이 생성되었음을 알리는 이벤트
- InventoryDecreasedEvent: 재고가 성공적으로 차감되었음을 알리는 이벤트
- InventoryDecreaseFailedEvent: 재고 차감이 실패했음을 알리는 이벤트
- PointsDeductedEvent: 사용자 포인트가 성공적으로 차감되었음을 알리는 이벤트
- PointsDeductionFailedEvent: 사용자 포인트 차감이 실패했음을 알리는 이벤트
- OrderCompletedEvent: 주문이 성공적으로 완료되었음을 알리는 이벤트
- OrderFailedEvent: 주문이 실패했음을 알리는 이벤트

### 결론
서비스가 확장될수록 이를 잘 고려하여 아키텍처를 설계하는 것이 중요하고, 이러한 개념들을 도입하면 보다 안정적이고 유연한 시스템을 구현할 수 있다는걸 알게 되었고, 코드에서 이렇게 작은 변화로 큰 효과를 볼 수 있다는 점에서 정말 흥미로운 것 같다.

