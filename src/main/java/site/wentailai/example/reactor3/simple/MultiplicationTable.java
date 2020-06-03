package site.wentailai.example.reactor3.simple;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

/**
 * User: libingkui
 * Date: 2020/6/3
 * Description:
 */
@Slf4j
public class MultiplicationTable {
    public Flux<String> getMultiplicationTable() {
        return Flux.range(1, 9)
                .flatMap(x -> Flux.range(1, x)
                        .reduce("", (a, i) -> a + i + " * " + x + " = " + x * i + " ")
                );
    }

    public static void main(String[] args) {
        MultiplicationTable m = new MultiplicationTable();
        m.getMultiplicationTable()
                .subscribe(log::debug);
    }
}
