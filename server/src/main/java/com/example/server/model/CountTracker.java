package com.example.server.model;

public class CountTracker {

    private int runningCount = 0;

    public void record(Card card) { this.runningCount += card.countValue(); }

    public void reset() { this.runningCount = 0; }

    public int getRunningCount() { return this.runningCount; }

    public double getTrueCount(double decksRemaining) {
        // Guard against divide-by-zero: an exhausted shoe would otherwise yield
        // Infinity/NaN, which is also invalid JSON when this crosses the wire.
        if (decksRemaining <= 0) {
            return 0;
        }
        return this.runningCount / decksRemaining;
    }
}
