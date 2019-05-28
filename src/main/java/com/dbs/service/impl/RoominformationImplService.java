package com.dbs.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dbs.mapper.RoominformationMapper;
import com.dbs.po.NetworkManagement;
import com.dbs.po.Roominformation;
import com.dbs.service.RoominformationService;

@Service
@Transactional
public class RoominformationImplService implements RoominformationService {
	@Autowired
	private RoominformationMapper roominformationMapper;

	@Override
	public void addRoominformation(Roominformation roominformation)throws Exception {
		this.roominformationMapper.addRoominformation(roominformation);
	}

	@Override
	public Roominformation findRoominformationByNumber(int r_number)throws Exception {
		return this.roominformationMapper.findRoominformationByNumber(r_number);

	}

	@Override
	public int deleteRoominformationByNumber(int r_number)throws Exception {
	 return	this.roominformationMapper.deleteRoominformationByNumber(r_number);

	}

	@Override
	public NetworkManagement openNetworkManagement(int r_number)throws Exception{
		 return this.roominformationMapper.openNetworkManagement(r_number);
		// 待定等待查询

	}

	@Override
	public void updateRoominformationByNumber(Roominformation roominformation) throws Exception {
	  this.roominformationMapper.updateRoominformationByNumber(roominformation);
		
	}

}
