# kyh-spring-core
김영한 Spring Core

#스프링 컨테이너의 역할  
스프링 컨테이너는 자바 객체의 생명 주기를 관리하며, 생성된 자바 객체들에게 추가적인 기능을 제공하는 역할을 합니다.  
여기서 말하는 자바 객체를 스프링에서는 `빈(Bean)`이라고 부릅니다.  
**스프링 컨테이너로 인해서 IoC와 DI의 원리를 적용할 수 있습니다.**

개발자는 new 연산자, 인터페이스 호출, 팩토리 호출 방식으로 객체를 생성하고 소멸시킬 수 있는데,  
스프링 컨테이너가 이 역할을 대신해 줍니다. **즉, 제어 흐름을 외부에서 관리 할 수 있게 합니다.**  
또한, 객체들 간의 의존 관계를 스프링 컨테이너가 런타임 과정에서 알아서 만들어 줍니다.

#스프링 컨테이너란?  
`ApplicationContext` 를 스프링 컨테이너라고 한다.  
더 정확하게는 **BeanFactory** , **ApplicationContext** 로 구분해서 이야기 할 수 있지만,  
일반적으로 **ApplicationContext**를 스프링 컨테이너 라고 합니다.  
둘중 어떠한 클래스를 호출하더라도, 동일한 결과를 가지고 오게 됩니다.

**ApplicationContext**

``` java
public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order.toString());
        System.out.println("order.calculatePrice = " + order.calculatePrice());
    }
```

**ApplicationContext**

``` java
/Users/gwnam/Library/Java/JavaVirtualMachines/openjdk-17.0.2/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=59327:/Applications/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/gwnam/Desktop/dev/IDE/core/out/production/classes:/Users/gwnam/Desktop/dev/IDE/core/out/production/resources:/Users/gwnam/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-starter/2.6.6/b34b4f0c1e99edc181ea40a6b1b79fa72658ee04/spring-boot-starter-2.6.6.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-autoconfigure/2.6.6/e5b575d304ec5c6746fef3cc594bbe89721d64c2/spring-boot-autoconfigure-2.6.6.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot/2.6.6/286137a0c57d159f845faa1c2e8d0b7548391085/spring-boot-2.6.6.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-starter-logging/2.6.6/e37b388e2407a1b03f920a7fd0722e41745c92c6/spring-boot-starter-logging-2.6.6.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/jakarta.annotation/jakarta.annotation-api/1.3.5/59eb84ee0d616332ff44aba065f3888cf002cd2d/jakarta.annotation-api-1.3.5.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/org.springframework/spring-core/5.3.18/7ff3000f3342989cb011b6095a0e86f2e5176cef/spring-core-5.3.18.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/org.yaml/snakeyaml/1.29/6d0cdafb2010f1297e574656551d7145240f6e25/snakeyaml-1.29.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/org.springframework/spring-context/5.3.18/34f6683d9dbe6edb02ad9393df3d3211b5484622/spring-context-5.3.18.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/ch.qos.logback/logback-classic/1.2.11/4741689214e9d1e8408b206506cbe76d1c6a7d60/logback-classic-1.2.11.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/org.apache.logging.log4j/log4j-to-slf4j/2.17.2/17dd0fae2747d9a28c67bc9534108823d2376b46/log4j-to-slf4j-2.17.2.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/org.slf4j/jul-to-slf4j/1.7.36/ed46d81cef9c412a88caef405b58f93a678ff2ca/jul-to-slf4j-1.7.36.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/org.springframework/spring-jcl/5.3.18/7e5d8cd447981bc20d4c397a2ba0a1c65ff2267a/spring-jcl-5.3.18.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/org.springframework/spring-aop/5.3.18/8e9cce60c60257ae1d5b3cd675ec3a8286ed1955/spring-aop-5.3.18.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/org.springframework/spring-beans/5.3.18/3f0ea6598a5a1eae0a672f025a33a0b7e0d6dfd3/spring-beans-5.3.18.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/org.springframework/spring-expression/5.3.18/61c51831e49a85fc5bf925253ca63f46fec6f013/spring-expression-5.3.18.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/ch.qos.logback/logback-core/1.2.11/a01230df5ca5c34540cdaa3ad5efb012f1f1f792/logback-core-1.2.11.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/org.slf4j/slf4j-api/1.7.36/6c62681a2f655b49963a5983b8b0950a6120ae14/slf4j-api-1.7.36.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/org.apache.logging.log4j/log4j-api/2.17.2/f42d6afa111b4dec5d2aea0fe2197240749a4ea6/log4j-api-2.17.2.jar hello.core.OrderApp
23:22:48.573 [main] DEBUG org.springframework.context.annotation.AnnotationConfigApplicationContext - Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@33e5ccce
23:22:48.580 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.annotation.internalConfigurationAnnotationProcessor'
23:22:48.633 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.event.internalEventListenerProcessor'
23:22:48.634 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.event.internalEventListenerFactory'
23:22:48.634 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.annotation.internalAutowiredAnnotationProcessor'
23:22:48.635 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.annotation.internalCommonAnnotationProcessor'
23:22:48.638 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'appConfig'
23:22:48.641 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'memberService'
23:22:48.649 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'memberRepository'
23:22:48.650 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'orderService'
23:22:48.650 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'discountPolicy'
order = Order{memberId=1, itemName='itemA', itemPrice=10000, discountPrice=1000}
order.calculatePrice = 9000

Process finished with exit code 0
```

