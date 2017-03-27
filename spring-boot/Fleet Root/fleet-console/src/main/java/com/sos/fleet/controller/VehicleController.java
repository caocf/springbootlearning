package com.sos.fleet.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sos.fleet.common.condition.VehicleBindingCondition;
import com.sos.fleet.common.constants.ApplicationKeys;
import com.sos.fleet.common.constants.OperationKeys;
import com.sos.fleet.common.constants.OperationTypeKeys;
import com.sos.fleet.common.constants.StatusKeys;
import com.sos.fleet.common.controller.RestClientController;
import com.sos.fleet.common.domain.SearchPageImpl;
import com.sos.fleet.common.domain.SearchPageImpl.SimpleOrder;
import com.sos.fleet.common.domain.UserDomain;
import com.sos.fleet.common.domain.VehicleBindingDomain;
import com.sos.fleet.common.domain.VehicleBindingDomain_;
import com.sos.fleet.common.dto.VehicleBindResultDto;
import com.sos.fleet.common.settings.SecuritySettings;
import com.sos.fleet.common.util.JsonUtil;
import com.sos.fleet.common.util.OperationUtil;
import com.sos.fleet.common.util.SessionUtils;
import com.sos.fleet.service.VehicleBindingService;
import com.sos.fleet.service.VehicleService;

@Controller
@RequestMapping("vehicle")
@Secured(SecuritySettings.ROLE_ADMIN)
public class VehicleController extends RestClientController{
	
	static Log log = LogFactory.getLog(VehicleController.class);
	
	@Autowired
	private VehicleBindingService vehicleBindingServiceImpl;
	
	@Autowired
	private VehicleService vehicleServiceImpl;
	
	@RequestMapping("showVehicles")
	public ModelAndView showVehicles(SearchPageImpl<VehicleBindingDomain> page, VehicleBindingCondition condition) {
		
		ModelAndView view = new ModelAndView("vehicle/vehicles");
		
		page.setSize(8);
		page.setCondition(condition);
		SimpleOrder order = null;
		
		if(CollectionUtils.isEmpty(page.getOrders())) {
			page.setOrders(new ArrayList<SimpleOrder>());
			order = new SimpleOrder();
			order.setProperty(VehicleBindingDomain_.bindingDate.getName());
			order.setDirection(Direction.DESC);
			page.getOrders().add(order);
			
			order = new SimpleOrder();
			order.setProperty(VehicleBindingDomain_.id.getName());
			order.setDirection(Direction.DESC);
			page.getOrders().add(order);
		}else {
			order = new SimpleOrder();
			order.setProperty(VehicleBindingDomain_.id.getName());
			order.setDirection(Direction.DESC);
			page.getOrders().add(order);
		}
		
		page = this.vehicleBindingServiceImpl.findAll(page);
		
		view.addObject("page", page);
		view.addObject("condition", condition);
		OperationUtil.log(OperationKeys.SHOW_VECHILE,OperationTypeKeys.VEHICLE_MGMT);
		return view;
	}
	
	@RequestMapping("showBindVehicles")
	public ModelAndView showBindVehicles(SearchPageImpl<VehicleBindingDomain> page, 
			VehicleBindingCondition condition, String companyName, String msg) {
		
		ModelAndView view = new ModelAndView("user/userVehicles");
		
		page.setSize(8);
		page.setCondition(condition);
		SimpleOrder order = null;
		
		if(CollectionUtils.isEmpty(page.getOrders())) {
			page.setOrders(new ArrayList<SimpleOrder>());
			order = new SimpleOrder();
			order.setProperty(VehicleBindingDomain_.bindingDate.getName());
			order.setDirection(Direction.DESC);
			page.getOrders().add(order);
			
			order = new SimpleOrder();
			order.setProperty(VehicleBindingDomain_.id.getName());
			order.setDirection(Direction.DESC);
			page.getOrders().add(order);
		}else {
			order = new SimpleOrder();
			order.setProperty(VehicleBindingDomain_.id.getName());
			order.setDirection(Direction.DESC);
			page.getOrders().add(order);
		}
		
		page = this.vehicleBindingServiceImpl.findAll(page);
		
		view.addObject("page", page);
		view.addObject("condition", condition);
		view.addObject("companyName", companyName);
		view.addObject("msg", msg);
		OperationUtil.log(condition.getUserId(),OperationKeys.SHOW_USER_VECHILE,null,OperationTypeKeys.VEHICLE_MGMT,null);
		return view;
	}
	
