package com.jerome.excelImport;

import net.sf.json.JSONObject;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @ClassName: ExportExcelServlet 
 * @Description: 导入
 * @author jerome
 * @date 2015年11月3日 下午5:11:46 
 * index.jsp 10M电信开卡数据表-电信提供-导入模版.xls
 *	<dependency>
		<groupId>org.apache.poi</groupId>
		<artifactId>poi</artifactId>
		<version>3.9</version>
	</dependency>
	<dependency>
		<groupId>org.apache.poi</groupId>
		<artifactId>poi-ooxml</artifactId>
		<version>3.9</version>
	</dependency>
 */
@WebServlet(name = "importExcel", urlPatterns = { "/importExcel" })
@MultipartConfig//标识Servlet支持文件上传
public class ImportExcelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ImportExcelServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Part part = request.getPart("file");
		InputStream inputStream = part.getInputStream();
		JSONObject json = null;
		try {
			json = importCardExcel(inputStream);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		}
		
//		DWZUtil.writer(response, json);
		PrintWriter writer;
		try {
			response.setCharacterEncoding("UTF-8");
			writer = response.getWriter();
			writer.print(json);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	/**
	 * 导入开卡数据
	 * 
	 * @param inputStream
	 *            excel
	 * @return
	 * @throws IOException
	 * @throws InvalidFormatException
	 */
	public JSONObject importCardExcel(InputStream inputStream) throws InvalidFormatException, IOException {

		List<Card> cards = new ArrayList<Card>(); // 存放要插入数据库的集合
		if (inputStream.available() == 0) {
//			return DWZUtil.returnFailedJson("文件为空", "", "", "");
		}

		org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(inputStream);
		Sheet sheet = workbook.getSheetAt(0);
		Iterator<Row> rows = sheet.rowIterator();
		// 校验
		int count = 0;
		Card card = null;
		while (rows.hasNext()) {
			count++;
			Row row = rows.next();
			// 如果不是String类型统一设置为String
			if (row.getCell(1).getCellType() == 0 || row.getCell(1).getCellType() == 2
					|| row.getCell(1).getCellType() == 3 || row.getCell(1).getCellType() == 4) {
				row.getCell(1).setCellType(1);
			}
			if (row.getCell(2).getCellType() == 0 || row.getCell(2).getCellType() == 2
					|| row.getCell(2).getCellType() == 3 || row.getCell(2).getCellType() == 4) {
				row.getCell(2).setCellType(1);
			}
			if (row.getCell(3).getCellType() == 0 || row.getCell(3).getCellType() == 2
					|| row.getCell(3).getCellType() == 3 || row.getCell(3).getCellType() == 4) {
				row.getCell(3).setCellType(1);
			}

			if (count > 2) {
//				if (StringUtil.isEmpty(row.getCell(1).getStringCellValue())) {
//					return DWZUtil.returnFailedJson("第" + (count - 2) + "行的iccid不能为空", "", "", "");
//				}
//
//				if (findCardByIccid(row.getCell(1).getStringCellValue()) != null) {
//					return DWZUtil.returnFailedJson("第" + (count - 2) + "行的iccid已存在", "", "", "");
//				}

				card = new Card();
				card.setIccid(row.getCell(1).getStringCellValue());
				card.setPhone(row.getCell(2).getStringCellValue());
				card.setRemark(row.getCell(3).getStringCellValue());
				cards.add(card);
			}
		}

		for (Card c : cards) { 
//			boolean saveResult = save(c);
//			if (!saveResult) {
//				return DWZUtil.returnFailedJson("iccid：" + c.getIccid() + "保存失败请重试", "", "", "");
//			}
			System.out.println(c.toString());

		}
//		return DWZUtil.returnSuccessJson("上传成功", "", "", "");
		return JSONObject.fromObject("{'msg':'ok'}");
	}
	
}
