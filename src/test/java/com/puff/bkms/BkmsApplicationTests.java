package com.puff.bkms;

import com.mysql.jdbc.Driver;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.SQLException;

@SpringBootTest
class BkmsApplicationTests {


    @Test
    void contextLoads() {
        System.out.println("yes");
    }

}
