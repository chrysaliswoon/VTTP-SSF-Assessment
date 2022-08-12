package assessment.core.vttpssf.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

@Repository
public class NewsRespository {

    // ? To save the articles info
    @Autowired
    @Qualifier("redis")
    private RedisTemplate<String, String> redisTemplate;

    //? Save data to Redis
    public void save(String articles, String payload) {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        valueOps.set(articles, payload);
    }

    //? Load data from Redis
    // ? To get the articles info
    public String get(String id) {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        return valueOps.get(id);

    }
}
