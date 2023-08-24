package com.test.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class TestFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain){
        ServerHttpRequest request = exchange.getRequest(); //打印请求参数
        System.out.println(request.getQueryParams()); //判断是否包含test参数，且参数值为1
        List<String> value = request.getQueryParams().get("test");
        if(value != null && !value.contains("1")) {
            //将ServerWebExchange向过滤链的下一级传递
            return chain.filter(exchange);
        }else {
            //直接在这里不再向下传递，然后返回响应
            return exchange.getResponse().setComplete();
        }
    }
}