**BeanFactory**

``` java
public static void main(String[] args) { 
    BeanFactory applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); MemberService         memberService = applicationContext.getBean("memberService", MemberService.class); 
    OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

    Long memberId = 1L;
    Member member = new Member(memberId, "memberA", Grade.VIP);
    memberService.join(member);

    Order order = orderService.createOrder(memberId, "itemA", 10000);

    System.out.println("order = " + order.toString());
    System.out.println("order.calculatePrice = " + order.calculatePrice());
}
```

**BeanFactory**

``` java
/Users/gwnam/Library/Java/JavaVirtualMachines/openjdk-17.0.2/Contents/Home/bin/java -javaagent:/Applications/IntelliJ IDEA.app/Contents/lib/idea_rt.jar=59257:/Applications/IntelliJ IDEA.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/gwnam/Desktop/dev/IDE/core/out/production/classes:/Users/gwnam/Desktop/dev/IDE/core/out/production/resources:/Users/gwnam/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-starter/2.6.6/b34b4f0c1e99edc181ea40a6b1b79fa72658ee04/spring-boot-starter-2.6.6.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-autoconfigure/2.6.6/e5b575d304ec5c6746fef3cc594bbe89721d64c2/spring-boot-autoconfigure-2.6.6.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot/2.6.6/286137a0c57d159f845faa1c2e8d0b7548391085/spring-boot-2.6.6.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/org.springframework.boot/spring-boot-starter-logging/2.6.6/e37b388e2407a1b03f920a7fd0722e41745c92c6/spring-boot-starter-logging-2.6.6.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/jakarta.annotation/jakarta.annotation-api/1.3.5/59eb84ee0d616332ff44aba065f3888cf002cd2d/jakarta.annotation-api-1.3.5.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/org.springframework/spring-core/5.3.18/7ff3000f3342989cb011b6095a0e86f2e5176cef/spring-core-5.3.18.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/org.yaml/snakeyaml/1.29/6d0cdafb2010f1297e574656551d7145240f6e25/snakeyaml-1.29.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/org.springframework/spring-context/5.3.18/34f6683d9dbe6edb02ad9393df3d3211b5484622/spring-context-5.3.18.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/ch.qos.logback/logback-classic/1.2.11/4741689214e9d1e8408b206506cbe76d1c6a7d60/logback-classic-1.2.11.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/org.apache.logging.log4j/log4j-to-slf4j/2.17.2/17dd0fae2747d9a28c67bc9534108823d2376b46/log4j-to-slf4j-2.17.2.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/org.slf4j/jul-to-slf4j/1.7.36/ed46d81cef9c412a88caef405b58f93a678ff2ca/jul-to-slf4j-1.7.36.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/org.springframework/spring-jcl/5.3.18/7e5d8cd447981bc20d4c397a2ba0a1c65ff2267a/spring-jcl-5.3.18.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/org.springframework/spring-aop/5.3.18/8e9cce60c60257ae1d5b3cd675ec3a8286ed1955/spring-aop-5.3.18.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/org.springframework/spring-beans/5.3.18/3f0ea6598a5a1eae0a672f025a33a0b7e0d6dfd3/spring-beans-5.3.18.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/org.springframework/spring-expression/5.3.18/61c51831e49a85fc5bf925253ca63f46fec6f013/spring-expression-5.3.18.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/ch.qos.logback/logback-core/1.2.11/a01230df5ca5c34540cdaa3ad5efb012f1f1f792/logback-core-1.2.11.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/org.slf4j/slf4j-api/1.7.36/6c62681a2f655b49963a5983b8b0950a6120ae14/slf4j-api-1.7.36.jar:/Users/gwnam/.gradle/caches/modules-2/files-2.1/org.apache.logging.log4j/log4j-api/2.17.2/f42d6afa111b4dec5d2aea0fe2197240749a4ea6/log4j-api-2.17.2.jar hello.core.OrderApp
23:05:23.832 [main] DEBUG org.springframework.context.annotation.AnnotationConfigApplicationContext - Refreshing org.springframework.context.annotation.AnnotationConfigApplicationContext@33e5ccce
23:05:23.840 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.annotation.internalConfigurationAnnotationProcessor'
23:05:23.890 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.event.internalEventListenerProcessor'
23:05:23.891 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.event.internalEventListenerFactory'
23:05:23.891 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.annotation.internalAutowiredAnnotationProcessor'
23:05:23.892 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'org.springframework.context.annotation.internalCommonAnnotationProcessor'
23:05:23.895 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'appConfig'
23:05:23.898 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'memberService'
23:05:23.905 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'memberRepository'
23:05:23.905 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'orderService'
23:05:23.906 [main] DEBUG org.springframework.beans.factory.support.DefaultListableBeanFactory - Creating shared instance of singleton bean 'discountPolicy'
order = Order{memberId=1, itemName='itemA', itemPrice=10000, discountPrice=1000}
order.calculatePrice = 9000

Process finished with exit code 0
```

