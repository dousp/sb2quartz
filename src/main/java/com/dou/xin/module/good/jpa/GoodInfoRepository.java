package com.dou.xin.module.good.jpa;


import com.dou.xin.module.good.entity.GoodInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * ========================
 * Created with IntelliJ IDEA.
 * Date：2017/11/5
 * Time：14:55
 * 码云：http://git.oschina.net/jnyqy
 * ========================
 *
 * @author 恒宇少年
 */
public interface GoodInfoRepository
        extends JpaRepository<GoodInfoEntity, Long> {
}
