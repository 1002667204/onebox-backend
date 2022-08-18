package com.turing.onebox;

import com.turing.onebox.common.utils.UUIDUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class OneboxApplicationTests {

    @Test
    void contextLoads() {
    }


    /**
     * 测试生成id
     */
    @Test
    void getCode(){
        for (int i = 0; i < 10; i++) {
            System.out.println(UUIDUtils.getUUID());
        }
    }
}
