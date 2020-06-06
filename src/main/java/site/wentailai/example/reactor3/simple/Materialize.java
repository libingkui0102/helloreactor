package site.wentailai.example.reactor3.simple;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * User: libingkui
 * Date: 2020/6/6
 * Description:
 */
@Slf4j
public class Materialize {
    public static void main(String[] args) {
        Flux.range(1, 10)
                .doOnNext(x -> log.debug("data : {}", x))
                .materialize()
                .doOnNext(x -> log.debug("signal: {}", x))
                .dematerialize()
                .collectList()
                .subscribe(x -> log.debug("result: {}", x));
    }
}