그럼 여기서 의문점이 생기게 될 것입니다.

## 두가지 경우의 결과가 같은 이유는 무엇일까?

그 이유는 **ApplicationContext** 가 **BeanFactory** 를 상속 받았기 때문입니다.

```java
public interface ApplicationContext extends EnvironmentCapable, ListableBeanFactory, 
  HierarchicalBeanFactory, MessageSource, ApplicationEventPublisher, ResourcePatternResolver { 
  ... 
} 
```

## 그렇다면 두 경우의 차이점은 무엇일까?

어플리케이션을 개발할때는 빈을 관리하고 조회하는 기능은 물론, 다른 추가적인 기능이 필요합니다.


![어플리케이션 컨테스트의 역할 ](https://user-images.githubusercontent.com/77770531/165910663-8287af0f-9232-4aee-9184-086a017b9e7d.png)

```
BeanFactory에서 빈을 관리하는 기능을 물려받은 것은 맞습니다.  
그리고 ApplicationContext는 국제화가 지원되는 텍스트 메시지 관리, 이미지같은 파일 자원을 로드, 리너스로 등록된 빈에게 이벤트 발생 알림 등 부가적인 기능을 갖고 있습니다.  
그래서 스프링 컨테이너하면 주로 이 ApplicationContext를 뜻합니다.
```

## 하지만 빈 관리에서도 두 컨테이너가 동일한 기능을 하지는 않습니다.

`BeanFactory`는 처음으로 getBean() 메소드가 호출된 시점에서야 해당 빈을 생성하고,  
`ApplicationContext`는 Context 초기화 시점에 모든 싱글톤 빈을 미리 로드한 후 애플리케이션 가동 후에는 빈을 지연 없이 받을 수 있습니다.

#### 해당 이점 때문에 실제 개발에서는 ApplicationContext를 주로 사용합니다.
