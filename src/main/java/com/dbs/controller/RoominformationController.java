package com.dbs.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dbs.po.NetworkManagement;
import com.dbs.po.Roominformation;
import com.dbs.service.RoominformationService;
import com.dbs.util.ReturnData;
import com.sun.xml.internal.fastinfoset.util.StringIntMap;

@Controller
@RequestMapping(value = "/room")
public class RoominformationController {
	@Autowired
	RoominformationService roominformationService;

	/***
	 * 添加住房信息
	 * 
	 * @param response
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/insertroominformation", method = RequestMethod.POST)
	public @ResponseBody ReturnData insertroominformation(HttpServletResponse response, HttpServletRequest request)
			throws Exception {
		ReturnData returnData = new ReturnData();
		Roominformation roominformation = new Roominformation();
		roominformation.setR_number(Integer.parseInt(request.getParameter("r_number")));
		roominformation.setR_direction(request.getParameter("r_direction"));
		roominformation.setR_tpye(request.getParameter("r_tpye"));
		roominformation.setR_equipment(Integer.parseInt(request.getParameter("r_equipment")));
		roominformation.setR_state(Integer.parseInt(request.getParameter("r_state")));
		roominformationService.addRoominformation(roominformation);
		List<Object> listroominformation = new ArrayList<Object>();
		// 插入值添加到list里面
		listroominformation.add(roominformation);
		returnData.setBody(listroominformation);

		try {
			if (returnData.getBody() != null) {
				returnData.setKey("SUCCESS");
				returnData.setMsg("客房信息添加成功");
			} else {
				returnData.setKey("FAIL");
				returnData.setMsg("客房信息添加失败");
				System.out.println();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return returnData;
	}

	/***
	 * 查找响应的客房信息，由于是按照 主键来查找则每次只能查找一条数据
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "findRoominformationByNumberlogin", method = RequestMethod.POST)
	public @ResponseBody ReturnData findRoominformationByNumber(HttpServletRequest request) throws Exception {
		Roominformation roominformation = new Roominformation();
		ReturnData returnData = new ReturnData();
		int r_number = Integer.parseInt(request.getParameter("r_number"));
		Roominformation room1 = roominformationService.findRoominformationByNumber(r_number);
		while (room1 != null) {
			List<Object> list = new ArrayList<Object>();
			roominformation.setR_direction(room1.getR_direction());
			roominformation.setR_equipment(room1.getR_equipment());
			roominformation.setR_number(room1.getR_number());
			roominformation.setR_state(room1.getR_state());
			roominformation.setR_tpye(room1.getR_tpye());
			list.add(roominformation);
			returnData.setBody(list);
		}
		try {
			if (returnData.getBody() != null) {
				returnData.setKey("SUCCESS");
				returnData.setMsg("查询信息添加成功");
			} else {
				returnData.setKey("FAIL");
				returnData.setMsg("查询客房信息添加失败");
				System.out.println();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return returnData;
	}

	/**
	 * 根据房间号对其进行删除
	 * 
	 * @param response
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "deleteRoominformationByNumberlogin", method = RequestMethod.POST)
	public @ResponseBody ReturnData deleteRoominformationByNumber(HttpServletRequest request) {
		ReturnData returnData = new ReturnData();
		int r_number = Integer.parseInt(request.getParameter("r_number"));
		try {
			int deletenumber = roominformationService.deleteRoominformationByNumber(r_number);
			if (deletenumber > 0) {
				returnData.setKey("SUCCESS");
				returnData.setKey("你已经删除了编号为" + r_number + "的房间");
			} else {
				returnData.setKey("FAIL");
				returnData.setKey("对不起没有这个" + r_number + "的房间，无法进行删除");
			}
		} catch (Exception e) {

			e.printStackTrace();
		}
		return returnData;
	}

	/**
	 * 修改先判断是否有这个房间 如果有就进行修改
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updateRoominformationByNumberlogin", method = RequestMethod.POST)
	public @ResponseBody ReturnData updateRoominformationByNumber(HttpServletRequest request) {
		int r_number = Integer.parseInt(request.getParameter("r_number"));
		Roominformation roominformation = new Roominformation();
		ReturnData returnData = new ReturnData();
		List<Object> list = new ArrayList<Object>();

		Roominformation room1;
		try {
			room1 = roominformationService.findRoominformationByNumber(r_number);
			while (room1 != null) {
				roominformation.setR_number(r_number);
				roominformation.setR_direction(request.getParameter("r_direction"));
				roominformation.setR_equipment(Integer.parseInt(request.getParameter("r_equipment")));
				roominformation.setR_state(Integer.parseInt(request.getParameter("r_state")));
				roominformation.setR_tpye(request.getParameter("r_tpye"));
				roominformationService.updateRoominformationByNumber(roominformation);
				list.add(roominformation);
				returnData.setBody(list);
				returnData.setKey("SUCCESS");
				returnData.setMsg("修改" + r_number + "信息成功");
			}
			returnData.setKey("FAIL");
			returnData.setMsg("修改" + r_number + "信息失败没有这个房间");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return returnData;
	}

	public @ResponseBody ReturnData openNetworkManagement(HttpServletRequest request) {
		int r_number = Integer.parseInt(request.getParameter("r_number"));
		ReturnData returnData = new ReturnData();
		List<Object> list = new ArrayList<Object>();
		try {
			Roominformation room1 = roominformationService.findRoominformationByNumber(r_number);
			while (room1 != null) {
				NetworkManagement networkManagement = roominformationService.openNetworkManagement(r_number);
				if(networkManagement.getN_opentime()!=null){
					list.add(networkManagement.getN_opentime());
					list.add(networkManagement.getN_roomnumber());
					list.add(networkManagement.getN_closetime());
					list.add(networkManagement.getN_customernumbernumber());
					list.add(networkManagement.getN_serialnumber());
					returnData.setBody(list);
	               returnData.setKey("SUCCESS");
	               returnData.setMsg("此人开通的时间是："+networkManagement.getN_roomnumber());
				}
				else {
					returnData.setKey("FAIL");
			        returnData.setMsg("不好意思你没有开通网络功能");
				}
				//NetworkManagement networkManagement = roominformationService.openNetworkManagement(r_number);
				
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return returnData;
	}

}
