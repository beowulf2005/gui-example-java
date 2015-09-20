package org.gui.example.aspect;

import javax.inject.Inject;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import org.gui.example.ann.IndexUpdate;
import org.gui.example.entity.base.Data;
import org.gui.example.service.ESIndexService;

@Component
@Aspect
public class IndexUpdateAspect extends AnnTypeAwareAspect {

    private static final Logger LOG = LoggerFactory.getLogger(IndexUpdateAspect.class);

    @Inject
    private ESIndexService esIndexService;

    @Around(value = "@annotation(ann) && execution(* *..*(..))")
    public Object update(ProceedingJoinPoint joinPoint, IndexUpdate ann) throws Throwable {
        LOG.debug("{}", joinPoint.getThis());
        return processArgsAfterRunMethod(joinPoint, ann.value());
    }

    protected boolean work(final Class<?> clazz, Object obj, ProceedingJoinPoint joinPoint) throws Throwable {
        try {
            esIndexService.index(clazz.getSimpleName(), (Data) obj);
        } catch (Exception e) {
            LOG.error("failed to update object {}", joinPoint.getSignature(), e);
        }
        return true;
    }
}
