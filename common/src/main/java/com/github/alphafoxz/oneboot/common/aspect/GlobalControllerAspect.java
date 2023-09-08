package com.github.alphafoxz.oneboot.common.aspect;

import com.github.alphafoxz.oneboot.common.toolkit.coding.CollUtil;
import com.github.alphafoxz.oneboot.common.toolkit.coding.StrUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Aspect
@Component
public class GlobalControllerAspect {
    @Pointcut("execution(* com.github.alphafoxz.oneboot.*.controller.*.*(..))")
    public void controller() {
    }

    @Around("controller()")
    public Object postAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (servletRequestAttributes == null) {
            return joinPoint.proceed(args);
        }
        HttpServletRequest request = servletRequestAttributes.getRequest();
        if (!CollUtil.contains(CollUtil.newArrayList(HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.PATCH.name()), request.getMethod())) {
            return joinPoint.proceed(args);
        }
        /*
         HACK post参数只能有一个，并且当其是字符串的时候外边会多一层"包装，所以需要要解包装。如："\"一个字符串\\n和换行\"" -> "一个字符串\n和换行"
         虽然正常情况post传参应该都是对象，但是为了不影响开发作此处理，也许应该在设计的时候就禁止post传String会更好？
         */
        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof String str) {
                args[i] = StrUtil.unWrap(str, '"');
            }
        }
        return joinPoint.proceed(args);
    }
}
