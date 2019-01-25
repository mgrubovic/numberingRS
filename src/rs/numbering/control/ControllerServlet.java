package rs.numbering.control;

import rs.numbering.operation.*;
import rs.numbering.format.*;
import rs.numbering.model.DataManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.io.*;


/**
 * Servlet implementation class ControllServlet
 */
@WebServlet("/ControllServlet")
public class ControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String hiddenParam = request.getParameter("select");
		System.out.println("hiddenParam " + hiddenParam);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext sContext = request.getServletContext();
		List <Range> rangesMain = (List<Range>) sContext.getAttribute("geoRange");
		
		String hiddenParam = request.getParameter("select");
		if(hiddenParam == null){
			uploadFile(request,  response, rangesMain);
		}else{

			if(hiddenParam.equals("range")){
				String mgRequest= request.getParameter("mg");
				String startRangeRequest = request.getParameter("startRange");
				String endRangeRequest = request.getParameter("endRange");
	
				SearchRanges searchRanges = new SearchRanges(rangesMain);
				Range item = new Range();
				item.setMg(mgRequest);
				item.setStartRange(startRangeRequest);
				item.setEndRange(endRangeRequest);
				List <String> answerLines = searchRanges.getDescription(item);
				System.out.println("answerLines is: " + answerLines);
				item.setDescription(answerLines);
				request.setAttribute("answerRange", item);
				
				
				//List <String> answerLines = searchRanges.getAnswers(mgRequest, startRangeRequest, endRangeRequest);
				//request.setAttribute("answerRange", answerLines);
				RequestDispatcher view = request.getRequestDispatcher("/ranges/RangeCheckResult.jsp");
				view.forward(request, response);
	
			}else if(hiddenParam.equals("number")){
				String hiddenString = request.getParameter("numbersToSend");
				SearchNumbers searchNumbers = new SearchNumbers(rangesMain);
				List <String> answerLines = searchNumbers.getAnswers(hiddenString);
				request.setAttribute("answers", answerLines);
				RequestDispatcher view = request.getRequestDispatcher("/numbers/NumberCheckResult.jsp");
				view.forward(request, response);
			}else if(hiddenParam.equals("file")){
				System.out.println("hiddenParam " + hiddenParam);
	
			}else{
				System.out.println("No catch for " + " hiddenParam  "+ hiddenParam);
			}
		}
	}
	
	public void uploadFile1(HttpServletRequest request, HttpServletResponse response, List <Range> rangesMain)throws ServletException, IOException{
		String fileName=null;
		File uploadedFile=null;
		String filePath = request.getServletContext().getRealPath("/data");
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		try{
			List<FileItem>   fileItems =  upload.parseRequest(request);
			
			System.out.println("fileItems.size is: " + fileItems.size() + " ;0- "+fileItems.get(0) + " ;1- " + fileItems.get(1));

			FileItem fi=  fileItems.get(1);

			fileName = filePath +"\\" + fi.getName() ;
			uploadedFile = new File(fileName);

			fi.write(uploadedFile);

		}catch(Exception ex){
			System.out.println(ex);
		}
		DataManager dm = new DataManager();
		String secondFormat = "shortForm";
		List <Range> uploadRanges = dm.getRanges( secondFormat, fileName);
		
		SearchRanges searchRanges = new SearchRanges(rangesMain);
		for(int i=0; i<uploadRanges.size(); i++){
			Range item = uploadRanges.get(i);
			List <String> answerLines = searchRanges.getDescription(item);
			item.setDescription(answerLines);
		
		}
		request.setAttribute("compareRanges", uploadRanges);
		

		RequestDispatcher view = request.getRequestDispatcher("/compare/CompareResult.jsp");
		view.forward(request, response);

	}
	
	public void uploadFile(HttpServletRequest request, HttpServletResponse response, List <Range> rangesMain)throws ServletException, IOException{
		String fileName=null;
		String name = null;
		String filePath = request.getServletContext().getRealPath("/data");
		
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);

		try{
			   List<FileItem> items = upload.parseRequest(request);
			   Iterator<FileItem> iter = items.iterator();
			   while (iter.hasNext()) {
			        FileItem item = iter.next();
			        if (item.isFormField()) {
			            InputStream input = item.getInputStream();
			            if(item.getFieldName().equals("select")){
			                byte[] str = new byte[input.available()];
			                input.read(str);
			                name = new String(str,"UTF8");
			                System.out.println("Hidden param select is: " + name);
			            }
			        }else{
			        	FileItem fi =  item;
			        	fileName = filePath + File.pathSeparator + fi.getName() ;
						fi.write(new File(fileName));
			        }
			    }
			System.out.println("fileItems.size is: " + items.size() + " ;0- "+items.get(0) + " ;1- " + items.get(1));
		}catch(Exception ex){
			System.out.println(ex);
		}
		DataManager dm = new DataManager();
		String secondFormat = "shortForm";
		List <Range> uploadRanges = dm.getRanges( secondFormat, fileName);
		
		SearchRanges searchRanges = new SearchRanges(rangesMain);
		for(int i=0; i<uploadRanges.size(); i++){
			Range item = uploadRanges.get(i);
			List <String> answerLines = searchRanges.getDescription(item);
			item.setDescription(answerLines);
		
		}
		request.setAttribute("compareRanges", uploadRanges);

		RequestDispatcher view = request.getRequestDispatcher("/compare/CompareResult.jsp");
		view.forward(request, response);

	}
	

}
