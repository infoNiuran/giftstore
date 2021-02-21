package com.niuran.giftstore.dao;

import com.niuran.giftstore.model.Gift;
import com.niuran.giftstore.model.GiftExample;

import java.util.List;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftMapper {
    int countByExample(GiftExample example);

    int deleteByExample(GiftExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Gift record);

    @Options(useGeneratedKeys = true)
    int insertSelective(Gift record);

    List<Gift> selectByExample(GiftExample example);

    Gift selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Gift record, @Param("example") GiftExample example);

    int updateByExample(@Param("record") Gift record, @Param("example") GiftExample example);

    int updateByPrimaryKeySelective(Gift record);

    int updateByPrimaryKey(Gift record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table gift
     *
     * @mbg.generated
     * @project https://github.com/itfsw/mybatis-generator-plugin
     */
    Gift selectOneByExample(GiftExample example);
}