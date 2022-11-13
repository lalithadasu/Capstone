import java.io.*;  
import javax.servlet.*;  
import javax.servlet.http.*;

import jakarta.servlet.annotation.WebServlet;  

@WebServlet("/bgimg")
public class bgimg extends HttpServlet {  
	private static final long serialVersionUID = 1L;
	
	public bgimg() {
        super();
        // TODO Auto-generated constructor stub
    }
	
    public void doGet(HttpServletRequest request,HttpServletResponse response)  
             throws IOException  
    {  
    response.setContentType("image/jpeg");  
    ServletOutputStream out;  
    out = response.getOutputStream();  
    FileInputStream fin = new FileInputStream("/home/lalitha/Documents/bg.img");  
      
    BufferedInputStream bin = new BufferedInputStream(fin);  
    BufferedOutputStream bout = new BufferedOutputStream(out);  
    int ch =0; ;  
    while((ch=bin.read())!=-1)  
    {  
    bout.write(ch);  
    }  
      
    bin.close();  
    fin.close();  
    bout.close();  
    out.close();  
    }  
}  