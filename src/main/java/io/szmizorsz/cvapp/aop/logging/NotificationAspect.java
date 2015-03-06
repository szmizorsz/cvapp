package io.szmizorsz.cvapp.aop.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.context.Context;
import org.thymeleaf.context.IContext;
import org.thymeleaf.spring4.SpringTemplateEngine;

import io.szmizorsz.cvapp.service.MailService;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

/**
 * Aspect for notification on domain entity changes
 */
@Aspect
public class NotificationAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    
    @Inject
    private MailService mailService;
    
    @Inject
    private SpringTemplateEngine templateEngine;
    
    private static final String POINTCUT_LITERAL = "execution(* io.szmizorsz.cvapp.web.rest.CompanyResource.create(..)) || execution(* io.szmizorsz.cvapp.web.rest.CompanyResource.delete(..)) || " +
    		"execution(* io.szmizorsz.cvapp.web.rest.ProjectResource.create(..)) || execution(* io.szmizorsz.cvapp.web.rest.ProjectResource.delete(..)) || " +
    		"execution(* io.szmizorsz.cvapp.web.rest.TechnologyResource.create(..)) || execution(* io.szmizorsz.cvapp.web.rest.TechnologyResource.delete(..)) || " +
    		"execution(* io.szmizorsz.cvapp.web.rest.EducationResource.create(..)) || execution(* io.szmizorsz.cvapp.web.rest.EducationResource.delete(..)) || " +
    		"execution(* io.szmizorsz.cvapp.web.rest.KnowledgeResource.create(..)) || execution(* io.szmizorsz.cvapp.web.rest.KnowledgeResource.delete(..)) || " +
    		"execution(* io.szmizorsz.cvapp.web.rest.LanguageResource.create(..)) || execution(* io.szmizorsz.cvapp.web.rest.LanguageResource.delete(..)) || " +
    		"execution(* io.szmizorsz.cvapp.web.rest.OtherResource.create(..)) || execution(* io.szmizorsz.cvapp.web.rest.OtherResource.delete(..))";

    @Pointcut(POINTCUT_LITERAL)
    public void notificationPoincut() {}

    @Around("notificationPoincut()")
    public Object notifyAround(ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            Object result = joinPoint.proceed();
                
            Map<String, Object> variables = new HashMap<>();
            variables.put("resource", joinPoint.getSignature().getDeclaringTypeName());
            variables.put("method", joinPoint.getSignature().getName());
            variables.put("data", Arrays.toString(joinPoint.getArgs()));
            IContext context = new Context(Locale.ENGLISH, variables);
            String dataChangeNotificationEmailContent = templateEngine.process("dataChangeNotification", context);
            mailService.sendDataChangeNotificationEmail(dataChangeNotificationEmailContent, Locale.ENGLISH);
                
            return result;
        } catch (IllegalArgumentException e) {
            log.error("Illegal argument: {} in {}.{}()", Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());

            throw e;
        }
    }
}
