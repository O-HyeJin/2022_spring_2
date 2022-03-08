# Rest API 학습

**과제목표**: Rest API가 무엇인지 학습하고 Github에 업로드   
#### 세부 목표
1. HTTP 통신에 관하여
2. 브라우저에 URL을 입력 후 요청하여 서버에서 응답하는 과정까지에 대해 학습한 내용을 작성

## Rest API   
REST 아키텍처의 제약 조건을 준수하는 애플리케이션 프로그래밍 인터페이스(API)를 뜻한다.   

#### API: Application Programming Interface
* 정보 제공자와 정보 사용자 간의 계약
* 소비자에게 필요한 콘텐츠(호출)와 생산자에게 필요한 콘텐츠(응답)를 구성
   * ex) 날씨 서비스용 API에서 *사용자*는 우편번호 제공, *생산자*는 최고 기온 및 최저 기온으로 구성된 응답으로 답하도록 지정할 수 있다.   


#### Rest: Representational State Transfer   
* 아키텍처 원칙 세트이다.   
* RESTful API를 통해 요청이 수행될 때, RESTful API는 리소스 상태에 대한 표현을 요청자에게 전송한다.   
* 이 정보 또는 표현은 **HTTP: JSON(Javascript Object Notation), HTML, XMT, 일반 텍스트**를 통해 몇 가지 형식으로 전송된다.
  * *``JSON``은 사용 언어와 상관이 없고, 인간과 머신이 모두 읽을 수 있기 때문에 가장 널리 사용된다.*   
   
   
   
## REST 디자인 원칙(제약 조건)
### 1. 균일한 인터페이스  
&nbsp;  동일한 리소스에 대한 모든 API 요청은 동일하게 보여야 한다. 동일한 데이터 조각이 오직 하나의 URI(Uniform Resource Identifier)에 속함을 보장해야 한다. 리소스에는 클라이언트가 필요로 하는 모든 정보를 포함해야 한다.   
  
### 2. 클라이언트-서버 디커플링   
&nbsp;  클라이언트와 서버 애플리케이션은 서로 간에 완전히 독립적이어야 한다. 클라이언트 애플리케이션이 알아야 하는 유일한 정보는 요청된 리소스의 URI이며, 이는 다른 방법으로 서버 애플리케이션과 상호작용할 수 없다. 서버 애플리케이션은 HTTP를 통해 요청된 데이터를 전달하는 것 말고는 클라이언트 애플리케이션을 수정하지 않아야 한다.   
&nbsp;&nbsp;&nbsp; ▶ 클라이언트와 서버에서 개발해야 할 내용이 명확해지고 서로간 의존성이 감소한다.
  
### 3. Stateless   
&nbsp;  각 요청에서 처리에 필요한 모든 정보를 포함해야 한다. 즉, 서버측 세션을 필요로 하지 않는다. 서버 애플리케이션은 클라이언트 요청과 관련된 데이터를 저장할 수 없다.   
&nbsp;&nbsp;&nbsp; ▶ 세션 정보나 쿠키 정보르르 별도로 저장하고 관리하지 않기 때문에 API 서버는 들어오는 요청을 단순히 처리하면 된다.
  
### 4. 캐싱 가능성   
&nbsp;  리소스를 클라이언트 또는 서버측에서 캐싱할 수 있어야 한다. 서버 응답에는 전달된 리소스에 대해 캐싱이 허용되는지 여부 또한 포함되어야 한다. 이는 서버측의 확장성 증가, 클라이언트측의 성능 향상을 위함이다.   
  
### 5. 계층 구조 아키텍처   
&nbsp;  호출과 응답이 서로 다른 계층을 통과한다. 엔드 애플리케이션 또는 중개자와 통신하는지 여부를 클라이언트나 서버가 알 수 없도록 설계되어야 한다.   
&nbsp;&nbsp;&nbsp; ▶ 보안, 로드 밸런싱, 암호화 계층을 추가해 구조상의 유연성을 둘 수 있다.   
&nbsp;&nbsp;&nbsp; ▶ PROXY, 게이트웨이 같은 네트워크 기반의 중간매체를 사용할 수 있다.
  
### 6. 코디 온디맨드(옵션)   
&nbsp;  일반적으로 정적 리소스를 전송하지만, 특정한 경우에는 응답에 실행 코드(예: Java 애플릿)를 포함할 수 있다. 이런 경우, 코드는 요청 시에만 실행되어야 한다.   
<br>

