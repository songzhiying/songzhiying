package cn.duc.global.base;

import java.util.Map;

public interface BaseMapper<T extends BaseModel> {

	int deleteByPrimaryKey(String id);

	int deleteByPrimaryKey(Map<String, Object> params);

	T selectByPrimaryKey(String id);

	int insert(T record);

	int insertSelective(T record);

	int updateByPrimaryKeyWithBLOBs(T record);

	int updateByPrimaryKey(T record);

	int updateByPrimaryKeySelective(T record);
}
