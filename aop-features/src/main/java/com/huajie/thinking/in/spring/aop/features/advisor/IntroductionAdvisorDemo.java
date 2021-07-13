package com.huajie.thinking.in.spring.aop.features.advisor;

import com.huajie.thinking.in.spring.aop.overview.EchoService;
import java.lang.reflect.Method;
import java.util.Map;
import org.springframework.aop.IntroductionAdvisor;
import org.springframework.aop.IntroductionInfo;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.support.DefaultIntroductionAdvisor;

/**
 * {@link IntroductionAdvisor} 示例
 *
 * @Author: xiewenfeng
 * @Date: 2021/7/13 14:04
 */
public class IntroductionAdvisorDemo implements EchoService, Comparable<IntroductionAdvisorDemo> {

  public static void main(String[] args) {
    IntroductionAdvisorDemo target = new IntroductionAdvisorDemo();
    // 使用构造器会导致 IntroductionInfo 失效
    // ProxyFactory proxyFactory = new ProxyFactory(target);
    ProxyFactory proxyFactory = new ProxyFactory();
    proxyFactory.setTarget(target);
    proxyFactory.addAdvisor(new DefaultIntroductionAdvisor(new MethodBeforeAdvice() {
      @Override
      public void before(Method method, Object[] args, Object target) throws Throwable {
        System.out.println("BeforeAdvice : " + method);
      }
    }, new IntroductionInfo() {
      @Override
      public Class<?>[] getInterfaces() {
        return new Class[]{EchoService.class, Map.class};
      }
    }));

    EchoService proxy = (EchoService) proxyFactory.getProxy();
    System.out.println(proxy.echo("Hello,World"));

    // 可以被拦截，但是会报错，目标类最好实现了 Map 接口
    Map map = (Map) proxyFactory.getProxy();
    map.put(1,2);

    Comparable comparable = (Comparable) proxyFactory.getProxy();
    comparable.compareTo(null);
  }


  @Override
  public String echo(String message) {
    return "IntroductionAdvisorDemo:" + message;
  }

  @Override
  public int compareTo(IntroductionAdvisorDemo o) {
    return 0;
  }
}
