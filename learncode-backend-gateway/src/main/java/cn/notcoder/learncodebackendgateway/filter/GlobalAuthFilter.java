package cn.notcoder.learncodebackendgateway.filter;

import com.alibaba.nacos.common.packagescan.resource.AntPathMatcher;
import com.alibaba.nacos.shaded.io.grpc.internal.JsonUtil;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

@Component
public class GlobalAuthFilter implements GlobalFilter {

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getURI().getPath();
        if (antPathMatcher.match("/**/inner/**", path)) {
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.FORBIDDEN);
            response.getHeaders().add("content-type", "application/json");
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            DataBuffer dataBuffer = dataBufferFactory.wrap(
                    "{\"code\":40101,\"data\":null,\"message\":\"无权限\"}"
                            .getBytes(StandardCharsets.UTF_8)
            );
            return response.writeWith(Mono.just(dataBuffer));
        }
        return chain.filter(exchange);
    }
}
