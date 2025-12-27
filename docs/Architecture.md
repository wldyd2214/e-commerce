# API-Server 개발 가이드

## 아키텍처
- 클린 + 레이어드 아키텍처
- 도메인 모델 패턴

## 계층
- Presentation Layer (표현): 사용자의 요청을 처리하고 사용자에게 정보를 보여주는 계층
  - 각 서비스별 controller 패키지가 이에 해당한다.
- Application Layer (응용): 사용자가 요청한 기능을 실행하는 계층으로 업무 로직을 직접 구현하지 않으며 도메인 계층을 조합해서 기능을 실행하는 계층
  - 각 서비스별 application 패키지가 이에 해당한다.
- Domain Layer (도메인): 시스템이 제공할 도메인 규칙이 구현되는 계층
  - 각 서비스별 service, domain 패키지가 이에 해당한다.
- Infrastructure Layer (인프라스트럭처): 데이터베이스나 메시징 시스템과 같은 외부 시스템과의 연동을 처리하는 계층
  - 각 서비스별 persistence, infrastructure 패키지가 이에 해당한다. 

> Presentation -> Application -> Domain <- Infrastructure
- 위의 예시 처럼 의존성의 방향(화살표) 저수준 모듈(Presentation, Application, Infrastructure)에서 고수준 모듈(Domain) 향하도록 개발한다.

## 패키지
- 서비스 시스템 (content, member, mobilegiftcard, order, payment, product)
  - controller
  - application
  - service, domain
  - persistence
- 외부 지원 시스템 (support)