	@RequestMapping("toEditVehiclePage")
	public ModelAndView toEditVehiclePage(@RequestParam Long id) {
		log.info(id+"======");
		ModelAndView view = new ModelAndView("user/vehicle");
		
		VehicleBindingDomain pojo = this.vehicleBindingServiceImpl.get(id);
		view.addObject("pojo", pojo);
		OperationUtil.log(pojo==null?(Long)null:pojo.getUserId(),OperationKeys.CLICK_UPDATE_VEHICLE,String.valueOf(id),OperationTypeKeys.VEHICLE_MGMT,null);
		return view;
	}
	
	@RequestMapping("editVehicle")
	@ResponseBody
	@PreAuthorize(value=SecuritySettings.DENY_ON_READ_ONLY_ENV)
	public VehicleBindingDomain editVehicle(VehicleBindingDomain vehiclebind) {
		
		log.info(JsonUtil.getJson(vehiclebind)+"========");
		boolean failure = false;
		String mes = null;
		try{
			this.vehicleBindingServiceImpl.updateVehicleBind(vehiclebind);
		}catch(Exception e){
			mes = e.getMessage();
			failure = !failure;
		}
		OperationUtil.log(vehiclebind==null?(Long)null:vehiclebind.getUserId(),OperationKeys.UPDATE_VEHICLE,String.valueOf(vehiclebind==null?null:vehiclebind.getId()),OperationTypeKeys.VEHICLE_MGMT,null);
		return vehiclebind;
	}
	
	@RequestMapping("downloadBindResults")
	public void isSuccResponse(HttpServletResponse response, HttpServletRequest request) {
		
		List<VehicleBindResultDto> results = (ArrayList<VehicleBindResultDto>)SessionUtils.getAttribute(request, ApplicationKeys.BIND_RESULTS);
		log.info(results.size()+"=========");
		boolean failure = false;String mes=null;
		if(!CollectionUtils.isEmpty(results)) {
			HSSFWorkbook wb = buildWorkbook(results);
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-disposition", "attachment;filename=binding_result.xls");
			OutputStream ouputStream = null ;
			try {
				response.flushBuffer();
				ouputStream = response.getOutputStream();
				wb.write(ouputStream);
			} catch (IOException e) {
				mes = e.getMessage();
				failure = true;
			}finally{
				try {
					ouputStream.flush();
					ouputStream.close();
				} catch (IOException e) {
					mes = e.getMessage();
					failure = true;
				}
				OperationUtil.log(OperationKeys.DOWNLOAD_BIND_RESULT,OperationTypeKeys.VEHICLE_MGMT,failure?StatusKeys.FAILURE:StatusKeys.SUCCESS);
				
			}
		}
		
	}
	
	@RequestMapping(value="bindVehicle")
	@PreAuthorize(value=SecuritySettings.DENY_ON_READ_ONLY_ENV)
	public void bindVehicle(@RequestParam("vbfile") MultipartFile file, @RequestParam Long userId, 
			HttpServletResponse response, 
			HttpServletRequest request) throws IOException {
		
		String status = ApplicationKeys.SUCC;
        String message = "";
        
        PrintWriter out = response.getWriter();
        log.info("bindVehicle::::::::::::::::::::");
        boolean failure=false;
        if(!file.getOriginalFilename().endsWith(".xlsx") && !file.getOriginalFilename().endsWith(".xls") ) {
        	status = ApplicationKeys.FAIL;
        	message = "onlyExcel";
        	failure=true;
        }else {
        	try {
    			List<VehicleBindingDomain> vehiclebinds = this.buildVehicleBinds(file);
    			if(vehiclebinds.size() > 100) {
    				status = ApplicationKeys.FAIL;
    				message = "excelLengthError";
    				failure=true;
    			}else {
    				List<VehicleBindResultDto> results = this.vehicleBindingServiceImpl.bindVehicle(vehiclebinds, userId);
    				SessionUtils.setAttribute(request, ApplicationKeys.BIND_RESULTS, (ArrayList<VehicleBindResultDto>)results);
    			}
    		} catch (Exception e) {
    			// TODO: handle exception
    			e.printStackTrace();
    			status = ApplicationKeys.FAIL;
    			message = "error";
    			failure=true;
    		}
        }
       
		OperationUtil.log(userId, OperationKeys.UPLOAD_VEHICLE_EXCEL, null, failure?StatusKeys.FAILURE:StatusKeys.SUCCESS, null, OperationTypeKeys.VEHICLE_MGMT, message);
        response.setContentType("text/html;charset=UTF-8");
        out.println("<script name='testsp'>parent.bindVehicleCallback('"+status+"','"+message+"')</script>");
	}
	
