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

## HTTP Method
|**메소드**|역할|예시|
|----|-------------|-------|
|**GET**|특정 리소스의 표시를 요청한다. GET 요청은 데이터 받기만 수행한다.|게시판 리스트 불러오기|
|**HEAD**|GET 메서드의 요청과 동일한 응답을 요구하지만, 응답 본문을 포함하지 않는다.|Meta정보 요청|
|**POST**|특정 리소스에 엔티티(실체, 객체)를 제출할 때 쓰인다.|회원가입, 로그인|
|**PUT**|목적 리소스의 모든 현재 표시를 요청 payload로 바꾼다.(전체 수정)|회원정보 전체 수정|
|**DELETE**|특정 리소스를 삭제한다.|회원정보 삭제|
|**CONNECT**|목적 리소스로 식별되는 서버로 터널을 맺는다.|프록시 연결 요청|
|**OPTIONS**|목적 리소스의 통신을 설정한다.|요청한 URL에 어떤 메소드가 가능한지 질문|
|**TRACE**|목적 리소스의 경로를 따라 메시지 look-back 테스트를 한다.|이전까지 요청한 정보들의 목록 요청|
|**PATCH**|리소스의 부분만을 수정하는 데 쓰인다.(일부 수정)|회원정보 일부 수정|

\* *look-back 테스트: 회선종단장치와 데이터 단말 장치의 작동 상태를 검사하는 방법*

<img width="700px" src="https://t1.daumcdn.net/cfile/tistory/997FDE4F5B34DB1C1E"></img>   

<br>

## HTTP Status Code
**상태 코드**는 서버가 클라이언트에게 응답의 상태를 알리는 수단이며, 다섯가지 클래스로 분류된다.
### 1xx: Informational - 요청 정보 처리 중
  * 클라이언트가 보낸 요청을 서버가 성공적으로 수신했으며 서버 끝에서 처리 중이라는 뜻이다.
  * 서버의 임시 응답이며 일반적으로 상태 줄, 선택적 헤더만 포함하며 빈 줄로 끝난다.
  * 현재는 거의 사용하지 않는다.
### 2xx: Success - 요청을 정상적으로 처리함
  * 서버가 요청을 받고 성공적으로 처리되었음을 나타낸다.
### 3xx: Redirection - 요청을 완료하기 위해 추가 동작 필요
  * 브라우저는 자동으로 다른 URL로 리디렉션되므로 브라우저 창에는 이 코드가 표시되지 않는다.
  * 다만, 이미지 파일처럼 캐싱된 파일을 새로고침 후 확인하면 3xx 코드를 확인할 수 있다.
### 4xx: Client Error - 서버가 요청을 이해하지 못함
  * 서버가 해결할 수 없는 클라이언트 측 에러 코드다.
  * 주로 클라이언트(사용자)가 서버에 잘못된 요청을 보냈을 때 발생한다. 
### 5xx: Server Error - 서버가 요청 처리 실패함
  * 서버가 클라이언트의 요청을 처리하지 못했을 때 발생한다.
  * 서버는 보안 상 통신하지 않는 것이 가장 좋으므로 대부분의 에러 코드를 500 Error로 처리한다.

