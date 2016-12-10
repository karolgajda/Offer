package pl.karol.administration.user.service;

@FunctionalInterface
public interface EncryptionService {

    String encrypt(String text);

}
