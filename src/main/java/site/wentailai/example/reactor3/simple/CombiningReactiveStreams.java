package site.wentailai.example.reactor3.simple;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * User: libingkui
 * Date: 2020/6/6
 * Description:
 */
public class CombiningReactiveStreams {
    public void concat() {
        Flux.concat(
            Flux.just(1, 2, 3),
            Flux.just(4, 5),
            Flux.just(6, 7, 8)
        )
            .subscribe(System.out::println);
    }
    public void merge() {
        Flux.merge(
            Flux.just(1, 2, 3),
            Flux.just(4, 5),
            Flux.just(6, 7, 8)
        )
            .subscribe(System.out::println);
    }
    public void zip() {
        Flux.zip(
                Flux.just(1, 2, 3),
                Flux.just(4, 5),
                Flux.just(6, 7, 8)
        )
                .subscribe(System.out::println);
    }
    public <T> void combineLatest() {
        Flux.combineLatest(
                Flux.interval(Duration.ofMillis(50)),
                Flux.interval(Duration.ofMillis(80)),
                (a, b) -> {
                    System.out.println("最新的两个元素" + a + "和" + b);
                    return a * b;
                }
        )
                .subscribe(System.out::println);
    }

    public static void main(String[] args) throws InterruptedException {
        CombiningReactiveStreams c = new CombiningReactiveStreams();
        System.out.println("concat:");
        c.concat();
        System.out.println("merge:");
        c.merge();
        System.out.println("zip:");
        c.zip();
        System.out.println("combine latest:");
        c.combineLatest();

        TimeUnit.SECONDS.sleep(1);
    }
}






















