# 2024 年 1 月 24 日 问题汇总

## Q1

### 问题描述

StringFormatValidator#isValid 方法中，想获取 CashIncomeReportExcelData.statisticMonth 上
@DataInfo注解的信息。有什么方式可以获取到吗？

```java
public class CashIncomeReportExcelData {
    @DataInfo("中文描述")
    @StringFormat(targetClass = Integer.class)
    private String statisticMonth;

}

public class StringFormatValidator implements ConstraintValidator<StringFormat, String> {
    private Class<?> targetClass;
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
    }
    ...
}
```