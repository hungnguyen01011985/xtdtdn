package vn.toancauxanh.model.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomDateSerializer extends StdSerializer<Date>{

    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

    public CustomDateSerializer() {
        this(null);
    }

    public CustomDateSerializer(Class t) {
        super(t);
    }

    @Override
    public void serialize(Date date, JsonGenerator generator, SerializerProvider provider) 
        throws IOException, JsonProcessingException {

        generator.writeString(formatter.format(date));
    }
}
