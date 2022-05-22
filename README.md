# kyh-spring-core

김영한 Spring Core

# **스프링 컨테이너**

# 스프링 컨테이너의 역할

스프링 컨테이너는 자바 객체의 생명 주기를 관리하며, 생성된 자바 객체들에게 추가적인 기능을 제공하는 역할을 합니다.  
여기서 말하는 자바 객체를 스프링에서는 `빈(Bean)`이라고 부릅니다.  
**스프링 컨테이너로 인해서 IoC와 DI의 원리를 적용할 수 있습니다.**

개발자는 new 연산자, 인터페이스 호출, 팩토리 호출 방식으로 객체를 생성하고 소멸시킬 수 있는데,  
스프링 컨테이너가 이 역할을 대신해 줍니다. **즉, 제어 흐름을 외부에서 관리 할 수 있게 합니다.**  
또한, 객체들 간의 의존 관계를 스프링 컨테이너가 런타임 과정에서 알아서 만들어 줍니다.

# 스프링 컨테이너란?

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
    BeanFactory applicationContext = new AnnotationConfigApplicationContext(AppConfig.class); 
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

# **싱글톤 패턴, 싱글톤 컨테이너**

기존 스프링이 없는 순수한 DI 컨테이너는 AppConfig가 요청을 할 때 마다 객체를 새로 요청합니다. 이 말은 고객의 트래픽이 초당 100이 나오면 초당 100개의 객체가 생성되고 사용된다는 말로써 메모리
낭비가 심합니다. 이 문제의 해결방안은 객체를 딱 하나만 생성하고, 해당 객체를 공유할 수 있도록 설계하는 것입니다. -> 이를 `싱글톤 패턴`이라고 합니다.

## 싱글톤 패턴?

- 클래스의 인스턴스가 딱 한개만 생성되도록 보장하는 디자인 패턴입니다.
- 객체 인스턴스를 2개 이상 생성하지 못하도록 막아야하는데 - private 생성자를 사용해서 외부에서 new 키워드를 사용하지 못다로록 막아야 합니다.

``` java
public class SingletonService {
         //1. static 영역에 객체를 딱 1개만 생성해둔다.
         private static final SingletonService instance = new SingletonService();
         
         //2. public으로 열어서 객체 인스터스가 필요하면 이 static 메서드를 통해서만 조회하도록 허용한다.
         public static SingletonService getInstance() {
                return instance;
         }
         
         //3. 생성자를 private으로 선언해서 외부에서 new 키워드를 사용한 객체 생성을 못하게 막는다.
         private SingletonService() {
         }
         
         public void logic() {
                System.out.println("싱글톤 객체 로직 호출");
         }
}
```

1. static 영역에 객체 instance를 미리 하나 생성해서 올려둔다.
2. 이 객체 인스턴스가 필요하면 오직 getInstance() 메서드를 통해서만 조회할 수 있다. 이 메서드를 호출하면 항상 같은 인스턴스를 반환한다.
3. 딱 1개의 객체 인스턴스만 존재해야 하므로, 생성자를 private으로 막아서 혹시라도 외부에서 new 키워드로 객체 인스턴스가 생성되는 것을 막는다.

싱글톤 패턴을 적용하면 고객의 요청이 들어 올 때마다 객체를 생성하는 것이 아니라 이미 만들어진 객체를 공유해서 사용하기 때문에 보다 효율적으로 사용 할 수 있게됩니다. 하지만 싱글톤 패턴은 여러가지 문제점들을
가지고 있습니다.

## 싱글톤 패턴의 문제점

- 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다.
- 의존관계상 클라이언트가 구체 클래스에 의존한다. DIP를 위반한다.
- 클라이언트가 구체 클래스에 의존해서 OCP 원칙을 위반할 가능성이 높다.
- 테스트하기 어렵다.
- 내부 속성을 변경하거나 초기화 하기 어렵다.
- private 생성자로 자식 클래스를 만들기 어렵다.
- 결론적으로 유연성이 떨어진다.
- 안티패턴으로 불리기도 한다.

이러한 문제점을 해결한 방안이 `싱글톤 컨테이너` 입니다.

## 싱글톤 컨테이너란?

`싱글톤 컨테이너`는 이름 그래도 컨테이너의 하나의 객체를 등록시켜서 사용하는 방식입니다. `스프링 컨테이너를 싱글톤 컨테이너라고도 한다`

