package com.github.gin.yunsearch.service;

import com.github.gin.yunsearch.model.YunUser;
import com.github.gin.yunsearch.repository.YunDataRepository;
import com.github.gin.yunsearch.repository.YunUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author GinPonson
 * @since 2017/4/2
 */
@Service
@Transactional
public class YunUserService {

    @Autowired
    private YunUserRepository userRepository;
    @Autowired
    private EntityManager entityManager;

    public void saveIgnore(YunUser yunUser) {
        Query query = entityManager.createNativeQuery(
                "INSERT IGNORE INTO yun_user VALUES(" +
                        ":id, " +
                        ":username, " +
                        ":uk," +
                        ":updateTime, " +
                        ":avatarUrl, " +
                        ":pubshareCount, " +
                        ":version, " +
                        ":pubshareCrawled, " +
                        ":followCrawled, " +
                        ":followTime," +
                        ":fansCrawled" +
                        ")"
        );
        query.setParameter("id", yunUser.getId())
                .setParameter("username", yunUser.getUsername())
                .setParameter("uk", yunUser.getUk())
                .setParameter("updateTime", yunUser.getUpdateTime())
                .setParameter("avatarUrl", yunUser.getAvatarUrl())
                .setParameter("pubshareCount", yunUser.getPubshareCount())
                .setParameter("version", yunUser.getVersion())
                .setParameter("pubshareCrawled", yunUser.isPubshareCrawled())
                .setParameter("followCrawled", yunUser.isFollowCrawled())
                .setParameter("followTime", yunUser.getFollowTime())
                .setParameter("fansCrawled", yunUser.isFansCrawled())
                .executeUpdate();
    }

    public void setPubshareCrawledComplete(Long uk) {
        userRepository.updatePubshareCrawledFor(true ,uk);
    }

    public void setFollowCrawledComplete(Long uk) {
        userRepository.updateFollowCrawledFor(true ,uk);
    }

    public void setFansCrawledComplete(Long uk) {
        userRepository.updateFansCrawledFor(true ,uk);
    }

    public List<YunUser> findPubshareNeedCrawle() {
        return userRepository.findTop3ByPubshareCrawled(false);
    }

    public List<YunUser> findFollowNeedCrawle() {
        return userRepository.findTop3ByFollowCrawled(false);
    }

    public List<YunUser> findFansNeedCrawle() {
        return userRepository.findTop3ByFansCrawled(false);
    }
}
