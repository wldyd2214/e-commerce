### 상품(Product)
_Entity_
#### 속성
- `id`: `Long`
- `name`: 상품명
- `price`: 상품 금액
- `status`: `ProductStatus` 상품 상태
- `categoryId`: 카테고리 식별자 아이디

### 상품 상태(ProductStatus)
_Enum_
#### 상수
- `PENDING`: 등록 대기
- `ACTIVE`: 등록 완료
- `INACTIVE`: 비활성