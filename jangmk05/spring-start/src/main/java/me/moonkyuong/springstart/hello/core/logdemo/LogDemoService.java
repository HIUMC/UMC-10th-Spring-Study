package me.moonkyuong.springstart.hello.core.logdemo;

import lombok.RequiredArgsConstructor;
import me.moonkyuong.springstart.hello.core.common.MyLogger;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LogDemoService {
    private final MyLogger myLogger;

    public void logic(String id) {
        myLogger.log("service id = " + id);
    }
}
