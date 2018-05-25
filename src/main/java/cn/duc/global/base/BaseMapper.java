package cn.duc.global.base;

import java.util.Map;

import plugin.BaseModel;

public interface BaseMapper<T extends BaseModel> {

	public int deleteByPrimaryKey(String id);

	public	int deleteByPrimaryKey(Map<String, Object> params);

	public T selectByPrimaryKey(String id);

	public int insert(T record);

	public int insertSelective(T record);

	public int updateByPrimaryKeyWithBLOBs(T record);

	public int updateByPrimaryKey(T record);

	public int updateByPrimaryKeySelective(T record);
}
