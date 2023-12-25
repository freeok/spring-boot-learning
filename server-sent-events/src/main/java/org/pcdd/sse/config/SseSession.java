package org.pcdd.sse.config;

import lombok.SneakyThrows;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author pcdd
 */
public class SseSession {

    private static final Map<String, SseEmitter> SSE_SESSION_CACHE = new ConcurrentHashMap<>();

    private SseSession() {
    }

    public static int getSize() {
        return SSE_SESSION_CACHE.size();
    }

    public static void add(String sessionKey, SseEmitter sseEmitter) {
        if (!exists(sessionKey)) {
            SSE_SESSION_CACHE.put(sessionKey, sseEmitter);
        }
    }

    public static void remove(String sessionKey) {
        SseEmitter sseEmitter = SSE_SESSION_CACHE.get(sessionKey);
        if (sseEmitter != null) {
            sseEmitter.complete();
            SSE_SESSION_CACHE.remove(sessionKey);
        }
    }

    public static boolean exists(String sessionKey) {
        return SSE_SESSION_CACHE.containsKey(sessionKey);
    }

    public static void onError(String sessionKey, Throwable throwable) {
        SseEmitter sseEmitter = SSE_SESSION_CACHE.get(sessionKey);
        if (sseEmitter != null) {
            sseEmitter.completeWithError(throwable);
        }
    }

    @SneakyThrows
    public static void send(String sessionKey, String content) {
        if (!exists(sessionKey)) {
            return;
        }
        SseEmitter sseEmitter = SSE_SESSION_CACHE.get(sessionKey);
        sseEmitter.send(content);
    }

}


