package com.behere.common.service;

import org.springframework.stereotype.Service;

import com.behere.common.domain.LogDO;
import com.behere.common.domain.PageDO;
import com.behere.common.utils.Query;
@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}
