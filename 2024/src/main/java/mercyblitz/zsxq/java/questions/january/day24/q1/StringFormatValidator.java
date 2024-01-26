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
package mercyblitz.zsxq.java.questions.january.day24.q1;

import org.hibernate.validator.internal.engine.constraintvalidation.ConstraintValidatorContextImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Path;
import java.lang.reflect.Field;

import static mercyblitz.zsxq.java.questions.january.day24.q1.bean.validation.ThreadLocalBeanValidationInterceptor.getValidatedBean;
import static org.apache.commons.lang3.reflect.FieldUtils.getField;
import static org.apache.commons.lang3.reflect.FieldUtils.readField;

/**
 * {@link StringFormat} Validator
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy<a/>
 * @see StringFormat
 * @since 1.0.0
 */
public class StringFormatValidator implements ConstraintValidator<StringFormat, String> {

    private static final Logger logger = LoggerFactory.getLogger(StringFormatValidator.class);

    private StringFormat stringFormat;

    @Override
    public void initialize(StringFormat stringFormat) {
        this.stringFormat = stringFormat;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (context instanceof ConstraintValidatorContextImpl) {
            ConstraintValidatorContextImpl contextImpl = (ConstraintValidatorContextImpl) context;
            try {
                Object fieldValue = readField(contextImpl, "basePath", true);
                Path path = (Path) fieldValue;
                Object validatedBean = getValidatedBean();
                if (validatedBean != null) {
                    Class<?> beanClass = validatedBean.getClass();
                    String propertyName = path.toString();
                    Field field = getField(beanClass, propertyName, true);
                    DataInfo dataInfo = field.getAnnotation(DataInfo.class);
                    logger.info("Bean[class : {}] 属性字段[name : {}] 标注的 @DataInfo value : {}", beanClass.getName(), propertyName, dataInfo.value());
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

        return true;
    }
}
