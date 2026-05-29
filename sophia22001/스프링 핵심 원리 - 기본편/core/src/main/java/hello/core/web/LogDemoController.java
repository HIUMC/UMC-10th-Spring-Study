package hello.core.web;

import hello.core.common.MyLogger;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequiredArgsConstructor
public class LogDemoController {

    private final LogDemoService logDemoService;
//    private final MyLogger myLogger; // 시도1
//    private final ObjectProvider<MyLogger> myLoggerProvider; // 시도2
    private final MyLogger myLogger;  // 프록시 사용시 // 시도3

    @RequestMapping("log-demo")
    @ResponseBody
    public String logDemo(HttpServletRequest request) throws InterruptedException {
//        MyLogger myLogger = myLoggerProvider.getObject(); // 추가 // 시도2

        String requestURL = request.getRequestURL().toString();
        myLogger.setRequestUrl(requestURL);

        myLogger.log("controller test");
//        Thread.sleep(1000); // 여러 사용자가 동시에 요청이 들어오는 경우 테스트
        logDemoService.logic("testId");
        return "OK";
    }


}
