package site.wentailai.example.reactor3.simple;

import reactor.core.publisher.Flux;

import java.util.LinkedList;

/**
 * User: libingkui
 * Date: 2020/6/6
 * Description:
 */
public class BatchProcessing {
    public void buffer() {
        Flux.range(1, 108)
                .buffer(10)
                .subscribe(System.out::println);
    }

    public void window() {
        Flux.range(1, 108)
                .windowUntil(x -> x % 10 == 0 && x > 40)
                .map(x -> x.collectList())
                .subscribe(x -> x.subscribe(System.out::println));
    }
    public void group() {
        Flux.range(1, 108)
                .groupBy(x -> x > 50 ? "大于50" : "小于50")
                .subscribe(
                    x -> {
                        x.reduce(
                            new LinkedList<>(),
                                (list, e) -> {
                                    list.add(e);
                                    return list;
                                }
                        )
                        .subscribe(
                                data -> System.out.println(x.key() + "的数据:" + data)
                        )
                        ;
                    }
                );

    }
    public static void main(String[] args) {
        BatchProcessing b = new BatchProcessing();
        b.buffer();
        b.window();
        b.group();
    }
}
