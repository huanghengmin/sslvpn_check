package com.hzih.sslvpn.dao;

import cn.collin.commons.dao.BaseDao;
import cn.collin.commons.domain.PageResult;
import com.hzih.sslvpn.domain.ClientLog;

import java.util.Date;
import java.util.List;

public interface ClientLogDao extends BaseDao {

	PageResult listByPage(String name, String audittype, Date startDate, Date endDate, int pageIndex, int limit);

	List<ClientLog>  findByCode(String code);

	List<ClientLog>  findByYearCode(String year, String code);

	List<ClientLog>  findByYearMonthCode(String year, String month, String code);

	List<ClientLog>  findByYearMonthDayCode(String year, String month, String day, String code);

	List<ClientLog>  findByYearMonthDayHourCode(String year, String month, String day, String hour, String code);

	void removeLists(long[] ids);

	void remove(long id);

}
