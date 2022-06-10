package hello.core.singleton;

public class SingletonService {
    private static final SingletonService instance=new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }
    //Private 생성자를 만들게 되면 클래스 외부에서 객체 생성이 불가능하다.
    private SingletonService(){

    }
    public void logic(){
        System.out.println("Logic Executed");
    }

}
