package work.pcdd.websocket.socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import jakarta.websocket.EndpointConfig;

/**
 * Websocket编码器
 *
 * @author pcdd
 * @date 2021/8/29 0:38
 */
public class JsonEncoder implements Encoder.Text<MessageEntity> {

    @Override
    public void init(EndpointConfig endpointConfig) {
    }

    @Override
    public String encode(MessageEntity entity) throws EncodeException {
        ObjectMapper mapper = new ObjectMapper();
        String json = "";
        try {
            json = mapper.writeValueAsString(entity);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    @Override
    public void destroy() {
    }

}
