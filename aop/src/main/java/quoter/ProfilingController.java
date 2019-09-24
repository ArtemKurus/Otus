package quoter;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

@Getter
@Setter
public class ProfilingController {
    @Value("true")
    private boolean enabled;
}
