package microservicewithproxy;

import microservicewithproxy.model.external.BookValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;


@Configuration
@EnableAspectJAutoProxy
public class AppConfiguration {
}
