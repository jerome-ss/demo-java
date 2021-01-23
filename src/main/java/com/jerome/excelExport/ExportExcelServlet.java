package com.jerome.excelExport;

//import com.aspose.cells.Workbook;
//import com.aspose.cells.WorkbookDesigner;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.text.ParseException;


@WebServlet(name = "exportExcel", urlPatterns = { "/exportExcel" })
public class ExportExcelServlet extends HttpServlet {
	/*private static final long serialVersionUID = 1L;
       
    public ExportExcelServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			// 创建工作薄
			ServletContext sc = request.getSession().getServletContext();
			// 模版所在位置
			String templatePath = sc.getRealPath("/data/cardApply.xls");

			// 创建工作薄加载模板
			Workbook wb = new Workbook(templatePath);
			WorkbookDesigner designer = new WorkbookDesigner();
			designer.setWorkbook(wb);
			designer.setDataSource("Card", getExportShipments());
			designer.process(true);

			// 设置输出响应头
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.addHeader("content-disposition", "inline;filename=" + "cardApply.xls");
			
			// 向客户端输出
			sendExcelReport(request, response, wb);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	*//**
	 * 向客户端返回excel
	 * 
	 * @param request
	 * @param response
	 * @param wb
	 * @throws Exception
	 *//*
	protected void sendExcelReport(HttpServletRequest request, HttpServletResponse response, Workbook wb)
			throws Exception {

		// 定义内存流
		ByteArrayOutputStream bos = null;
		ByteArrayInputStream bis = null;
		try {

			// 将excel写入内存中,这时内存中的excel就已经加上了sheet
			bos = new ByteArrayOutputStream();
			wb.save(bos, wb.getFileFormat());

			// 通过POI读取内存中的excel
			bis = new ByteArrayInputStream(bos.toByteArray());
			HSSFWorkbook xwb = new HSSFWorkbook(bis);

			// 删掉aspose生成的试用标记
			xwb.removeSheetAt(xwb.getNumberOfSheets() - 1);

			// 设置显示excel第一页
			xwb.setActiveSheet(0);

			// 向客户端输出
			xwb.write(response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(null != bos){
				bos.close();
			}
			if(null != bis){
				bis.close();
			}
		}
	}
	
	*//**
	 * 得到放如Excel的数据
	 * 
	 * @return
	 * @throws ParseException
	 *//*
	public List<ExportShipment> getExportShipments() {

		List<ExportShipment> list = new ArrayList<ExportShipment>();
		
		for (int i = 0; i < 20; i++) {
			ExportShipment e = new ExportShipment();
			e.setIndex(i);
			e.setIccid("123" + i);
			e.setCity("厦门" + i);
			e.setProvince("福建" + i);
			e.setPhone("12312312312");
			e.setRemark("备注1" + i);
			list.add(e);
		}
		
		return list;
	}*/

}
