package br.com.deja.apicachesample.infra.utils;

public class LongRunningTask {
    public static void simulate(int milli) {
        try {
            Thread.sleep(milli);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
