package hello.core.scan.filter;

import org.springframework.context.annotation.ComponentScans;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface MyIncludeComponent {
}
