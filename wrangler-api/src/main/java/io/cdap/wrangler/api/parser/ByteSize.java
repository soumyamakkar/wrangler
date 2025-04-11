package io.cdap.wrangler.api.parser;

public class ByteSize {
    private final long bytes;

    public ByteSize(String input) {
        this.bytes = parse(input);
    }

    public long getBytes() {
        return bytes;
    }

    private long parse(String input) {
        String trimmed = input.trim().toUpperCase();

        try {
            if (trimmed.endsWith("KB")) {
                return (long) (Double.parseDouble(trimmed.replace("KB", "")) * 1024);
            } else if (trimmed.endsWith("MB")) {
                return (long) (Double.parseDouble(trimmed.replace("MB", "")) * 1024 * 1024);
            } else if (trimmed.endsWith("GB")) {
                return (long) (Double.parseDouble(trimmed.replace("GB", "")) * 1024 * 1024 * 1024);
            } else if (trimmed.endsWith("TB")) {
                return (long) (Double.parseDouble(trimmed.replace("TB", "")) * 1024L * 1024L * 1024L * 1024L);
            } else if (trimmed.endsWith("B")) {
                return Long.parseLong(trimmed.replace("B", ""));
            } else {
                throw new IllegalArgumentException("Unrecognized byte unit in input: " + input);
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format for byte size: " + input, e);
        }
    }

    @Override
    public String toString() {
        return bytes + " bytes";
    }
}

