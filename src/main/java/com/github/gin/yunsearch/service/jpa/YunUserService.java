package com.github.gin.yunsearch.service.jpa;

import com.github.gin.yunsearch.model.YunUser;
import com.github.gin.yunsearch.repository.YunUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.QPageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.Date;
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
        YunUser user = userRepository.findByUk(uk);
        user.setPubshareCrawled(true);
        user.setUpdateTime(new Date());
    }

    public void setFollowCrawledComplete(Long uk) {
        YunUser user = userRepository.findByUk(uk);
        user.setFollowCrawled(true);
        user.setUpdateTime(new Date());
    }

    public void setFansCrawledComplete(Long uk) {
        YunUser user = userRepository.findByUk(uk);
        user.setFansCrawled(true);
        user.setUpdateTime(new Date());
    }

    public List<YunUser> findPubshareNeedCrawled() {
        YunUser user = new YunUser();
        user.setPubshareCrawled(false);

        Example<YunUser> example = Example.of(user);
        Pageable pageable = new QPageRequest(0,3);
        return userRepository.findAll(example,pageable).getContent();
    }

    public List<YunUser> findFollowNeedCrawled() {
        YunUser user = new YunUser();
        user.setFollowCrawled(false);

        Example<YunUser> example = Example.of(user);
        Pageable pageable = new QPageRequest(0,3);
        return userRepository.findAll(example,pageable).getContent();
    }

    public List<YunUser> findFansNeedCrawled() {
        YunUser user = new YunUser();
        user.setFansCrawled(false);

        Example<YunUser> example = Example.of(user);
        Pageable pageable = new QPageRequest(0,3);
        return userRepository.findAll(example,pageable).getContent();
    }
}
