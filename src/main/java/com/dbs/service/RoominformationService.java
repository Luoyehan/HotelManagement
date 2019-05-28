package com.dbs.service;

import com.dbs.po.Roominformation;

public interface RoominformationService {
	// 添加房务信息
	public void addRoominformation(Roominformation roominformation) throws Exception;

	// 查看房务信息
	public Roominformation findRoominformationByNumber(int r_number)throws Exception;

	// 删除房务信息
	public int deleteRoominformationByNumber(int r_number)throws Exception;

	// 开通网络处理信息
	public void openNetworkManagement()throws Exception;
	//修改客房服务
	public void updateRoominformationByNumber(Roominformation roominformation)throws Exception;

}
