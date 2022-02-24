package com.sbrf.reboot.concurrency;

import com.sbrf.reboot.service.ReportService;
import com.sbrf.reboot.service.SomeService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeoutException;
import java.util.stream.DoubleStream;

import static org.mockito.Mockito.*;

public class CompletableFeatureTest {

    @Test
    public void successCompletableFeature() throws ExecutionException, InterruptedException, TimeoutException {
        ReportService reportService = Mockito.mock(ReportService.class);

        when(reportService.sendReport("Отправляю отчет")).then(e->{

            Thread.sleep(Duration.ofSeconds(3).toMillis());
            return "SUCCESS";
        });

        SomeService someService = new SomeService(reportService);

        someService.doSomething();

        verify(reportService, times(1)).sendReport("Отправляю отчет");
    }

    /*
     * Задача на 5+.
     * Дополнительный пример использования CompletableFuture.
     */
    @Test
    public void additionalExample() throws ExecutionException, InterruptedException {

        final int SIZE = 10_000;
        final int HALF_SIZE = SIZE / 2;

        double[] arrayNumbers = DoubleStream.generate(Math::random).limit(SIZE).toArray();

        CompletableFuture<Double> leftCalculations = CompletableFuture.supplyAsync(() -> {
            double[] leftArrayNumbers = Arrays.copyOf(arrayNumbers, HALF_SIZE);
            return DoubleStream.of(leftArrayNumbers).map(Math::sin).sum();
        });

        CompletableFuture<Double> rightCalculations = CompletableFuture.supplyAsync(() -> {
            double[] rightArrayNumbers = Arrays.copyOfRange(arrayNumbers, HALF_SIZE, SIZE);
            return DoubleStream.of(rightArrayNumbers).map(Math::sin).sum();
        });

        CompletableFuture<Double> calculations = leftCalculations.thenCombine(rightCalculations, Double::sum);

        Assertions.assertEquals(DoubleStream.of(arrayNumbers).map(Math::sin).sum(), calculations.get());
    }

}
