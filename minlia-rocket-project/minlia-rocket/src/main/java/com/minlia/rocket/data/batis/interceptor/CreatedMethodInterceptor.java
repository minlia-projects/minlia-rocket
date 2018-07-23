package com.minlia.rocket.data.batis.interceptor;

import com.minlia.rocket.data.batis.event.publisher.AfterCreatedEventPublisher;
import com.minlia.rocket.data.batis.event.publisher.BeforeCreatedEventPublisher;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author will
 *
 * <!-- 配置AOP --> <aop:config> <aop:pointcut id="createdPointCut" expression="execution(*
 * com.service.impl.BusinessServiceImpl.insert*(..) )" /> <aop:advisor
 * advice-ref="createdMethodInterceptor" pointcut-ref="createdPointCut"/> </aop:config>
 */
public class CreatedMethodInterceptor implements MethodInterceptor {

  @Autowired
  private AfterCreatedEventPublisher afterCreatedEventPublisher;
  @Autowired
  private BeforeCreatedEventPublisher beforeCreatedEventPublisher;


  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {
    beforeCreatedEventPublisher.publish(this);
    Object ret = invocation.proceed();
    afterCreatedEventPublisher.publish(this);
    return ret;
  }

}
