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

    YunUser findByUk(long uk);
}
