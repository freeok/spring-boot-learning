package org.pcdd.sse.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.pcdd.sse.config.SseSession;
import org.pcdd.sse.dto.SseDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @author pcdd
 */
@Slf4j
@RestController
@RequestMapping("/sse")
public class SseController {

    @GetMapping("/open/{clientId}")
    public SseEmitter open(@PathVariable("clientId") String clientId) {
        log.info("SSE open, clientId = {}", clientId);
        SseEmitter sseEmitter = new SseEmitter(0L);
        sseEmitter.onError(err -> {
            log.error("SseSession Error, msg: {} clientId: {}", err.getMessage(), clientId);
            SseSession.onError(clientId, err);
        });
        sseEmitter.onTimeout(() -> {
            log.info("SseSession Timeout, clientId: {}", clientId);
            SseSession.remove(clientId);
        });
        sseEmitter.onCompletion(() -> {
            log.info("SseSession Completion, clientId: {}", clientId);
            SseSession.remove(clientId);
        });
        SseSession.add(clientId, sseEmitter);
        return sseEmitter;
    }

    @GetMapping("/close")
    public void close(@RequestParam("clientId") String clientId) {
        log.info("SseSession Close, clientId: {}", clientId);
        SseSession.remove(clientId);
    }

    @PostMapping("/send")
    public String sendMessage(@RequestBody SseDTO sseDTO) {
        String clientId = sseDTO.getClientId();
        SseSession.send(clientId, sseDTO.getData());
        return "OK";
    }

    @GetMapping("/test")
    @SneakyThrows
    public void likeChatGPT(@RequestParam String clientId) {
        Queue<String> queue = new ArrayDeque<>();
        queue.add("Server-Sent Events（SSE）是一种用于实现服务器向客户端实时推送数据的Web技术。与传统的轮询和长轮询相比，SSE提供了更高效和实时的数据推送机制。\n");
        queue.add("SE基于HTTP协议，允许服务器将数据以事件流（Event Stream）的形式发送给客户端。客户端通过建立持久的HTTP连接，并监听事件流，可以实时接收服务器推送的数据。\n");
        queue.add("SSE的主要特点包括：简单易用：SSE使用基于文本的数据格式，如纯文本、JSON等，使得数据的发送和解析都相对简单。\n");
        queue.add("单向通信：SSE支持服务器向客户端的单向通信，服务器可以主动推送数据给客户端，而客户端只能接收数据。\n");
        queue.add("实时性：SSE建立长时间的连接，使得服务器可以实时地将数据推送给客户端，而无需客户端频繁地发起请求。\n");

        while (!queue.isEmpty()) {
            SseDTO sseDTO = new SseDTO();
            sseDTO.setClientId(clientId);
            sseDTO.setData(queue.poll());
            SseSession.send(sseDTO.getClientId(), sseDTO.getData());
            Thread.sleep(200);
        }
    }

}


