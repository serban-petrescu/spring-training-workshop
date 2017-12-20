package group.msg.training.school.controllers;

import group.msg.training.school.util.CsvConverter;
import org.apache.commons.lang.NotImplementedException;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

@Component
public class CsvMessageConverter extends AbstractGenericHttpMessageConverter<List<Object>> {
    private final CsvConverter converter = new CsvConverter();

    public CsvMessageConverter() {
        super(MediaType.parseMediaType("text/csv"));
    }

    @Override
    public boolean canRead(Type type, Class<?> contextClass, MediaType mediaType) {
        return type instanceof ParameterizedType && ((ParameterizedType) type).getRawType().equals(List.class) && canRead(mediaType);
    }

    @Override
    protected void writeInternal(List<Object> objects, Type type, HttpOutputMessage httpOutputMessage) throws IOException {
        throw new NotImplementedException();
    }

    @Override
    protected List<Object> readInternal(Class<? extends List<Object>> aClass, HttpInputMessage httpInputMessage) throws IOException {
        return read(aClass, null, httpInputMessage);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Object> read(Type type, Class<?> aClass, HttpInputMessage httpInputMessage) throws IOException {
        Class<?> pojo = (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
        return (List<Object>)converter.read(pojo, httpInputMessage.getBody());
    }
}
