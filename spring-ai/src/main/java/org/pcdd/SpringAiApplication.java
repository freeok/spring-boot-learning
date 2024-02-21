package org.pcdd;

import org.springframework.ai.chat.ChatResponse;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.Map;

/**
 * @author pcdd
 */
@RestController
@SpringBootApplication
public class SpringAiApplication {

    public static void main(String[] args) {
        // 若 base-url 为 https://api.openai.com/ 需启用以下代码
        /* System.setProperty("http.proxyHost","127.0.0.1");
        // 修改为你代理软件的端口
        System.setProperty("http.proxyPort","10809");
        System.setProperty("https.proxyHost","127.0.0.1");
        System.setProperty("https.proxyPort","10809"); */
        SpringApplication.run(SpringAiApplication.class, args);
    }

    @Autowired
    OpenAiChatClient chatClient;

    @GetMapping("/ai/generate")
    public Map generate(@RequestParam(value = "msg", defaultValue = "给我讲个笑话") String msg) {
        return Map.of("generation", chatClient.call(msg));
    }

    @GetMapping("/ai/generateStream")
    public Flux<ChatResponse> generateStream(@RequestParam(value = "msg", defaultValue = "给我讲个笑话") String msg) {
        Prompt prompt = new Prompt(new UserMessage(msg));
        return chatClient.stream(prompt);
    }

}
