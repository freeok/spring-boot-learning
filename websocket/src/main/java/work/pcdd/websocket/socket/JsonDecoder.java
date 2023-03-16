package work.pcdd.websocket.socket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.websocket.Decoder;
import jakarta.websocket.EndpointConfig;
import lombok.extern.slf4j.Slf4j;

/**
 * Websocket解码器
 *
 * @author pcdd
 * @date 2022/11/18 18:55
 */
@Slf4j
public class JsonDecoder implements Decoder.Text<MessageEntity> {

    @Override
    public MessageEntity decode(String s) {
        log.info("s = {}", s);
        ObjectMapper mapper = new ObjectMapper();
        MessageEntity obj = null;
        try {
            obj = mapper.readValue(s, MessageEntity.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return obj;
    }

    @Override
    public boolean willDecode(String s) {
        return (s != null);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {
    }

    @Override
    public void destroy() {
    }

}
