package io.cdap.wrangler.api.parser;

public class TimeDuration {
    private final long milliseconds;

    public TimeDuration(String input) {
        this.milliseconds = parse(input);
    }

    public long getMilliseconds() {
        return milliseconds;
    }

    private long parse(String input) {
        String trimmed = input.trim().toLowerCase();

        try {
            if (trimmed.endsWith("ns")) {
                return (long) (Double.parseDouble(trimmed.replace("ns", "")) / 1_000_000);
            } else if (trimmed.endsWith("us")) {
                return (long) (Double.parseDouble(trimmed.replace("us", "")) / 1_000);
            } else if (trimmed.endsWith("ms")) {
                return (long) Double.parseDouble(trimmed.replace("ms", ""));
            } else if (trimmed.endsWith("s") || trimmed.endsWith("sec") || trimmed.endsWith("seconds")) {
                return (long) (Double.parseDouble(trimmed.replaceAll("(s|sec|seconds)$", "")) * 1000);
            } else if (trimmed.endsWith("m") || trimmed.endsWith("min") || trimmed.endsWith("minutes")) {
                return (long) (Double.parseDouble(trimmed.replaceAll("(m|min|minutes)$", "")) * 60 * 1000);
            } else if (trimmed.endsWith("h") || trimmed.endsWith("hr") || trimmed.endsWith("hours")) {
                return (long) (Double.parseDouble(trimmed.replaceAll("(h|hr|hours)$", "")) * 60 * 60 * 1000);
            } else {
                throw new IllegalArgumentException("Unrecognized time unit in input: " + input);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format for time duration: " + input, e);
        }
    }

    @Override
    public String toString() {
        return milliseconds + " ms";
    }
}
