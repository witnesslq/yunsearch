package com.github.gin.yunsearch.repository;

import com.github.gin.yunsearch.model.YunData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * @author GinPonson
 */
public interface YunDataRepository extends JpaRepository<YunData,Long>{

    List<YunData> findByUpdateTimeGreaterThan(Date updateTime);

}
