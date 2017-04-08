package com.github.gin.yunsearch.service.jpa;

import com.github.gin.yunsearch.model.YunData;
import com.github.gin.yunsearch.repository.YunDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * @author GinPonson
 * @since 2017/4/2
 */
@Service
@Transactional
public class YunDataService {

    @Autowired
    private YunDataRepository dataRepository;
    @Autowired
    private EntityManager entityManager;

    public void saveIgnore(YunData yunData) {
        Query query = entityManager.createNativeQuery(
                "INSERT IGNORE INTO yun_data VALUES(" +
                        ":id, " +
                        ":shareId, " +
                        ":dataId, " +
                        ":shareName, " +
                        ":uk, " +
                        ":description, " +
                        ":updateTime, " +
                        ":shareTime, " +
                        ":avatarUrl, " +
                        ":version" +
                        ")"
        );
        query.setParameter("id", yunData.getId())
                .setParameter("shareId", yunData.getShareId())
                .setParameter("dataId", yunData.getDataId())
                .setParameter("shareName", yunData.getShareName())
                .setParameter("uk", yunData.getUk())
                .setParameter("description", yunData.getDescription())
                .setParameter("shareTime", yunData.getShareTime())
                .setParameter("avatarUrl", yunData.getAvatarUrl())
                .setParameter("updateTime", yunData.getUpdateTime())
                .setParameter("version", yunData.getVersion())
                .executeUpdate();
    }

    public List<YunData> findByUpdateTime(Date updateTime){
        return dataRepository.findByUpdateTimeGreaterThan(updateTime);
    }

    public List<YunData> findAll(){
        return dataRepository.findAll();
    }
}
