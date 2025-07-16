package com.example.logindemo.util;

import com.example.logindemo.TestInfraApplication;
import com.example.logindemo.infra.reids.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = TestInfraApplication.class)
class RedisUtilTest {

    @Autowired
    private RedisUtil redisUtil;

    @Test
    void testSetAndGetValue() {
        String key = "test:key:string";
        String value = "Hello Redis";

        redisUtil.set(key, value);
        Object result = redisUtil.get(key);

        assertNotNull(result);
        assertEquals(value, result);
        redisUtil.delete(key);
    }

    @Test
    void testSetWithExpire() throws InterruptedException {
        String key = "test:key:expire";
        String value = "expire test";

        redisUtil.set(key, value, Duration.ofSeconds(1));
        assertEquals(value, redisUtil.get(key));

        Thread.sleep(2000);
        assertNull(redisUtil.get(key), "Value should expire");
    }

    @Test
    void testDeleteKey() {
        String key = "test:key:delete";
        redisUtil.set(key, "to be deleted");

        assertTrue(redisUtil.hasKey(key));
        assertTrue(redisUtil.delete(key));
        assertFalse(redisUtil.hasKey(key));
    }

    @Test
    void testHasKey() {
        String key = "test:key:exists";
        redisUtil.set(key, "exists");

        assertTrue(redisUtil.hasKey(key));
        redisUtil.delete(key);
        assertFalse(redisUtil.hasKey(key));
    }
}
