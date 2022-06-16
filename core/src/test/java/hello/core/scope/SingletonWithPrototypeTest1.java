package hello.core.scope;

import ch.qos.logback.core.net.server.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Provider;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonWithPrototypeTest1 {
    @Test
    void prototypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        PrototypeBean prototypeBean1 = ac.getBean(PrototypeBean.class);
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);
        
        prototypeBean1.addCount();
        assertThat(prototypeBean1.getCount()).isEqualTo(1);
        prototypeBean2.addCount();
        assertThat(prototypeBean2.getCount()).isEqualTo(1);

    }
    @Test
    void singletonClientUserPrototype(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class, ClientBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        ClientBean clientBean2 = ac.getBean(ClientBean.class);

        int count1=clientBean1.logic();
        assertThat(count1).isEqualTo(1);
        //System.out.println("PrototypeBean = " + clientBean1.getPrototypeBean());

        int count2=clientBean2.logic();
        assertThat(count2).isEqualTo(1);
        //System.out.println("PrototypeBean = " + clientBean2.getPrototypeBean());

    }

    @Scope("prototype")
    static class PrototypeBean{
        private int count=0;

        public void addCount(){
            count+=1;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
        @PostConstruct
        public void init(){
            System.out.println("Init method executed: "+this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("Destroy method executed: "+this);
        }
    }

    static class ClientBean{
       /* private final PrototypeBean prototypeBean;

        @Autowired
        public ClientBean(PrototypeBean prototypeBean){
            this.prototypeBean=prototypeBean;
        }*/
        /*@Autowired
        private ObjectProvider<PrototypeBean> prototypeBeanProvider;*/

        @Autowired
        private Provider<PrototypeBean> prototypeBeanProvider;


        public int logic(){
            //PrototypeBean prototypeBean=prototypeBeanProvider.getObject();
            PrototypeBean prototypeBean=prototypeBeanProvider.get();
            prototypeBean.addCount();
            int count=prototypeBean.getCount();
            return count;
        }

        /*public PrototypeBean getPrototypeBean(){
            return prototypeBean;
        }

        @PostConstruct
        public void init(){
            System.out.println("prototypeBean = " + prototypeBean);
            System.out.println("Init method executed: "+ this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("Destroy method executed: "+ this);
        }*/
    }
}
