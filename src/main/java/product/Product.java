package product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import javax.persistence.Id;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RedisHash("product")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id UUID id;

//    @TimeToLive(unit = TimeUnit.MINUTES)
//    public Long timeToLive() {
//        return 30L;
//    }
//
}

