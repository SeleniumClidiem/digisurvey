package Utilities_Digi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_QueType {
	static Statement stmt = null;   
	static ResultSet rs = null;
	static Connection conn=null;
	static String i;
	public static String  Db_qtype(String quizname, String quetext) throws ClassNotFoundException, SQLException
	{
	
		//FEIN_No=1;
		//FunctionalLibraries fl = new FunctionalLibraries();
		//ExcelUtils RC = new ExcelUtils("TestData\\RegisterCompany.xlsx");
		//fl.entervalueByName("", "", RC.getStringCellData(i, 5, "Sheet1"),"", "", "", "", "");
		
		/*String connectionUrl = "jdbc:sqlserver://198.71.226.6:1433;" +  
		         "databaseName=Clidiem_V1;user=clidiem;password=Clidiem@123";  */
		
		String connectionUrl = "jdbc:sqlserver://SERVER-PC:1433;" +  
		         "databaseName=DigiSurvey_26_Oct;user=sa;password=DBserver@123";   
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(connectionUrl);
			stmt = conn.createStatement(); 
			/*String quizID = "SELECT QuizId FROM Tbl_LQuizzes where QuizName='"+quizname+"'"; //Get quizId based on quizname
			String quiz_ID=quizID;
			System.out.println(quizID);
			
			String answertype_code = "SELECT AnswerTypeId FROM Tbl_LQuizQuestions where (QuizId="+quiz_ID+")"+" && Question='"+quetext+"'"+"'";
			String answer_code=answertype_code;
			System.out.println(answer_code);
			String quetype="SELECT AnswerType FROM Tbl_MAnswerTypes where AnswerTypeId='"+answer_code+"'";
			//String quetype="SELECT AnswerType FROM Tbl_MAnswerTypes where AnswerTypeId='"+"SELECT AnswerTypeId FROM Tbl_LQuizQuestions where QuizId="+"SELECT QuizId FROM Tbl_LQuizzes where QuizName='"+quizname+"'"+""+" && Question='"+quetext+"'"+"'"+"'";
			System.out.println(quetype);
			String SQL=quetype;*/
			String SQL="SELECT AnswerType FROM Tbl_MAnswerTypes where AnswerTypeId= (SELECT AnswerType FROM Tbl_LQuizQuestions where QuizId=(SELECT QuizId FROM Tbl_LQuizzes where QuizName='"+quizname+"') AND Question='"+quetext+"')";
			rs = stmt.executeQuery(SQL);
			
		 while (rs.next()) {  
	            //System.out.println(rs.getString("VerificationCode"));  
	            i=rs.getString("AnswerType");
	            System.out.println("Question Type is "+i);
	            
	         }
		 return i;
		
	}
	public static String  Db_qtype_Survey(String surveyname, String quetext) throws ClassNotFoundException, SQLException
	{
		String connectionUrl = "jdbc:sqlserver://SERVER-PC:1433;" +  
		         "databaseName=DigiSurvey_26_Oct;user=sa;password=DBserver@123";   
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			conn = DriverManager.getConnection(connectionUrl);
			stmt = conn.createStatement();
			//String SQL="SELECT AnswerType FROM Tbl_MAnswerTypes where AnswerTypeId= (SELECT AnswerType FROM Tbl_LSurveyQuestions where Questionnaire=(SELECT QuestionnaireId FROM Tbl_LSurveys where QuestionnaireName='"+surveyname+"') AND Question='"+quetext+"')";
			String SQL="Select MAT.AnswerType FROM Tbl_LSurveyQuestions SQ Inner JOIN Tbl_LSurveyQuestionsRelationship SQR ON  SQR.QuestionId = SQ.QuestionId Inner JOIN Tbl_MAnswerTypes MAT ON  MAT.AnswerTypeId = SQ.AnswerType Inner JOIN Tbl_LSurveys Ls ON  Ls.QuestionnaireId = SQR.SurveyId  where SQ.Question = '"+quetext+"'  AND Ls.QuestionnaireName = '"+surveyname+"'";
			rs = stmt.executeQuery(SQL);
			
		 while (rs.next()) {  
	            //System.out.println(rs.getString("VerificationCode"));  
	            i=rs.getString("AnswerType");
	            System.out.println("Question Type is "+i);
	            
	         }
		 return i;
		
	}
}
