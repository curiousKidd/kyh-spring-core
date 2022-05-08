# kyh-spring-core
김영한 Spring Core

#**스프링 컨테이너**

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


#**싱글톤 패턴, 싱글톤 컨테이너**

기존 스프링이 없는 순수한 DI 컨테이너는 AppConfig가 요청을 할 때 마다 객체를 새로 요청합니다.
이 말은 고객의 트래픽이 초당 100이 나오면 초당 100개의 객체가 생성되고 사용된다는 말로써 메모리 낭비가 심합니다.
이 문제의 해결방안은 객체를 딱 하나만 생성하고, 해당 객체를 공유할 수 있도록 설계하는 것입니다. -> 이를 `싱글톤 패턴`이라고 합니다.

##싱글톤 패턴?
- 클래스의 인스턴스가 딱 한개만 생성되도록 보장하는 디자인 패턴입니다.
- 객체 인스턴스를 2개 이상 생성하지 못하도록 막아야하는데
        - private 생성자를 사용해서 외부에서 new 키워드를 사용하지 못다로록 막아야 합니다.

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

싱글톤 패턴을 적용하면 고객의 요청이 들어 올 때마다 객체를 생성하는 것이 아니라 
이미 만들어진 객체를 공유해서 사용하기 때문에 보다 효율적으로 사용 할 수 있게됩니다.
하지만 싱글톤 패턴은 여러가지 문제점들을 가지고 있습니다.

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

`스프링 컨테이너`는 싱글톤 패턴의 문제점을 해결하면서, 객체 인스턴스를 싱글톤(1개만 생성)으로 관리하게 됩니다.
        ->
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
        - 특정 클라이언트에 의존적인 필드가 있으면 안된다.
        - 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다!
        - 가급적 읽기만 가능해야 한다.
        - 필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다.
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

ThreadA가 사용자A 코드를 호출하고 ThreadB가 사용자B 코드를 호출한다 가정합니다.
StatefulService 의 price 필드는 공유되는 필드인데, 특정 클라이언트가 값을 변경하게 되었을때,
사용자A의 주문금액은 10000원이 되어야 하는데, 20000원이라는 결과가 나왔다.
진짜 공유필드는 조심해야하며, **스프링 빈은 항상 무상태(stateless)로 설계하도록 합시다.**
