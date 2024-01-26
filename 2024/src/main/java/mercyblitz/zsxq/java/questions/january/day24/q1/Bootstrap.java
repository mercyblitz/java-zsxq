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

import io.microsphere.bean.validation.InterceptingValidationProviderResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * 引导类
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy<a/>
 * @see CashIncomeReportExcelData
 * @see DataInfo
 * @see StringFormat
 * @see StringFormatValidator
 * @since 1.0.0
 */
public class Bootstrap {

    private static final Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    public static void main(String[] args) {
        ValidatorFactory validatorFactory = Validation.byDefaultProvider()
                .providerResolver(new InterceptingValidationProviderResolver())
                .configure()
                .buildValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<CashIncomeReportExcelData>> violations = validator.validate(new CashIncomeReportExcelData());
        violations.forEach(i -> logger.info(i.getMessage()));
    }
}
