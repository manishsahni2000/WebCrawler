package com.pramati.internet;

import java.util.concurrent.TimeUnit;

public class InternetConnectionChecker {
	
	private final int retryIntervalInSeconds;

    private boolean connected = false;
    private final String address;

    public InternetConnectionChecker(int retryIntervalInSeconds, String address) {
        this.retryIntervalInSeconds = retryIntervalInSeconds;
        this.address = address;
    }

    public void awaitAvailability() throws InterruptedException {
        isConnected();
        while (!connected) {
            TimeUnit.SECONDS.sleep(retryIntervalInSeconds);
            isConnected();
        }
    }

    public boolean isConnected() {
        connected = Utils.pingUrl(address);
        return connected;
    }

}
