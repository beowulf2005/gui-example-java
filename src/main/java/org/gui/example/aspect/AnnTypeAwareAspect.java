package org.gui.example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;

public abstract class AnnTypeAwareAspect {

    protected Object processArgsAfterRunMethod(ProceedingJoinPoint joinPoint, final Class<?> clazz) throws Throwable {
        final Object[] args = joinPoint.getArgs();
        Object result = runMethod(joinPoint, args);
        if (clazz != null && args != null) {
            for (Object obj : args) {
                if (clazz.isInstance(obj)) {
                    final boolean next = work(clazz, obj, joinPoint);
                    if (!next) {
                        break;
                    }
                }
            }
        }
        return result;
    }

    private Object runMethod(ProceedingJoinPoint joinPoint, Object[] args) throws Throwable {
        Object result;
        if (args == null || args.length == 0) {
            result = joinPoint.proceed();
        } else {
            result = joinPoint.proceed(args);
        }
        return result;
    }

    abstract protected boolean work(final Class<?> clazz, Object obj, ProceedingJoinPoint joinPoint) throws Throwable;
}
