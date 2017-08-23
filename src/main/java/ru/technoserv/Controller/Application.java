package ru.technoserv.Controller;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;


@SpringBootApplication
public class Application {

    private static Logger log = Logger.getLogger(EmployeeController.class.getName());

    public static void main(String[] args){
        try {
            FileHandler fh = new FileHandler("C:\\users\\dkondratyev\\desktop\\file.txt");
            log.addHandler(fh);
        } catch (IOException e) {
            e.printStackTrace();
        }
        SpringApplication.run(Application.class, args);
    }
}
