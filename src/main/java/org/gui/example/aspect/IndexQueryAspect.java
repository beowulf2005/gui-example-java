package org.gui.example.aspect;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.gui.example.ann.ESQueryParam;
import org.gui.example.ann.ESQueryTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import org.gui.example.entity.base.Data;
import org.gui.example.service.ESIndexService;

@Component
@Aspect
public class IndexQueryAspect {

    private static final Logger LOG = LoggerFactory.getLogger(IndexQueryAspect.class);

    @Inject
    private ESIndexService esIndexService;

    @Around(value = "@annotation(template) && execution(* *..*(..))")
    public Object query(ProceedingJoinPoint jp, ESQueryTemplate template) throws Throwable {
        LOG.debug("{}", jp.getThis());
        // check query
        final String query = template.value();
        if (StringUtils.isBlank(query)) {
            return null;
        }

        // check query type
        final String[] types = checkTypes(template);
        if (types == null) {
            return null;
        }

        // check args, jp.getArgs() is never null
        final Object[] args = jp.getArgs();
        // if we can not find method declaration, return null
        final Annotation[][] annotations = findAnnotations(jp);
        if (annotations == null) {
            return null;
        }

        final Map<String, Object> vars = seekAnnotedParams(args, annotations);
        Object res = null;
        try {
            res = esIndexService.query(vars, query, types);
        } catch (Exception e) {
            LOG.error("failed to query {}", jp.getSignature(), e);
        }
        return res;
    }

    private static Annotation[][] findAnnotations(ProceedingJoinPoint jp) {
        final MethodSignature signature = (MethodSignature) jp.getSignature();
        final String methodName = signature.getMethod().getName();
        final Class<?>[] parameterTypes = signature.getMethod().getParameterTypes();
        Annotation[][] annotations;
        try {
            annotations = jp.getTarget().getClass().getMethod(methodName, parameterTypes).getParameterAnnotations();
        } catch (Exception e) {
            return null;
        }
        return annotations;
    }

    private static Map<String, Object> seekAnnotedParams(final Object[] args, Annotation[][] annotations) {
        final Map<String, Object> vars = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            final Object param = args[i];
            if (param == null) {
                continue;
            }
            final ESQueryParam paramAnn = findAnn(annotations[i], ESQueryParam.class);
            if (paramAnn == null) {
                continue;
            }
            final String name = paramAnn.value();
            if (name == null || name.isEmpty()) {
                continue;
            }
            vars.put(name, param);
        }
        return vars;
    }

    private static String[] checkTypes(ESQueryTemplate template) {
        Class<? extends Data>[] clazz = template.types();
        if (clazz.length == 0) {
            return null;
        }
        String[] types = new String[clazz.length];
        for (int i = 0; i < clazz.length; i++) {
            types[i] = clazz[i].getSimpleName();
        }
        return types;
    }

    @SuppressWarnings("unchecked")
    private static final <T extends Annotation> T findAnn(Annotation[] annotations, Class<T> annType) {
        for (Annotation ann : annotations) {
            if (ann.annotationType() == annType) {
                return (T) ann;
            }
        }
        return null;
    }

}
