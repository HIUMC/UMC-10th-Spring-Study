package hello.core.annotation;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
@Qualifier("mainDiscountPolicy")
public @interface MainDiscountPolicy {
}
