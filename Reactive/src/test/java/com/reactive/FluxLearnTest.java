package com.reactive;

import com.reactive.service.FluxLearnService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

@SpringBootTest
public class FluxLearnTest {

    @Autowired
    private FluxLearnService fluxLearnService;

//    @Test
//    void testing(){
//        this.fluxLearnService.fluxTestingService();
//    }

//    @Test
//    public void simpleFluxTest(){

//        fluxLearnService.getFlux().subscribe(data -> {
//            System.out.println(data);
//            System.out.println("done with flux data");
//        });

//        fluxLearnService.fruitsFlux().subscribe(System.out::println);

//        fluxLearnService.mapExampleFlux().subscribe(System.out::println);

//        Flux<String> capFlux = fluxLearnService.mapExampleFlux();
//        StepVerifier.create(capFlux)
//                .expectNext("Gautam".toUpperCase(), "abhay".toUpperCase(), "Parth".toUpperCase(), "Dev".toUpperCase(), "Sunny".toUpperCase())
//                .verifyComplete();

//        Flux<String> filterFlux = fluxLearnService.filterExampleFlux();
//        StepVerifier.create(filterFlux)
//                .expectNextCount(4)
//                .verifyComplete();
//    }


//    @Test
//    public  void flatMapExample(){
//        Flux<String> stringFlux = fluxLearnService.flatMapExample();
//        StepVerifier.create(stringFlux)
//                .expectNextCount(24)
//                .verifyComplete();
//    }

    @Test
    public void transformExample(){
        Flux flux = fluxLearnService.transformExample();
        StepVerifier.create(flux)
                .expectNextCount(4)
                .verifyComplete();
    }
}
