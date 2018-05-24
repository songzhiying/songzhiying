package cn.duc.global.base;

import java.util.List;
import java.util.Map;

import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;

public interface BaseProvider<T extends BaseModel> {

	/**
	 * 
	 * <B>方法名称：</B><BR>
	 * <B>概要说明：</B><BR>
	 * 
	 * @param record
	 * @param booleans
	 *        是否更新缓存
	 * @return
	 */
	@Transactional
	public T updateWithSampleId(T record, Boolean... booleans);

	@Transactional
	public T updateByPrimaryKeyWithBLOBs(T record, Boolean... booleans);

	@Transactional
	public int insert(T record);

	@Transactional
	public T update(T record, Boolean... booleans);

	@Transactional
	public T updateSelective(T record, Boolean... booleans); // bzt add

	@Transactional
	public T delete(String id);

	@Transactional
	public T deleteMark(String id, String userId);

	public T queryById(String id, Boolean... booleans);

	public <K> List<K> getList(List<String> ids, Class<K> cls);

	public String getCacheKey(Object id);

	public String createId(String key);

	public PageInfo<T> pageHelperQuery(Map<String, Object> params);

	public long pageHelperCount(Map<String, Object> params);
}
