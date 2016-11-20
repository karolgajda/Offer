package pl.karol.common.application;

import java.util.UUID;

public class IDGeneratorImpl implements IDGenerator {
    @Override
    public String generate() {
        return UUID.randomUUID().toString();
    }
}