## 주요 HTTP Status Code
|웹 서버 상태 코드|내용|
|---|---------|
|**200 OK**|클라이언트의 요청이 성공.|
|**201 Create**|클라이언트의 PUT 요청 성공.|
|**301 Moved Permanently**|브라우저의 요청을 다른 URL로 항시 전달. 다른 URL에 대한 정보는 Location 헤더에 나타남.|
|**302 Moved Temporarily**|브라우저의 요청을 임시 URL로 바꾸고 Location 헤더에 변경한 URL 정보를 적음.<br>클라이언트가 다음에 같은 요청을 하면 기존의 URL로 복귀.|
|**304 Not Modified**|브라우저가 서버에게 요청한 자료에 대해, 서버는 클라이언트 내에 복사된 캐시를 사용하면 됨을 의미.<br>서버는 If-Modified-Since와 If-None-Match 요청 헤더를 사용해 클라이언트가 가장 최근의 자료를 가지고 있는지 확인.|
|**400 Bad Request**|클라이언트가 서버에 잘못된 요청을 함.|
|**401 Unauthorized**|서버가 클라이언트의 요청에 대해 HTTP 인증 확인을 요구.|
|**403 Forbidden**|클라이언트의 요청에 대해 접근 차단.|
|**404 Not Found**|클라이언트가 서버에 요청한 자료가 존재하지 않음.|
|**500 Internal Server Error**|서버가 클라이언트의 요청을 실행할 수 없을 때 발생.<br>일반적으로 SQL 인젝션 취약점이 존재하는지 확인할 때 500 에러가 유용하게 사용됨.|

<br>

## Request & Response Header
<img width="800px" src="https://goodgid.github.io/assets/img/network/http_communicate_process_4.png"></img> 

### Request Header
<img width="800px" src="https://mdn.mozillademos.org/files/13821/HTTP_Request_Headers2.png"></img> 
1. GET / HTTP / 1.1 : HTTP 전송 방법과 프로토콜 버전
2. Host : 요청하는 서버 주소
3. User-Agent : OS / 브라우저 정보
4. Accept : 클라이언트가 이해 가능한 컨텐츠 타입
5. Accept-Language : 클라이언트 인식 언어
6. Accept-Encoding : 클라이언트 인코딩 방법
7. Connection : 전송 완료 후 접속 유지 정보(keep-alive)
8. Upgrade-Insecure-Requests : 신호를 보낼 때 데이터 암호화 여부
9. Content-Type: 클라이언트에게 반환되어야하는 컨텐츠 유형
10. Content-Length: 본문 크기

### Response Header
<img width="800px" src="https://mdn.mozillademos.org/files/13823/HTTP_Response_Headers2.png"></img> 
1. HTTP / 1.1 200 ok : 프로토콜 버전과 응답상태
2. Access-Control-Allow-Origin : 서버에 타 사이트의 접근을 제한하는 방침
3. Connection : 전송 완료 후 접속 유지 정보(keep-alive)
4. Content-Encoding : 미디어 타입을 압축한 방법
5. Date : 헤더가 만들어진 시간
6. ETag : 버전의 리소스를 구분하는 식별자
7. Keep-Alive : 연결 타임아웃과 요청 최대 개수 정보
8. Last-Modified : 웹 시간, 수정될 때만 데이터 변경(캐시연관)
9. Server : 웹서버로 사용되는 프로그램 이름
10. Set-Cookie : 쿠키 정보
11. Transfer-Encoding : 인코딩 형식 지정
12. Vary: 캐시된 응답을 새로운 요청 헤더 대신 사용할 수 있는지 여부를 결정
13. X-Frame-Options : frame / iframe / object 허용 여부

<br>

- - -

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
[HTTP 개념잡기](https://velog.io/@doomchit_3/Internet-HTTP-%EA%B0%9C%EB%85%90%EC%B0%A8%EB%A0%B7-IMBETPY)   
[MDN Web Docs \> HTTP /> HTTP 요청 가이드](https://developer.mozilla.org/ko/docs/Web/HTTP/Methods)   
[HTTP 요청/응답 및 HTTP 요청 method feat. Restful API](https://pro-self-studier.tistory.com/126)   
[HTTP \> 메시지 포맷](https://ko.wikipedia.org/wiki/HTTP)   
[HTTP 상태코드](https://yunyoung1819.tistory.com/16)  
[HTTP를 사용한 통신 과정](https://goodgid.github.io/HTTP-Communicate-Process/)   
[요청(Request) 응답(Response) 흐름 과정](https://deep-wide-studio.tistory.com/213)
[MDN Web Docs \> HTTP /> HTTP 헤더 \> Vary](https://developer.mozilla.org/ko/docs/Web/HTTP/Headers/Vary)
