package cn.duc.global.base;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.math.NumberUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.transaction.annotation.Transactional;

import com.common.lang.util.DateUtil;
import com.common.lang.util.PropertiesUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import cn.duc.global.until.Constants;
import cn.duc.global.until.DataUtil;
import cn.duc.global.until.FieldUtil;
import cn.duc.global.until.InstanceUtil;
import plugin.BaseModel;

public abstract class BaseProviderImpl<T extends BaseModel> implements BaseProvider<T> {

	@Autowired
	protected BaseMapper<T> mapper;

	@Transactional
	public T updateWithSampleId(T record, Boolean... booleans) {
		return record;
	}

	@Transactional
	public T updateByPrimaryKeyWithBLOBs(T record, Boolean... booleans) {
		try {
			record.setUpdateTime(new Date());
			int number = 0;
			if (StringUtils.isBlank(record.getId())) {
				String key = record.getClass().getSimpleName();
				record.setId(createId(key));
				if (record.getCreateTime() == null) {
					record.setCreateTime(new Date());
				}
				record.setEnable(true);
				number = insert(record);
			} else {
				number = mapper.updateByPrimaryKeyWithBLOBs(record);
			}
			boolean cache = true;
			if (booleans != null && booleans.length > 0 && booleans[0] != null && !booleans[0]) {
				cache = false;
			}
			if (number > 0 && cache) {
				//RedisUtil.set(getCacheKey(record.getId()), record);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return record;
	}

	@Transactional
	public int insert(T record) {
		return mapper.insert(record);
	}

	@Transactional
	public T update(T record, Boolean... booleans) {
		try {
			record.setUpdateTime(new Date());
			int number = 0;
			if (StringUtils.isBlank(record.getId())) {
				String key = record.getClass().getSimpleName();
				record.setId(createId(key));
				if (record.getCreateTime() == null) {
					record.setCreateTime(new Date());
				}
				record.setEnable(true);
				number = insert(record);
			} else {
				number = mapper.updateByPrimaryKey(record);
			}
			boolean cache = true;
			if (booleans != null && booleans.length > 0 && booleans[0] != null && !booleans[0]) {
				cache = false;
			}
			if (number > 0 && cache) {
				//RedisUtil.set(getCacheKey(record.getId()), record);
			}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return record;
	}

	@Transactional
	public T updateSelective(T record, Boolean... booleans) {
		try {
			record.setUpdateTime(new Date());
			int number = 0;
			if (StringUtils.isBlank(record.getId())) {
				String key = record.getClass().getSimpleName();
				record.setId(createId(key));
				record.setCreateTime(new Date());
				record.setEnable(true);
				number = mapper.insertSelective(record);
			} else {
				number = mapper.updateByPrimaryKeySelective(record);
			}
			//boolean cache = true;
			//if (booleans != null && booleans.length > 0 && booleans[0] != null && !booleans[0]) {
			//	cache = false;
			//}
			//if (number > 0 && cache) {
			//	RedisUtil.set(getCacheKey(record.getId()), record);
			//}
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return record;
	}

	@Transactional
	public T delete(String id) {
		try {
			T record = queryById(id, false);
			if (record != null) {
				int number = mapper.deleteByPrimaryKey(id);
				if (number > 0) {
					//RedisUtil.del(getCacheKey(id));
				}
			}
			return record;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	@Transactional
	public T deleteMark(String id, String userId) {
		try {
			T record = queryById(id, false);
			if (record != null) {
				record.setEnable(false);
				record.setUpdateTime(new Date());
				record.setUpdateBy(userId);
				int number = mapper.updateByPrimaryKey(record);
				if (number > 0) {
					//RedisUtil.set(getCacheKey(id), record);
				}

			}
			return record;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	/*@SuppressWarnings("unchecked")
	@Transactional
	public T queryById(String id, Boolean... booleans) {
		try {
			String key = getCacheKey(id);
			T record = (T) RedisUtil.get(key);
			if (record == null) {
				record = mapper.selectByPrimaryKey(id);
				if ((booleans == null || booleans.length == 0 || booleans[0] == null || booleans[0])
						&& record != null) {
					RedisUtil.set(key, record);
				}
			}
			return record;
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}*/

	@SuppressWarnings("unchecked")
	private BaseProviderImpl<T> getProvider() {
		return InstanceUtil.getBean(getClass());
	}

	/** 生成redis主键策略 */
	/*public String createId(String key) {
		String redisKey = PropertiesUtil.getString("redis.cache.namespace") + "REDIS_TBL_" + key;
		String dateTime = DateUtil.currentDateString(DateUtil.DATE_PATTERN.YYYYMMDDHHMMSSSSS);
		return dateTime + RedisUtil.incr(redisKey);
	}*/

	/** 生成redis主键策略 */
	/*public String createSampleId(String key) {
		String redisKey = PropertiesUtil.getString("redis.cache.namespace") + "REDIS_TBL_" + key;
		String dateTime = DateUtil.currentDateString(DateUtil.DATE_PATTERN.YYMMDDHH);
		return dateTime + RedisUtil.incr(redisKey);
	}*/

	/** 根据Id查询(cls返回类型Class) */
	public <K> List<K> getList(List<String> ids, Class<K> cls) {
		List<K> list = InstanceUtil.newArrayList();
		if (ids != null) {
			for (String id : ids) {
				T t = getProvider().queryById(id);
				K k = InstanceUtil.to(t, cls);
				list.add(k);
			}
		}
		return list;
	}

	/** 根据Id查询(默认类型T) */
	public List<T> getList(List<String> ids) {
		List<T> list = InstanceUtil.newArrayList();
		if (ids != null) {
			for (String id : ids) {
				list.add(getProvider().queryById(id));
			}
		}
		return list;
	}

	/** 获取缓存键值 */
	public String getCacheKey(Object id) {
		String cacheName = null;
		CacheConfig cacheConfig = getClass().getAnnotation(CacheConfig.class);
		if (cacheConfig == null || cacheConfig.cacheNames() == null || cacheConfig.cacheNames().length < 1) {
			cacheName = getClass().getName();
		} else {
			cacheName = cacheConfig.cacheNames()[0];
		}
		return new StringBuilder(PropertiesUtil.getString("redis.cache.namespace")).append(cacheName).append(":")
				.append(id).toString();
	}

	/** 启动分页查询 */
	protected void startPage(Map<String, Object> params) {
		params.put("pageNum", NumberUtils.toInt(String.valueOf(params.get("pageNum")), Constants.START_PAGE_NUM));
		params.put("pageSize", NumberUtils.toInt(String.valueOf(params.get("pageSize")), Constants.DEFAULT_PAGE_SIZE));

		// if (DataUtil.isEmpty(params.get("orderBy"))) {
		// params.put("orderBy", "id desc");
		// } else {
		if (!DataUtil.isEmpty(params.get("orderBy"))) {
			// 将驼峰命名法 转为数据库使用的 下划线命名法， 如：projectName desc 转为 project_name desc
			String orderBy = FieldUtil.orderByStrFormat((String) params.remove("orderBy"));
			if (!StringUtils.isEmpty(orderBy)) {
				params.put("orderBy", orderBy);
			}
		}
		if (!DataUtil.isEmpty(params.get("noOrderBy"))) {
			params.remove("orderBy");
		}
		PageHelper.startPage(params);
	}

	/**
	 * 分页查询(实时查询不取缓存)
	 * 
	 * @param datas
	 * @return
	 */
	public PageInfo<T> getPageByDB(Page<T> datas) {
		Page<T> page = new Page<T>(datas.getPageNum(), datas.getPageSize());
		page.setTotal(datas.getTotal());
		if (datas != null) {
			page.addAll(datas);
		}
		return new PageInfo<T>(page);
	}

	/**
	 * 分页查询(实时查询不取缓存,cls返回类型Class)
	 * 
	 * @param datas
	 * @return
	 */
	public <K> PageInfo<K> getPageByDB(Page<K> datas, Class<K> cls) {
		Page<K> page = new Page<K>(datas.getPageNum(), datas.getPageSize());
		page.setTotal(datas.getTotal());
		if (datas != null) {
			page.addAll(datas);
		}
		return new PageInfo<K>(page);
	}

	@Override
	public PageInfo<T> pageHelperQuery(Map<String, Object> params) {
		return null;
	}

	@Override
	public long pageHelperCount(Map<String, Object> params) {
		return 0;
	}

}
