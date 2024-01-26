/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mercyblitz.zsxq.java.questions.january.day24.q1.bean.validation;

import io.microsphere.bean.validation.ValidationInterceptor;

import javax.validation.ConstraintViolation;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static java.lang.ThreadLocal.withInitial;

/**
 * {@link Thread-Local} 保存校验 Bean 拦截器
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy<a/>
 * @see ValidationInterceptor
 * @since 1.0.0
 */
public class ThreadLocalBeanValidationInterceptor implements ValidationInterceptor {

    private static final ThreadLocal<List<Object>> validatedBeansHolder = withInitial(LinkedList::new);

    @Override
    public <T> void beforeValidate(T object, Class<?>... groups) {
        bind(object);
    }

    @Override
    public <T> void afterValidate(T object, Set<ConstraintViolation<T>> constraintViolations, Throwable error, Class<?>... groups) {
        unbind(object);
    }

    // Spring Validation 使用是以下方法
    @Override
    public <T> void beforeValidateParameters(T object, Method method, Object[] parameterValues, Class<?>... groups) {
        bind(object);
    }

    @Override
    public <T> void afterValidateParameters(T object, Method method, Object[] parameterValues, Set<ConstraintViolation<T>> constraintViolations, Throwable error, Class<?>... groups) {
        unbind(object);
    }

    private static void bind(Object object) {
        List<Object> validateBeans = validatedBeansHolder.get();
        validateBeans.add(object);
    }

    private static void unbind(Object object) {
        List<Object> validateBeans = validatedBeansHolder.get();
        validateBeans.remove(object);
    }

    public static Object getValidatedBean() {
        List<Object> validateBeans = validatedBeansHolder.get();
        int size = validateBeans.size();
        if (size < 1) {
            return null;
        }
        return validateBeans.get(size - 1);
    }
}
