package ua.javatrachenko.endpoint;

// класс, для запуска веб-сервера с веб-сервисами
import javax.xml.ws.Endpoint;
// класс нашего веб-сервиса
import ua.javatrachenko.ws.HelloWebServiceImpl;

public class HelloWebServicePublisher {
    public static void main(String... args) {
        // запускаем веб-сервер на порту 1986
        // и по адресу, указанному в первом аргументе,
        // запускаем веб-сервис, передаваемый во втором аргументе
        Endpoint.publish("http://localhost:1986/wss/hello", new HelloWebServiceImpl());
    }
}