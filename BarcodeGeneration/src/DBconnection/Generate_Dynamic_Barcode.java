package DBconnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import DBconnection.DBConnection;
import image.Image;

import Pdf.PDF;
@SuppressWarnings("unused")
public class Generate_Dynamic_Barcode {
	public static void main(String[] args) throws ClassNotFoundException {
		 	PreparedStatement ps=null;
			Connection connection=null;
			DBConnection obj_DBConnection=new DBConnection();
			connection=obj_DBConnection.getConnection();
			ResultSet rs=null;
		try { 
			String query="select * from user";
			ps = connection.prepareStatement(query);
			rs=ps.executeQuery();
			while(rs.next()){
				Image.createImage(rs.getString("Name")+".png", rs.getString("Mobile"));
				PDF.createPDF(rs.getString("Name")+".pdf", rs.getString("Name"));
				System.out.println("Creating Barcode for "+rs.getString("Mobile"));
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(connection!=null){
				try {
						connection.close();
					}
				 catch (Exception e2) {
					e2.printStackTrace();
				}
			}
			if(ps!=null){
				try {
					ps.close();
					}
				 catch (Exception e2) {
					 e2.printStackTrace();
				}
			}
		}
		}	
}

