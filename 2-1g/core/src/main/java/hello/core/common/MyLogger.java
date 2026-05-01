package hello.core.common;


import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
// 일단 가짜 프록시 객체를 주입하고, 필요한 시점에 진짜를 찾아서 주입해줌
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
// HTTP 요청 당 하나씩 생성, 요청이 끝나는 시점에 소멸된다
// uuid가 있기 때문에 다른 HTTP 요청과 구분할 수 있다.
public class MyLogger {

    private String uuid;
    private String requestURL;

    public void setRequestURL(String requestURL) {
        this.requestURL = requestURL;
    }

    public void log(String message) {
        System.out.println("[" + uuid + "]" + "[" + requestURL + "]" + message);
    }

    @PostConstruct
    public void init() {
        uuid = UUID.randomUUID().toString();
        System.out.println("[" + uuid + "] request scope bean create:" + this);
    }

    @PreDestroy
    public void close() {
        System.out.println();
        System.out.println("[" + uuid + "] request scope bean close:" + this);
    }
}