`스프링 컨테이너`는 싱글톤 패턴의 문제점을 해결하면서, 객체 인스턴스를 싱글톤(1개만 생성)으로 관리하게 됩니다. ->
지금까지 우리가 학습한 스프링 빈이 바로 싱글톤으로 관리되는 빈입니다.

- 스프링 컨테이너는 싱글턴 패턴을 적용하지 않아도, 객체 인스턴스를 싱글톤으로 관리한다.
- 이전에 설명한 컨테이너 생성 과정을 자세히 보자. 컨테이너는 객체를 하나만 생성해서 관리한다.
- 스프링 컨테이너는 싱글톤 컨테이너 역할을 한다. 이렇게 싱글톤 객체를 생성하고 관리하는 기능을 싱글톤 레지스트리라 한다.
- 스프링 컨테이너의 이런 기능 덕분에 싱글턴 패턴의 모든 단점을 해결하면서 객체를 싱글톤으로 유지할 수 있다.
- 싱글톤 패턴을 위한 지저분한 코드가 들어가지 않아도 된다.
- DIP, OCP, 테스트, private 생성자로 부터 자유롭게 싱글톤을 사용할 수 있다

``` java
@DisplayName("스프링 컨테이너와 싱글톤")
void springContainer() {
        ApplicationContext ac = new
        AnnotationConfigApplicationContext(AppConfig.class);
        //1. 조회: 호출할 때 마다 같은 객체를 반환
        MemberService memberService1 = ac.getBean("memberService", MemberService.class);
        
        //2. 조회: 호출할 때 마다 같은 객체를 반환
        MemberService memberService2 = ac.getBean("memberService", MemberService.class);
        
        //참조값이 같은 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);
        
        //memberService1 == memberService2
        assertThat(memberService1).isSameAs(memberService2);
}
```

