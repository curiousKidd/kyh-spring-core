# kyh-spring-core
김영한 Spring Core

#스프링 컨테이너  
`ApplicationContext` 를 스프링 컨테이너라고 한다.  
더 정확하게는 **BeanFactory** , **ApplicationContext** 로 구분해서 이야기 할 수 있지만,  
일반적으로 **ApplicationContext**를 스프링 컨테이너 라고 한다.  
둘중 어떠한 클래스를 호출하더라도, 동일한 결과를 가지고 오게 됩니다.  
그럼 여기서 이해가 안가는 부분이 생길 것입니다

## 두가지 경우의 결과가 같은 이유는 무엇일까?

그 이유는 **ApplicationContext** 가 **BeanFactory** 를 상속 받았기 때문입니다.

```
public interface ApplicationContext extends EnvironmentCapable, ListableBeanFactory, HierarchicalBeanFactory, MessageSource, ApplicationEventPublisher, ResourcePatternResolver { ... } 
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
