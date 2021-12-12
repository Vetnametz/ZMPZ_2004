package ua.javatrachenko.client;

import java.net.*;
import java.io.*;

// нужно, чтобы получить wsdl описание и через него
// дотянуться до самого веб-сервиса
import java.net.URL;
// такой эксепшн возникнет при работе с объектом URL
import java.net.MalformedURLException;

// классы, чтобы пропарсить xml-ку c wsdl описанием
// и дотянуться до тега service в нем
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

// интерфейс нашего веб-сервиса (нам больше и нужно)
import ua.javatrachenko.ws.HelloWebService;

public class HelloWebServiceClient {
    public static void main(String[] args) throws Exception {
        // создаем ссылку на wsdl описание
        URL url = new URL("http://localhost:1986/wss/hello?wsdl");

        // Параметры следующего конструктора смотрим в самом первом теге WSDL описания - definitions
        // 1-ый аргумент смотрим в атрибуте targetNamespace
        // 2-ой аргумент смотрим в атрибуте name
        QName qname = new QName("http://ws.javatrachenko.ua/", "HelloWebServiceImplService");

        // Теперь мы можем дотянуться до тега service в wsdl описании,
        Service service = Service.create(url, qname);
        // а далее и до вложенного в него тега port, чтобы
        // получить ссылку на удаленный от нас объект веб-сервиса
        HelloWebService hello = service.getPort(HelloWebService.class);

        // Ура! Теперь можно вызывать удаленный метод
        System.out.println(hello.getHelloString("Web-сервіси на мові Java"));

        readRemoteTxtFile();
    }

    static void readRemoteTxtFile() throws Exception {
        System.out.println(System.lineSeparator());
        System.out.println("--- Read remote txt file ---");

        URL w3Org = new URL("https://www.w3.org/TR/PNG/iso_8859-1.txt");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(w3Org.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
    }
}