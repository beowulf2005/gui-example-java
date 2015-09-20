package org.gui.example.aspect;

import javax.inject.Inject;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import org.gui.example.ann.IndexRemove;
import org.gui.example.entity.base.Data;
import org.gui.example.service.ESIndexService;

@Component
@Aspect
public class IndexRemoveAspect extends AnnTypeAwareAspect {

    private static final Logger LOG = LoggerFactory.getLogger(IndexRemoveAspect.class);

    @Inject
    private ESIndexService esIndexService;

    @Around(value = "@annotation(ann) && execution(* *..*(..))")
    public Object remove(ProceedingJoinPoint joinPoint, IndexRemove ann) throws Throwable {
        LOG.debug("{}", joinPoint.getThis());
        return processArgsAfterRunMethod(joinPoint, ann.value());
    }

    protected boolean work(final Class<?> clazz, Object obj, ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            esIndexService.remove(clazz.getSimpleName(), ((Data) obj).getId());
        } catch (Exception e) {
            LOG.error("failed to remove object {}", joinPoint.getSignature(), e);
        }
        return true;
    }

}
