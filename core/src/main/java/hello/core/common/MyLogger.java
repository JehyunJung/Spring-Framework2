package hello.core.common;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.UUID;

@Component
//@Scope(value="request")
@Scope(value="request",proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MyLogger {
    private String uuid;
    private String requestedURL;

    public void setRequestedURL(String requestedURL) {
        this.requestedURL = requestedURL;
    }

    public void log(String message){
        System.out.println("["+ uuid + "]"+"["+ requestedURL + "]"+"["+ message + "]");
    }

    @PostConstruct
    public void init(){
        uuid=UUID.randomUUID().toString();
        System.out.println("["+ uuid + "]"+"["+ requestedURL + "]"+"[request scope created: "+this+"]");
    }

    @PreDestroy
    public void destroy(){
        System.out.println("["+ uuid + "]"+"["+ requestedURL + "]"+"[request scope closed: "+this+"]");
    }
}
