package cn.duc.global.base;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.Page;

public interface BaseExpandMapper<T extends BaseModel> {

	public T query(Map<String, Object> params);

	/** 无需分页的查询实体 */
	public List<T> queryForList(Map<String, Object> params);

	public T queryExpand(Map<String, Object> params);

	public List<T> queryExpandForList(Map<String, Object> params);

	public Page<T> pageHelperQuery(Map<String, Object> params);

}
