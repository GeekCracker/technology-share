package com.technology.share.mapper;

import com.technology.share.domain.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

public interface UserMapper extends MyMapper<User> {

    /**
     * 根据DI修改用户启用禁用状态
     * @param id
     */
    @Update("update t_user set user_status = !user_status where id = #{id}")
    void enableDisable(@Param("id") Long id);
}
