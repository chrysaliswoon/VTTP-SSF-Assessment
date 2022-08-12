package assessment.core.vttpssf.repositories;

import java.util.Optional;

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

    public void save(String articles, String payload) {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        valueOps.set(articles, payload);
    }

    // ? To get the articles info
    public String get(String id) {
        ValueOperations<String, String> valueOps = redisTemplate.opsForValue();
        String value = valueOps.get(id);

        return value;

        // if (null == value)
        //     return Optional.empty(); // ? Empty box
        // return Optional.of(value); // ? Box with data

    }
}
