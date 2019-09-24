package quoter;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Profiling
@Component
public class Terminator implements Quoter {
    @Value("I`ll be back")
    private  String message;

    @InjectRandomInt(min = 2, max = 7)
    private int repeat;

    public Terminator(){
        System.out.println("Phase 1");
    };

    @PostConstruct
    public void init(){
        System.out.println("Phase 2");
        System.out.println(repeat);
    }

    @Override
    public void sayQuote() {
        for(int i = 0; i < repeat; i++){
            System.out.println("message = " + message);
        }

    }
}