	private HSSFWorkbook buildWorkbook(List<VehicleBindResultDto> bindResults) {
		// TODO Auto-generated method stub
		
		String[] excelHeader = {"企业用户名", "车架号", "车牌", "车型", "使用人", "联系方式", "绑定状态", "描述"};
		int[] excelHeaderWidth = { 120, 180, 100, 100, 100, 150, 100, 500};
		VehicleBindResultDto resultDto = null;
		
		HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("sheet1");
        HSSFRow row = sheet.createRow((int) 0);
        HSSFCellStyle style = wb.createCellStyle();
        
        HSSFFont font = wb.createFont();  
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        style.setFont(font);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        
        HSSFCellStyle rowstyle = wb.createCellStyle();
        rowstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
    
        for (int i = 0; i < excelHeader.length; i++) {    
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(excelHeader[i]);
            cell.setCellStyle(style);
            sheet.setColumnWidth(i, 32 * excelHeaderWidth[i]);
        }    
    
        String[] msgs = null;
        int idx = 0;
        String key = null;
        String msg = "";
        String argument = null;
        StringBuilder comments = null;
        ResourceBundle resource = ResourceBundle.getBundle(ApplicationKeys.MESSAGE_RESOURCE);
        
        for (int i = 0; i < bindResults.size(); i++) {    
            row = sheet.createRow(i + 1);
            resultDto = bindResults.get(i);
            row.createCell(0).setCellValue(resultDto.getUserName());
            row.createCell(1).setCellValue(resultDto.getVin());
            row.createCell(2).setCellValue(resultDto.getPlateId());
            row.createCell(3).setCellValue(resultDto.getModel());
            row.createCell(4).setCellValue(resultDto.getDriver());
            row.createCell(5).setCellValue(resultDto.getTelephone());
            
            try {
				msg = new String(resource.getString("fu.message.binding."+resultDto.getStatus()).getBytes("ISO-8859-1"),"UTF-8");
            } catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            row.createCell(6).setCellValue(msg);

            if(StringUtils.isNotBlank(resultDto.getComments())) {
            	msgs = resultDto.getComments().split(",");
            	comments = new StringBuilder();
            	for (int j = 0; j < msgs.length; j++) {
            		idx = msgs[j].lastIndexOf(".");
            		key = msgs[j].substring(0, idx < 0 ? msgs[j].length() : idx);
            		if(resource != null) {
            			try {
            				msg = new String(resource.getString("fu.message.binding."+key).getBytes("ISO-8859-1"),"UTF-8");
            			} catch (UnsupportedEncodingException e) {
            				// TODO Auto-generated catch block
            				e.printStackTrace();
            			}
            		}
            		argument = idx < 0 ? null : msgs[j].substring(idx+1, msgs[j].length());
            		comments.append(j+1).append(".").append(" ");
            		comments.append(String.format(msg, argument));
            		comments.append(" ");
            	}
            	row.createCell(7).setCellValue(comments.toString());
            }
            
            row.getCell(0).setCellStyle(rowstyle);
            row.getCell(1).setCellStyle(rowstyle);
            row.getCell(2).setCellStyle(rowstyle);
            row.getCell(3).setCellStyle(rowstyle);
            row.getCell(4).setCellStyle(rowstyle);
            row.getCell(5).setCellStyle(rowstyle);
            row.getCell(6).setCellStyle(rowstyle);
        }
        
        return wb;
	}

