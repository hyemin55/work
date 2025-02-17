package com.pmh.org;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class FirstApplication {

    public static void main(String[] args) {

        System.out.println("ଘ(੭*ˊᵕˋ)੭»ㅡ❥");
        ApplicationContext applicationContext =
                SpringApplication.run(FirstApplication.class, args);

//        Arrays.stream(applicationContext.getBeanDefinitionNames())
//                .forEach(System.out::println);

    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}

