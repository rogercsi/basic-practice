package group.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class MyRunner implements CommandLineRunner {

    @Autowired
    private Mybean mybean; // Assuming you have @Autowired for HelloWorld bean

    @Override
    public void run(String... args) throws Exception {
        mybean.testAop();
    }
}