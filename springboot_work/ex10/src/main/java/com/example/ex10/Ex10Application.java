package com.example.ex10;

import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Arrays;

@SpringBootApplication
@EnableJpaAuditing
public class Ex10Application {

    public static void main(String[] args) {

        System.out.println("ଘ(੭*ˊᵕˋ)੭»ㅡ❥");
        ApplicationContext applicationContext =
                SpringApplication.run(Ex10Application.class, args);

//        Arrays.stream(applicationContext.getBeanDefinitionNames())
//                .forEach(System.out::println);

    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}

