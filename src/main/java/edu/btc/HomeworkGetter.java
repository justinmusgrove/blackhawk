package edu.btc;

import java.io.File;
import java.io.IOException;

import com.google.common.io.Files;

/**
 * <p>This program will be used to retrieve homework from student drives.  This 
 * eliminates the need to email the instructor and allows for a much more efficient 
 * way to get all programs.</p>  
 * 
 * <p>Home should following instructions for homework assignments:
 * 
 * 	Use the @author tag to properly identify use
 * 	Assignment Name: ch#{key}#.{ext}
 * 		KEY
 * 			ch - Chapter
 *			ex - Review Questions and Exercises
 *			ch - Programming Challenge
 *			po - Predict the Output
 *			pr - Predict the Result
 *			sa - Short Answer
 *			aw - Algorithm Workbench
 *			fe - Find the Error
 *			mc - multiple choice
 * 
 *	Location: H:\JAVA\Homework\
 *				
 *	Extension:
 *			.java
 *			.doc
 * 
 * @author Justin Musgrove
 *
 */
public class HomeworkGetter {
	
	private static String STUDENTS_BASE_URL = "\\\\admin.blackhawk.edu\\btc\\Home_Dir\\Students";
	private static String STUDENTS_COMPLETED_HOMEWORK_FOLDER = "\\JAVA\\Homework";
	private static String INSTRUCTOR_BASE_DIR = "H:\\152-143IntroToJava\\2013";

	private static String [] STUDENT_USER_NAME = {
	};

	private static String[] assignmentArray = {
	};
	
	/**
	 * Loop through each of the students name and retrieve homework, copy to instructor location, output when file doesn't exist
	 * 
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		String fileWithFileUrl_ToGet, fileWithFileUrl_ToWrite, fileUrl_ToWrite, fileName  = null;
		for (int x=0; x < STUDENT_USER_NAME.length; x ++) {
			
			for (int y=0; y < assignmentArray.length; y++) { 
				fileName = assignmentArray[y];
				
				fileWithFileUrl_ToGet = STUDENTS_BASE_URL + "\\" + STUDENT_USER_NAME[x]  + STUDENTS_COMPLETED_HOMEWORK_FOLDER + "\\" + fileName ;
				fileUrl_ToWrite = INSTRUCTOR_BASE_DIR + "\\" + STUDENT_USER_NAME[x];
				fileWithFileUrl_ToWrite = fileUrl_ToWrite + "\\" + fileName;
			    
				try {
					createStudentDirectory (fileUrl_ToWrite);
					moveFile (fileWithFileUrl_ToGet, fileWithFileUrl_ToWrite);
					//System.out.println("User: " + STUDENT_USER_NAME[x] + " Assignment: " + fileWithFileUrl_ToGet + " copied to location " + fileWithFileUrl_ToGet);
					
				} catch (IOException e) {
					//e.printStackTrace();
					System.out.println("-User: " + STUDENT_USER_NAME[x] + " Assignment not present: " + fileName + " at location " + fileWithFileUrl_ToGet);
				}
			}
		}
	}
	
	/**
	 * Method should create any necessary but nonexistent parent directories of the specified file.
	 * @param dir
	 * @throws IOException
	 */
	private static void createStudentDirectory (String dirToCreate) throws IOException {
		File newFile = new File( dirToCreate, "test.dat" );
	    Files.createParentDirs( newFile );
	}
	
	/**
	 * Method should move file from one location to another
	 * 
	 * @param filePathFrom
	 * @param filePathTo
	 * @throws IOException
	 */
	private static void moveFile(String filePathFrom, String filePathTo) throws IOException {
		Files.copy(new File(filePathFrom), new File (filePathTo));
	}
	
}
