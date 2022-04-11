package ru.itis.pashin.websiteservice.aspect;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:ruslan.pashin@waveaccess.ru">Ruslan Pashin</a>
 */
@Aspect
@Component
@EnableAspectJAutoProxy
@RequiredArgsConstructor
@Slf4j
public class LoginAspect {

    //private final MailServiceClient mailServiceClient;

    @Pointcut("execution(public * ru.itis.pashin.websiteservice.security.details.UserDetailsServiceImpl.loadUserByUsername(..))")
    public void callAtLogin() {

    }

    @AfterReturning(pointcut = "callAtLogin()", returning = "userDetails")
    public void afterCallMethodSaveFile(JoinPoint jp, UserDetails userDetails) {
        log.debug("login email: {}", userDetails.getUsername());
        //todo : spring AOP not working with FeignClient client
        //mailServiceClient.sendLoginMessage(jp.getArgs()[0].toString());
    }
}