![image](https://user-images.githubusercontent.com/77770531/167294877-d349cbb5-4b3c-4b8b-af15-24652ab0a439.png)

스프링 컨테이너 덕분에 고객의 요청이 들어올 때 마다 객체를 생성하는 것이 아니라, 이미 만들어진 객체를 공유함으로써 효율적으로 재사용 할 수 있습니다.

```
참고: 스프링의 기본 빈 등록 방식은 싱글톤이지만, 싱글톤 방식만 지원하는 것은 아니다. 요청할 때 마다
새로운 객체를 생성해서 반환하는 기능도 제공한다. 자세한 내용은 뒤에 빈 스코프에서 설명하겠다.
```

## 싱글톤 컨테이너의 주의점

- 싱글톤 패턴이든, 스프링 같은 싱글톤 컨테이너를 사용하든, 객체 인스턴스를 하나만 생성해서 공유하는
- 싱글톤 방식은 여러 클라이언트가 하나의 같은 객체 인스턴스를 공유하기 때문에 싱글톤 객체는 상태를 유지(stateful)하게 설계하면 안된다.
- 무상태(stateless)로 설계해야 한다!
  - 특정 클라이언트에 의존적인 필드가 있으면 안된다. - 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다!
  - 가급적 읽기만 가능해야 한다. - 필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다.
- 스프링 빈의 필드에 공유 값을 설정하면 정말 큰 장애가 발생할 수 있다!!!

``` java
public class StatefulService {
         private int price; //상태를 유지하는 필드
         
         public void order(String name, int price) {
                 System.out.println("name = " + name + " price = " + price);
                 this.price = price; //여기가 문제!
         }
         
         public int getPrice() {
                return price;
         }
}
```

``` java
public class StatefulServiceTest {
         @Test
         void statefulServiceSingleton() {
                ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
                
                StatefulService statefulService1 = ac.getBean("statefulService", StatefulService.class);
                
                //ThreadA: A사용자 10000원 주문
                statefulService1.order("userA", 10000);
                
                //ThreadB: B사용자 20000원 주문
                statefulService2.order("userB", 20000);
                
                //ThreadA: 사용자A 주문 금액 조회
                int price = statefulService1.getPrice();
                
                //ThreadA: 사용자A는 10000원을 기대했지만, 기대와 다르게 20000원 출력
                System.out.println("price = " + price);
                Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
         }
                
                static class TestConfig {
                @Bean
                public StatefulService statefulService() {
                        return new StatefulService();
                }
 }
```

ThreadA가 사용자A 코드를 호출하고 ThreadB가 사용자B 코드를 호출한다 가정합니다. StatefulService 의 price 필드는 공유되는 필드인데, 특정 클라이언트가 값을 변경하게 되었을때,
사용자A의 주문금액은 10000원이 되어야 하는데, 20000원이라는 결과가 나왔다. 진짜 공유필드는 조심해야하며, **스프링 빈은 항상 무상태(stateless)로 설계하도록 합시다.**

# 의존성 주입

의존성 주입의 경우는 4가지 방법이 있다

1. 생성자 의존성 주입
2. 수정자 의존성 주입
3. 필드 의존성 주입 -> 사용하지 말자, 단 테스트 코드 및 Configuration 에서는 사용해도 무관하다
    - 왜냐하면 실제 프로그램과는 무관하게 진행 할 수 있기 때문
4. 일반 메서드 주입

```
자바빈 프로퍼티
자바에서 사용한 필드의 값을 직접적으로 변경하지 않고, `setter`, `getter`를 활용해서 수정하는 규칙
```
## 옵션처리
주입할 스프링 빈이 없어도 동작해야 할 때가 있다.
그런데 `@Autowired` 만 사용하면 `required` 옵션의 기본값이 `true` 로 되어 있어서 자동 주입 대상이 없으면 오류가 발생한다

**자동 주입 대상을 옵션으로 처리하는 방법은 다음과 같다.**
- `@Autowired(required=false)` : 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 안됨
- `org.springframework.lang.@Nullable` : 자동 주입할 대상이 없으면 null이 입력된다.
- `Optional<>` : 자동 주입할 대상이 없으면 Optional.empty 가 입력된다

``` java
        //호출 안됨
        @Autowired(required = false)
        public void setNoBean1(Member member) {
         System.out.println("setNoBean1 = " + member);
        }
        //null 호출
        @Autowired
        public void setNoBean2(@Nullable Member member) {
         System.out.println("setNoBean2 = " + member);
        }
        //Optional.empty 호출
        @Autowired(required = false)
        public void setNoBean3(Optional<Member> member) {
         System.out.println("setNoBean3 = " + member);
        }
```

``` java
// 출력결과
setNoBean2 = null
setNoBean3 = Optional.empty
```
***참고: @Nullable, Optional은 스프링 전반에 걸쳐서 지원된다. 예를 들어서 생성자 자동 주입에서 특정 필드에만 사용해도 된다.***

## 생성자 주입을 선택해라!
과거에는 수정자 주입과 필드 주입을 많이 사용했지만, 최근에는 스프링을 포함한 DI 프레임워크 대부분이
생성자 주입을 권장한다. 그 이유는 다음과 같다.

**불변**
- 대부분의 의존관계 주입은 한번 일어나면 애플리케이션 종료시점까지 의존관계를 변경할 일이 없다. 오히려 대부분의 의존관계는 애플리케이션 종료 전까지 변하면 안된다.(불변해야 한다.)
- 수정자 주입을 사용하면, setXxx 메서드를 public으로 열어두어야 한다.
- 누군가 실수로 변경할 수 도 있고, 변경하면 안되는 메서드를 열어두는 것은 좋은 설계 방법이 아니다.
- 생성자 주입은 객체를 생성할 때 딱 1번만 호출되므로 이후에 호출되는 일이 없다. 따라서 불변하게 설계할 수 있다.
- 생성자 주입 방식을 선택하는 이유는 여러가지가 있지만, 프레임워크에 의존하지 않고, 순수한 자바 언어의 특징을 잘 살리는 방법이기도 하다.
- 기본으로 생성자 주입을 사용하고, 필수 값이 아닌 경우에는 수정자 주입 방식을 옵션으로 부여하면 된다.
- 생성자 주입과 수정자 주입을 동시에 사용할 수 있다.
- 항상 생성자 주입을 선택해라! 그리고 가끔 옵션이 필요하면 수정자 주입을 선택해라. 필드 주입은 사용하지 않는게 좋다

## lombok
최근에는 생성자를 딱 1개 두고, @Autowired 를 생략하는 방법을 주로 사용한다. 
여기에 Lombok 라이브러리의 @RequiredArgsConstructor 함께 사용하면 기능은 다 제공하면서, 코드는 깔끔하게 사용할 수 있다

``` java
//lombok 설정 추가 시작
configurations {
 compileOnly {
 extendsFrom annotationProcessor
 }
}

dependencies {
 //lombok 라이브러리 추가 시작
 compileOnly 'org.projectlombok:lombok'
 annotationProcessor 'org.projectlombok:lombok'
 testCompileOnly 'org.projectlombok:lombok'
 testAnnotationProcessor 'org.projectlombok:lombok'
 //lombok 라이브러리 추가 끝
 }
}
//lombok 설정 추가 끝
```

`@Autowired` 는 타입(Type)으로 조회한다.
그런데 만약 같은 타입의 스프링 빈이 2개 이상이라면 `NoUniqueBeanDefinitionException` 오류가 발생한다

해당 오류를 해결 할 수 있는 방법은
- @Autowired 필드 명 매칭    -> 사용하지 않는 것을 추천
- @Qualifier @Qualifier끼리 매칭 빈 이름 매칭    -> 추가 구분자(부제목)를 달아줌으로써 구분할 수 있게 함
- @Primary 사용   -> 우선권 부여

## @Qualifier 정리
1. @Qualifier끼리 매칭
2. 빈 이름 매칭
3. NoSuchBeanDefinitionException 예외 발생

## 우선순위
`@Primary` 는 기본값 처럼 동작하는 것이고, `@Qualifier` 는 매우 상세하게 동작한다. 
이런 경우 어떤 것이 우선권을 가져갈까? 
스프링은 자동보다는 수동이, 넒은 범위의 선택권 보다는 좁은 범위의 선택권이 우선순위가 높다. 
따라서 여기서도 @Qualifier 가 우선권이 높다.

이렇게 해서 같은 타입의 스프링 빈이 두개이상 등록될 경우 `List`, `Map`로 받을 수 있다
``` java
@Test
 void findAllBean() {
         ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
         DiscountService discountService = ac.getBean(DiscountService.class);
         Member member = new Member(1L, "userA", Grade.VIP);
         int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");
         assertThat(discountService).isInstanceOf(DiscountService.class);
         assertThat(discountPrice).isEqualTo(1000);
 }
 static class DiscountService {
 private final Map<String, DiscountPolicy> policyMap;
 private final List<DiscountPolicy> policies;
 public DiscountService(Map<String, DiscountPolicy> policyMap,
List<DiscountPolicy> policies) {
         this.policyMap = policyMap;
         this.policies = policies;
         System.out.println("policyMap = " + policyMap);
         System.out.println("policies = " + policies);
 }
 public int discount(Member member, int price, String discountCode) {
         DiscountPolicy discountPolicy = policyMap.get(discountCode);
         System.out.println("discountCode = " + discountCode);
         System.out.println("discountPolicy = " + discountPolicy);
         return discountPolicy.discount(member, price);
 }
 }
```

## 조회한 빈이 모두 필요할 때, List, Map
의도적으로 정말 해당 타입의 스프링 빈이 다 필요한 경우도 있다.
예를 들어서 할인 서비스를 제공하는데, 클라이언트가 할인의 종류(rate, fix)를 선택할 수 있다고 가정해보자.
스프링을 사용하면 소위 말하는 전략 패턴을 매우 간단하게 구현할 수 있다.

``` java
public class AllBeanTest {
         @Test
         void findAllBean() {
                 ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class, DiscountService.class);
                 DiscountService discountService = ac.getBean(DiscountService.class);
                 Member member = new Member(1L, "userA", Grade.VIP);
                 int discountPrice = discountService.discount(member, 10000, "fixDiscountPolicy");
                 assertThat(discountService).isInstanceOf(DiscountService.class);
                 assertThat(discountPrice).isEqualTo(1000);
         }
         
         static class DiscountService {
                private final Map<String, DiscountPolicy> policyMap;
                private final List<DiscountPolicy> policies;
         
                public DiscountService(Map<String, DiscountPolicy> policyMap, List<DiscountPolicy> policies) {
                        this.policyMap = policyMap;
                        this.policies = policies;
                        System.out.println("policyMap = " + policyMap);
                        System.out.println("policies = " + policies);
                }
                
                public int discount(Member member, int price, String discountCode) {
                        DiscountPolicy discountPolicy = policyMap.get(discountCode);
                        System.out.println("discountCode = " + discountCode);
                        System.out.println("discountPolicy = " + discountPolicy);
                        return discountPolicy.discount(member, price);
                }
        }
}
```

## 로직 분석
```
DiscountService는 Map으로 모든 DiscountPolicy 를 주입받는다. 이때 fixDiscountPolicy, rateDiscountPolicy 가 주입된다.
discount () 메서드는 discountCode로 "fixDiscountPolicy"가 넘어오면 map에서 fixDiscountPolicy 스프링 빈을 찾아서 실행한다. 
물론 “rateDiscountPolicy”가 넘어오면 rateDiscountPolicy 스프링 빈을 찾아서 실행한다.
```

## 주입 분석
```
Map<String, DiscountPolicy> : map의 키에 스프링 빈의 이름을 넣어주고, 그 값으로 DiscountPolicy 타입으로 조회한 모든 스프링 빈을 담아준다.
List<DiscountPolicy> : DiscountPolicy 타입으로 조회한 모든 스프링 빈을 담아준다.
만약 해당하는 타입의 스프링 빈이 없으면, 빈 컬렉션이나 Map을 주입한다.
```

## 자동, 수동의 올바른 실무 운영 기준
### 편리한 자동 기능을 기본으로 사용하자
그러면 어떤 경우에 컴포넌트 스캔과 자동 주입을 사용하고, 어떤 경우에 설정 정보를 통해서 수동으로 빈을 등록하고, 의존관계도 수동으로 주입해야 할까?
결론부터 이야기하면, 스프링이 나오고 시간이 갈 수록 점점 자동을 선호하는 추세다.
스프링은 `@Component` 뿐만 아니라 `@Controller`, `@Service`, `@Repository`처럼 계층에 맞추어 일반적인 애플리케이션 로직을 자동으로 스캔할 수 있도록 지원한다.
거기에 더해서 최근 스프링 부트는 컴포넌트 스캔을 기본으로 사용하고, 스프링 부트의 다양한 스프링 빈들도 조건이 맞으면 자동으로 등록하도록 설계했다.

설정 정보를 기반으로 애플리케이션을 구성하는 부분과 실제 동작하는 부분을 명확하게 나누는 것이 이상적이지만, 
개발자 입장에서 스프링 빈을 하나 등록할 때 `@Component` 만 넣어주면 끝나는 일을
`@Configuration` 설정 정보에 가서 `@Bean` 을 적고, 객체를 생성하고, 주입할 대상을 일일이 적어주는 과정은 상당히 번거롭다.
또 관리할 빈이 많아서 설정 정보가 커지면 설정 정보를 관리하는 것 자체가 부담이 된다.
그리고 결정적으로 자동 빈 등록을 사용해도 OCP, DIP를 지킬 수 있다.

### 그러면 수동 빈 등록은 언제 사용하면 좋을까?

애플리케이션은 크게 업무 로직과 기술 지원 로직으로 나눌 수 있다.
- 업무 로직 빈: 웹을 지원하는 컨트롤러, 핵심 비즈니스 로직이 있는 서비스, 데이터 계층의 로직을 처리하는 리포지토리등이 모두 업무 로직이다. 보통 비즈니스 요구사항을 개발할 때 추가되거나 변경된다.
- 기술 지원 빈: 기술적인 문제나 공통 관심사(AOP)를 처리할 때 주로 사용된다. 데이터베이스 연결이나, 공통 로그 처리 처럼 업무 로직을 지원하기 위한 하부 기술이나 공통 기술들이다.
- 업무 로직은 숫자도 매우 많고, 한번 개발해야 하면 컨트롤러, 서비스, 리포지토리 처럼 어느정도 유사한 패턴이 있다. 
        이런 경우 자동 기능을 적극 사용하는 것이 좋다. 보통 문제가 발생해도 어떤 곳에서 문제가 발생했는지 명확하게 파악하기 쉽다.
- 기술 지원 로직은 업무 로직과 비교해서 그 수가 매우 적고, 보통 애플리케이션 전반에 걸쳐서 광범위하게 영향을 미친다. 
        그리고 업무 로직은 문제가 발생했을 때 어디가 문제인지 명확하게 잘 들어나지만, 기술 지원 로직은 적용이 잘 되고 있는지 아닌지 조차 파악하기 어려운 경우가 많다. 
        그래서 이런 기술 지원 로직들은 가급적 수동 빈 등록을 사용해서 명확하게 들어내는 것이 좋다.
