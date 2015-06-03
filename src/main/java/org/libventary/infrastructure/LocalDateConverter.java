package org.libventary.infrastructure;

import java.time.LocalDate;

import com.thoughtworks.xstream.converters.SingleValueConverterWrapper;
import com.thoughtworks.xstream.converters.basic.AbstractSingleValueConverter;


public class LocalDateConverter extends AbstractSingleValueConverter {

    @Override
    public boolean canConvert(Class _class) {
        return _class.equals(LocalDate.class);
    }

    @Override
    public Object fromString(String str) {
        return LocalDate.parse(str);
    }

}
