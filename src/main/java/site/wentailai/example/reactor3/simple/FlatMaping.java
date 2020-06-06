package site.wentailai.example.reactor3.simple;

import lombok.AllArgsConstructor;
import lombok.Data;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * User: libingkui
 * Date: 2020/6/6
 * Description:
 */
public class FlatMaping {
    public Flux<User> getUsers(String name) {
        Random random = new Random();
        return Flux.range(1,  10)
                .map(x -> new User("user" + x, 20 + random.nextInt(9)))
                .filter(x -> x.getName().equals(name))
                .delayElements(Duration.ofMillis(20));
    }

    public Flux<User> getSpecificUsersByName(String... userName) {
        return Flux.fromArray(userName)
                .flatMap(x -> getUsers(x));
    }

    public static void main(String[] args) throws InterruptedException {
        FlatMaping f = new FlatMaping();
        f.getSpecificUsersByName("user1", "user2", "user3")
                .subscribe(System.out::println);
        TimeUnit.SECONDS.sleep(2);
    }

    @Data
    @AllArgsConstructor
    public static class User{
        private String name;
        private int age;
    }



}
