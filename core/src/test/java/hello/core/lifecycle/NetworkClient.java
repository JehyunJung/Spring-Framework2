package hello.core.lifecycle;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class NetworkClient /*implements InitializingBean, DisposableBean*/ {
    private String url;

    public NetworkClient() {
        System.out.println("Constructor Method executed,url = " + url);
/*        connect();
        call("Calling~");*/
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect(){
        System.out.println("Connection: ..."+url);
    }
    //서비스 진행 동안 호출
    public void call(String message) {
        System.out.println("Call: " + url + " message = " + message);
    }
    //서비스 종류 후 호출
    public void disconnect(){
        System.out.println("Disconnected..." + url);
    }

    /*//의존관계 주입이 완료되면 초기화 콜백이 호출된다.
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Initializable Callback");
        connect();
        call("Calling~");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Disposable Callback");
        disconnect();
    }*/
    @PostConstruct
    public void init() throws Exception {
        System.out.println("Initializable Callback");
        connect();
        call("Calling~");
    }
    @PreDestroy
    public void close() throws Exception {
        System.out.println("Disposable Callback");
        disconnect();
    }

}
