package com.reactive;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

import java.time.Duration;

@SpringBootTest
class ReactiveApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void workingWithMono() throws InterruptedException {

        // Mono -> publisher that have 0...1 items
        // created mono
//		Mono<String> errorMono = Mono.error(new RuntimeException("Error !!"));
//
//		Mono<String> m1 = Mono
//				.just("Learning about reactive")
//				.log()
//				.then(errorMono);
//
//
//		// consume the mono by subcribing
//		m1.subscribe(System.out::println);
//		errorMono.subscribe(System.out::println);


        Mono<String> m1 = Mono.just("Learn Code With Durgesh");
        Mono<String> m2 = Mono.just("Subscribe this channel");
        Mono<Integer> m3 = Mono.just(13213245);

		Flux<String> stringFlux = m1.concatWith(m2).log().delayElements(Duration.ofMillis(2000));

		stringFlux.subscribe((data -> {
			System.out.println(Thread.currentThread().getName());
			System.out.println(data);
		}));

		Thread.sleep(4000);
		System.out.println("main thread is terminated...");
//        Mono<String> resultMapMono = m1.map(value -> value.toUpperCase());
//        resultMapMono.subscribe(System.out::println);
//
//		System.out.println("-----------------------------------");
//
//        Mono<String[]> resultFlatExample = m1.flatMap(valueM1 -> Mono.just(valueM1.split(" ")));
//        resultFlatExample.subscribe(data -> {
//            for (String s : data) {
//                System.out.println(s);
//            }
//        });
//
//		System.out.println("-----------------------------------");
//
//		Flux<String> stringFlux = m1.flatMapMany(valueM1 -> Flux.just(valueM1.split(" ")).log());
//		stringFlux.subscribe(data -> {
//			System.out.println(data);
//		});

//		Mono<Tuple3<String, String, Integer>> combineMono = Mono.zip(m1, m2, m3);
//
//		combineMono.subscribe(data -> {
//			System.out.println(data.getT1());
//			System.out.println(data.getT2());
//			System.out.println(data.getT3());
//		});
//
//		Mono<Tuple2<String, String>> zipWithMono = m1.zipWith(m2);
//		zipWithMono.subscribe(data -> {
//			System.out.println(data.getT1());
//			System.out.println(data.getT2());
//		});


    }

}
