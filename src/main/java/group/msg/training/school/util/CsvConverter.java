package group.msg.training.school.util;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

public class CsvConverter {
    private final CsvMapper mapper = new CsvMapper();

    public <T> List<T> read(Class<T> clazz, InputStream stream) throws IOException {
        CsvSchema schema = mapper.schemaFor(clazz).withHeader().withColumnReordering(true);
        ObjectReader reader = mapper.readerFor(clazz).with(schema);
        return reader.<T>readValues(stream).readAll();
    }

    public void write(Class<?> clazz, List<Object> data, OutputStream stream) throws IOException {
        CsvSchema schema = mapper.schemaFor(clazz).withHeader().withColumnReordering(true);
        ObjectWriter writer = mapper.writerFor(clazz).with(schema);
        writer.writeValues(stream).writeAll(data);
    }

}
