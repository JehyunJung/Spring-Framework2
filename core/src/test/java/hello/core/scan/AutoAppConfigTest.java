package hello.core.scan;

import hello.core.AutoAppConfig;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;

import static org.assertj.core.api.Assertions.assertThat;

public class AutoAppConfigTest {

    @Test
    void basicScan(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
        MemberService memberService = ac.getBean(MemberService.class);
        assertThat(memberService).isInstanceOf(MemberService.class);

        OrderServiceImpl orderServiceImpl = ac.getBean(OrderServiceImpl.class);
        System.out.println("DiscountPolicy = " + orderServiceImpl.getDiscountPolicy());

        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
            
            if(beanDefinition.getRole()==BeanDefinition.ROLE_APPLICATION){
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("bean = " + bean);
            }
        }
    }
    @Configuration
    @ComponentScan(
            basePackages = "hello.core",
            excludeFilters = @ComponentScan.Filter(type=FilterType.ANNOTATION,classes=Configuration.class)
    )
    static class AutoAppConfig{
        @Bean(name="memoryMemberRepository")
        public MemberRepository memberRepository(){
            return new MemoryMemberRepository();
        }

    }
}
