package group.msg.training.school.controllers.rest;

import group.msg.training.school.util.CsvConverter;
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
		return type instanceof ParameterizedType && ((ParameterizedType) type).getRawType().equals(List.class) &&
				canRead(mediaType);
	}

	@Override
	public boolean canWrite(Type type, Class<?> clazz, MediaType mediaType) {
		return type instanceof ParameterizedType && ((ParameterizedType) type).getRawType().equals(List.class) &&
				canWrite(mediaType);
    }

    @Override
    protected void writeInternal(List<Object> objects, Type type, HttpOutputMessage httpOutputMessage) throws IOException {
        converter.write(extractPojoClassFromType(type), objects, httpOutputMessage.getBody());
    }

    @Override
    protected List<Object> readInternal(Class<? extends List<Object>> aClass, HttpInputMessage httpInputMessage) throws IOException {
        return read(aClass, null, httpInputMessage);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Object> read(Type type, Class<?> aClass, HttpInputMessage httpInputMessage) throws IOException {
        return (List<Object>)converter.read(extractPojoClassFromType(type), httpInputMessage.getBody());
    }

    private Class<?> extractPojoClassFromType(Type type) {
        return (Class<?>) ((ParameterizedType) type).getActualTypeArguments()[0];
    }
}
