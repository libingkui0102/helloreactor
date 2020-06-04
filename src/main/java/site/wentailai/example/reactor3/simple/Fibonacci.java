package site.wentailai.example.reactor3.simple;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

/**
 * User: libingkui
 * Date: 2020/6/4
 * Description:
 */

public class Fibonacci {
    public Flux<Integer> getFibonacciSequence() {
        return Flux.generate(
                () -> Tuples.of(1, 1),
                (state, sink) -> {
                    sink.next(state.getT1());
                    return Tuples.of(state.getT2(), state.getT1() + state.getT2());
                }
        );
    }

    public static void main(String[] args) {
        Fibonacci f = new Fibonacci();
        f.getFibonacciSequence()
                .take(11)
                .subscribe(System.out::println);
    }
}
