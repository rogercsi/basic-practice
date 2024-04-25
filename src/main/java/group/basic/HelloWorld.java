package group.basic;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication // Assuming you're using Spring Boot
@EnableAspectJAutoProxy
public class HelloWorld {
    public void testAop() {
        System.out.println("testAop");
    }
    public static void main(String[] args) {
        SpringApplication.run(HelloWorld.class, args);
    }
}
