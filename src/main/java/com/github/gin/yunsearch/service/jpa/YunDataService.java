package com.github.gin.yunsearch.service.jpa;

import com.github.gin.yunsearch.model.YunData;
import com.github.gin.yunsearch.model.YunUser;
import com.github.gin.yunsearch.repository.YunDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.querydsl.QPageRequest;
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
                        ":isSingleShare" +
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
                .setParameter("isSingleShare", yunData.isSingleShare())
                .executeUpdate();
    }


    public void setSingleShare(Long id) {
        YunData data = dataRepository.findOne(id);
        data.setSingleShare(true);
        data.setUpdateTime(new Date());
    }

    public List<YunData> findByUpdateTime(Date updateTime) {
        return dataRepository.findByUpdateTimeGreaterThan(updateTime);
    }

    public List<YunData> findAll() {
        return dataRepository.findAll();
    }

    public List<YunData> findBySingleShare() {
        return dataRepository.findTop1000ByIsSingleShare(false);
    }

    public List<YunData> findByUkAndShareName(long uk, String shareName) {
        YunData data = new YunData();
        data.setUk(uk);
        data.setShareName(shareName);

        Example<YunData> example = Example.of(data);
        Sort sort = new Sort(Sort.Direction.DESC, "updateTime");
        return dataRepository.findAll(example, sort);
    }

    public void delete(Iterable<YunData> datas) {
        dataRepository.delete(datas);
    }
}
