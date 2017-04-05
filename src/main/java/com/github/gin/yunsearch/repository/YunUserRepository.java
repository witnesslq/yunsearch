package com.github.gin.yunsearch.repository;

import com.github.gin.yunsearch.model.YunUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author GinPonson
 */
public interface YunUserRepository extends JpaRepository<YunUser, Long> {

    List<YunUser> findTop3ByPubshareCrawled(boolean isPubshareCrawled);

    List<YunUser> findTop3ByFollowCrawled(boolean isFollowCrawled);

    List<YunUser> findTop3ByFansCrawled(boolean isFansCrawled);

    @Modifying
    @Query("update YunUser u set u.pubshareCrawled = ?1 where u.uk = ?2")
    void updatePubshareCrawledFor(boolean isPubshareCrawled, Long uk);

    @Modifying
    @Query("update YunUser u set u.followCrawled = ?1 where u.uk = ?2")
    void updateFollowCrawledFor(boolean isFollowCrawled, Long uk);

    @Modifying
    @Query("update YunUser u set u.fansCrawled = ?1 where u.uk = ?2")
    void updateFansCrawledFor(boolean isFansCrawled, Long uk);
}
