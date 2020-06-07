package site.wentailai.example.reactor3.simple;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.stream.IntStream;

/**
 * User: libingkui
 * Date: 2020/6/6
 * Description:
 */
@Slf4j
public class GeneratePushAndCreate {

    static Consumer<List<Integer>> consumer;
    static Consumer<List<Integer>> consumer2;

    public static void main(String[] args) throws InterruptedException {
        Flux.generate(
                () -> Tuples.of(0, 1),
                (state, sink) -> {
                    sink.next(state.getT1());
                    return Tuples.of(state.getT2(), state.getT1() + state.getT2());
                }
            )
            .take(8)
            .subscribe( x -> log.debug("(generate example)Fibanocci: {}", x));
        Flux.push(e -> {
            IntStream.range(1, 10)
                    .forEach(e::next);
            })
            .delayElements(Duration.ofMillis(1))
            .subscribe(e -> log.debug("(push example)onNext: {}", e));


        Flux.create(
                sink -> {
                    consumer = items -> items.forEach(sink::next);
                    consumer2 = items -> items.forEach(sink::next);
                }
            )
            .subscribe(x -> log.debug("(create example) {}", x));

        Thread thread1 = new Thread(
                () -> consumer.accept(Arrays.asList(1, 2, 3))
        );
        Thread thread2 = new Thread(
                () -> consumer2.accept(Arrays.asList(4, 5, 6))
        );
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        TimeUnit.SECONDS.sleep(2);
    }
}
