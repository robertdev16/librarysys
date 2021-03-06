package DataAccess;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

import Application.Author;
import Application.Book;
import Application.Member;
import Application.Periodical;

public class DataAccess {
	public static final String separator=System.getProperty("file.separator"); 
	public static final String OUTPUT_DIR = System.getProperty("user.dir") 
			+ separator+"doc"+separator+"Storage"+separator;
	
	public int generateID(String folder){
		File directory = new File(OUTPUT_DIR+folder);
        //get all the files from a directory
        File[] fList = directory.listFiles();
        if(fList.length==0){
        	return 1;
        }else{
        int i=0;
        int max=0;
        for (File file : fList){
            if (file.isFile()){
                
                try{
                i=Integer.parseInt(file.getName());
                }
                catch(Exception e){
                	System.out.println("file not correct");
                }
                finally{
                	if(max<i){
                		max=i;
                	}
                }
            }
        }
        if(i==0){
        	return 1;
        }
        else{
        	return max+1;
        }
        }
		
		
	}
	
	
	public int generateAuthorID(){
		return generateID("Author");
	}
	
	public int generateMemberID(){
		return generateID("Member");
	}
	
	
	public void saveMember(int memberkey, Member member) {
		// TODO Auto-generated method stub
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR+"Member", String.valueOf(memberkey));
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(member);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch(Exception e) {}
			}
		}
		
	}
	
	public Member readMember(int memberkey) {
		// TODO Auto-generated method stub
		ObjectInputStream in = null;
		Member member = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR+"Member", String.valueOf(memberkey));
			File f = new File(OUTPUT_DIR+"Member"+separator+String.valueOf(memberkey));
			if (!f.exists()){
				System.out.println("member doesnt exist");
				return null;
			}
			in = new ObjectInputStream(Files.newInputStream(path));
			member = (Member)in.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch(Exception e) {}
			}
		}
		return member;
	}
	
	public int getNextMemberId(){
		ObjectInputStream in = null;
		Member member = null;
		int maxId = 0;
		File[] subFiles;
		try {
			File dirName = new File(OUTPUT_DIR+"Member");
			if (!dirName.exists()){
				System.out.println("Member directory exist");
				return 0;
			}
			subFiles = dirName.listFiles();
			for (int i=0; i<subFiles.length; i++){
				in = new ObjectInputStream(Files.newInputStream(subFiles[i].toPath()));
				member = (Member)in.readObject();
				if (member.getMemberID() > maxId)
					maxId = member.getMemberID();
			}			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch(Exception e) {}
			}
		}
		
		return maxId + 1;
	}
	
	public void savePeriodical(String periodicalkey, Periodical periodical) {
		// TODO Auto-generated method stub
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR+"Periodical", periodicalkey);
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(periodical);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch(Exception e) {}
			}
		}
		
	}
	
	public Periodical readPeriodical(String periodicalkey) {
		// TODO Auto-generated method stub
		ObjectInputStream in = null;
		Periodical periodical = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR+"Periodical", periodicalkey);
			File f = new File(OUTPUT_DIR+"Periodical"+separator+periodicalkey);
			if (!f.exists()){
				System.out.println("periodical doesnt exist");
				return null;
				
			}
			in = new ObjectInputStream(Files.newInputStream(path));
			periodical = (Periodical)in.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch(Exception e) {}
			}
		}
		return periodical;
	}
	
	public void saveBook(String ISBN, Book book) {
		// TODO Auto-generated method stub
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR+"Book", ISBN);
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(book);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch(Exception e) {}
			}
		}
		
	}
	
	public Book readBook(String ISBN) {
		// TODO Auto-generated method stub
		ObjectInputStream in = null;
		Book book = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR+"Book", ISBN);
			File f = new File(OUTPUT_DIR+"Book"+separator+ISBN);
			if (!f.exists()){
				System.out.println("Book doesnt exist");
				return null;
				
			}
			in = new ObjectInputStream(Files.newInputStream(path));
			book = (Book)in.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch(Exception e) {}
			}
		}
		return book;
	}
	
	public void saveAuthor(int authorkey, Author author) {
		// TODO Auto-generated method stub
		ObjectOutputStream out = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR+"Author", String.valueOf(authorkey));
			out = new ObjectOutputStream(Files.newOutputStream(path));
			out.writeObject(author);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			if(out != null) {
				try {
					out.close();
				} catch(Exception e) {}
			}
		}
		
	}
	
	public Author readAuthor(int authorkey) {
		// TODO Auto-generated method stub
		ObjectInputStream in = null;
		Author author = null;
		try {
			Path path = FileSystems.getDefault().getPath(OUTPUT_DIR+"Author", String.valueOf(authorkey));
			File f = new File(OUTPUT_DIR+"Author"+separator+String.valueOf(authorkey));
			if (!f.exists()){
				System.out.println("Book doesnt exist");
				return null;
				
			}
			in = new ObjectInputStream(Files.newInputStream(path));
			author = (Author)in.readObject();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(in != null) {
				try {
					in.close();
				} catch(Exception e) {}
			}
		}
		return author;
	}
	
	
	
}
