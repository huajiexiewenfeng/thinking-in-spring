package com.huajie.thinking.in.spring.conversion;

import com.huajie.thinking.in.spring.conversion.domain.Company;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.ConditionalGenericConverter;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * {@link Company} -> String {@link ConditionalGenericConverter} 实现
 *
 * @author ：xwf
 * @date ：Created in 2020\6\14 0014 7:12
 */
public class CompanyToStringConverter implements ConditionalGenericConverter {
    @Override
    public boolean matches(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return Company.class.equals(sourceType.getObjectType()) &&
                String.class.equals(targetType.getObjectType());
    }

    @Override
    public Set<ConvertiblePair> getConvertibleTypes() {
        return Collections.singleton(new ConvertiblePair(Company.class, String.class));
    }

    @Override
    public Object convert(Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        Company company = Company.class.cast(source);
        return company.getName() + "-" + company.getAddress();
    }
}
