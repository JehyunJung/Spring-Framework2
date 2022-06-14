package hello.core;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok=new HelloLombok();
        helloLombok.setAge(123);
        System.out.println("helloLombok.getAge() = " + helloLombok.getAge());
    }
}
