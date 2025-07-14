package cn.gok.hotel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("cn.gok.hotel.mapper")
public class HotelBackApplication {
    public static void main(String[] args) {
        SpringApplication.run(HotelBackApplication.class, args);
    }
} 