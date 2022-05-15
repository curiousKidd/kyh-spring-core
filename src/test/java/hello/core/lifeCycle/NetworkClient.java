package hello.core.lifeCycle;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출, url = " + url);
//        conntect();
//        call("초기화 연결 메시지");
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void conntect() {
        System.out.println("connect: " + url);
    }

    //서비스 시작시 호출
    public void call(String message) {
        System.out.println("call : " + url + ", message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    //빈 생명주기

    /**
     * 스프링 빈의 이벤트 라이프사이클
     * 스프링컨테이너생성 -> 스프링빈생성 -> 의존관계주입 -> 초기화콜백 -> 사용 -> 소멸전콜백 -> 스프링 종료
     */

    //초기화 콜백과 소멸전 콜백은 @bean 어노테이션 안의 기능을 사용해도 되고
    //@PostConstruct @PreDestroy을 사용해도 되지만, 어노테이션 기능이 최신기능이다
    //각각의 장단점이 존재한다
    @PostConstruct
    public void init() throws Exception {
        conntect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() throws Exception {
        disconnect();
    }
}