- - -

<br>

## HTTP 통신
> HTTP 통신과 Rest API의 작동 방식의 비교   


### HTTP: Hyper Text Transfer Protocol   
인터넷에서 데이터를 주고받는 프로토콜.   
LINK: [HTTP 개념 잡기](https://velog.io/@doomchit_3/Internet-HTTP-%EA%B0%9C%EB%85%90%EC%B0%A8%EB%A0%B7-IMBETPY)

* TCP 위에서 동작하지만 무상태성, 비연결성의 특징을 가지고 있다. >> 쿠키나 세션을 이용하여 클라이언트의 상태 관리
* HTTP의 통신과정   
<img width="800px" src="https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FRhbUB%2Fbtq0iBHCBTX%2FfKL4uGrKHaz7w5rWr9Ottk%2Fimg.png"></img>   
  - HTTP 연결의 시작과 끝은 TCP 연결과 해제와 같이 이루어진다.

### REST API의 작동 방식
* HTTP 요청을 통해 통신하여 리소스 내에서 레코드의 작성, 읽기, 업데이트, 삭제(CRUD) 등의 표준 데이터베이스 기능을 수행한다.
  * *ex) GET 요청으로 레코드 검색, POST 요청으로 레코드 작성, PUT 요청으로 레코드 업데이트, DELETE 요청으로 레코드 삭제*

* 잘 디자인된 REST API는 HTTP 기능이 내장된 웹 브라우저에서 실행되는 웹 사이트와 유사하다.  
* 시간소인 또는 특정 인스턴트의 리소스 상태를 리소스 표현이라고 한다. 이런 정보는 거의 모든 형식(JSON, HTML, XLT, Python, PHP 등)으로 클라이언트에 전달할 수 있다.   
* 메타데이터, 권한 부여, URI, 캐싱, 쿠키 등의 중요한 식별자 정보를 포함하므로, 요청 헤더와 매개변수 역시 REST API 호출에서 중요하다. 요청 헤더와 응답 헤더는 일반적인 HTTP 상태 코드와 함께 잘 디자인된 REST API 내에서 사용된다.

<br>   

## 브라우저에 URL을 입력 후 요청하여 서버에서 응답하는 과정
1. 브라우저에 URL을 입력한다.
2. 브라우저는 URL주소에 적힌 값을 파싱하고 **HTTP 요청 메시지**를 만든다.
3. **DNS**(Domain Name Server)를 조회하여 **IP 주소**로 메시지를 받을 상대를 지정한다.
4. **OS**(운영체제)에 웹 서버로 송신을 의뢰한다.
5. 웹 서버 측 LAN(Local Area Network)에 메시지가 도착한다.
6. 웹 서버 측 방화벽이 검사하는데, **캐시 서버**를 통해 전달할지 말지를 결정한다.   
&nbsp;*(액세스한 페이지의 데이터가 캐시서버에 있으면 웹 서버에 의뢰하지 않고 바로 값을 읽을 수 있다. 페이지의 데이터 중 다시 이용할 수 있는 부분은 캐시 서버에 저장한다.)*
7. 웹 서버에 도착하면 패킷에 담긴 메시지를 복원해서 **WAS**(Web Application Server)에 전달한다.
8. WAS는 요청 메시지에 따른 데이터를 **응답 메시지**에 넣어 클라이언트로 송신한다.
9. 웹 브라우저는 웹 서버로부터 HTTP를 따라 데이터를 전송받는다.
10. 이어 렌더링 엔진을 사용해 데이터를 텍스트 및 이미지 등으로 변환한다.
11. 브라우저가 **최종 화면**을 사용자에게 표시한다.


<br>  

- - -

<br>

## Reference
[RedHat - 토픽 \> API 이해 \> REST API란? 구현 및 사용법](https://www.redhat.com/ko/topics/api/what-is-a-rest-api)   
[IBM - 클라우드 컴퓨팅 알아보기 \> REST API란?](https://www.ibm.com/kr-ko/cloud/learn/rest-apis)   
[NHN 클라우드 \> MeetUp! \> REST API 제대로 알고 사용하기](https://meetup.toast.com/posts/92)   
[HTTP 통신 과정](https://mysterico.tistory.com/29)   
[요청(Request) 응답(Response) 흐름 과정](https://deep-wide-studio.tistory.com/213)