	@RequestMapping("releaseBind")
	@ResponseBody
	@PreAuthorize(value=SecuritySettings.DENY_ON_READ_ONLY_ENV)
	public String releaseBind(@RequestParam Long id) {
		log.info(id);
		String status = "SUCCESS",mes=null;
		try {
			this.vehicleBindingServiceImpl.releaseBind(id);
		} catch (Exception e) {
			log.error(e,e);
			mes=e.getMessage();
			status = "FAILED";
		}
		if(!"SUCCESS".equals(status)){
			OperationUtil.log((Long)null, OperationKeys.RELEASE_BIND, String.valueOf(id), "SUCCESS".equals(status)?StatusKeys.SUCCESS:StatusKeys.FAILURE, OperationTypeKeys.VEHICLE_MGMT, mes);
		}
		return status;
	}
	
	public List<VehicleBindingDomain> buildVehicleBinds(MultipartFile file) throws IOException {
		
		List<VehicleBindingDomain> vehiclebinds = new ArrayList<VehicleBindingDomain>();
		VehicleBindingDomain vb = null;
    	UserDomain user = null;
    	
    	InputStream is = new ByteArrayInputStream(file.getBytes());
    	
    	Workbook workbook = new XSSFWorkbook(is);
        Sheet sheet = workbook.getSheetAt(0);
        for (int i = 1; i <= sheet.getLastRowNum(); i++)
        {
            Row row = sheet.getRow(i);
            if(isEmptyRow(row)) {
         	   continue;
            }
            vb = new VehicleBindingDomain();
            user = new UserDomain();
            user.setUserName(this.getCellValue(row.getCell(0)));
            vb.setUserDomain(user);
            vb.setVin(this.getCellValue(row.getCell(1)));
            vb.setPlateId(this.getCellValue(row.getCell(2)));
            vb.setModel(this.getCellValue(row.getCell(3)));
            vb.setDriver(this.getCellValue(row.getCell(4)));
            vb.setTelephone(this.getCellValue(row.getCell(5)));
            
//            vehicle = new VehicleDomain();
//            vehicle.setVin(vb.getVin());
//            vehicle.setPlateId(vb.getPlateId());
//            vehicle.setModel(vb.getModel());
//            vehicle.setDriver(vb.getDriver());
//            vehicle.setTelephone(vb.getTelephone());
//            vb.setVehicleDomain(vehicle);
            
            vehiclebinds.add(vb);
        }
        
        log.info("vehiclebinds size :::: "+vehiclebinds.size());
        return vehiclebinds;
		
	}
	
	public String getCellValue(Cell cell) {
		
		String value = null;
		
        if(cell == null) {
            return "";
        }
        if (cell.getCellType() == cell.CELL_TYPE_BOOLEAN) {  
            // 返回布尔类型的值  
        	value = String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {  
            // 返回数值类型的值  
        	value = new DecimalFormat("#").format(cell.getNumericCellValue());
        } else {  
            // 返回字符串类型的值  
            value = cell.getStringCellValue() == null ? "" : cell.getStringCellValue();
        }
        
        return StringEscapeUtils.escapeHtml4(value).trim();
    }
	
	public Boolean isEmptyRow(Row row) {
		
		if(row == null || row.getFirstCellNum() == -1) {
            return true;
        }
		
		boolean temp1 = StringUtils.isBlank(this.getCellValue(row.getCell(0)));
		boolean temp2 = StringUtils.isBlank(this.getCellValue(row.getCell(1)));
		boolean temp3 = StringUtils.isBlank(this.getCellValue(row.getCell(2)));
		boolean temp4 = StringUtils.isBlank(this.getCellValue(row.getCell(3)));
		boolean temp5 = StringUtils.isBlank(this.getCellValue(row.getCell(4)));
		boolean temp6 = StringUtils.isBlank(this.getCellValue(row.getCell(4)));

		return temp1 && temp2 && temp3 && temp4 && temp5 && temp6;
	}
	
}
