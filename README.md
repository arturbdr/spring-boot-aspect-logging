### Sample Spring Boot Logging Application with Aspect

This is a simple application to exemplify hoe to use Aspects for logging Controllers Payloads in Spring.
It uses the spring boot starter  ```spring-boot-starter-aop```

- Check the custom annotation ```config.annotation.ControllerLog ``` and it's respective implementation   ```config.annotation.impl.ControllerLogImpl```

- Sample of use of this annotation is in the Controller ```gateway.controller.SampleLoggedControllerB```
- Sample of testing the use of this annotation is in the integration test ```gateway.controller.SampleLoggedControllerBTest```