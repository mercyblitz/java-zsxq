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
package mercyblitz.zsxq.java.questions.match.day11.q1;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;

import static org.springframework.beans.factory.support.BeanDefinitionBuilder.genericBeanDefinition;

/**
 * 动态 Spring Batch Job 示例
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @since Job
 */
public class DynamicJobsSample {

    static JobBuilderFactory jobBuilderFactory = getJobBuilderFactory();
    ;

    public static void main(String[] args) {
        for (int i = 0; i < 12; i++) {
            /**
             * <prev>
             * @Bean
             * public Job mergeDataJob() {
             *     return this.jobBuilderFactory.get("mergeDataJob")
             *                      .start(mergeData())
             *                      .build();
             * }
             * public Job
             *</prev>
             */
            genericBeanDefinition(Job.class, DynamicJobsSample::mergeDataJob);
        }
    }

    private static Job mergeDataJob() {
        return jobBuilderFactory.get("mergeDataJob")
                .start(mergeData())
                .build();
    }

    private static Step mergeData() {
        return null;
    }

    private static JobBuilderFactory getJobBuilderFactory() {
        // TODO
        return null;
    }
}
