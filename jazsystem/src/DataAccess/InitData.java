package DataAccess;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.metal.MetalMenuBarUI;

import Application.AccountEntry;
import Application.AccountMap;
import Application.Address;
import Application.Author;
import Application.Book;
import Application.CheckOutEntry;
import Application.CheckOutRecord;
import Application.Copy;
import Application.Item;
import Application.Member;
import Application.Periodical;
import UI.SystemController;

public class InitData {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

/*
Member member=new Member();
MemberDataAccess MDA =new MemberDataAccess();
MDA.saveMember(1, member);

Author author =new Author();
AuthorDataAccess ADA=new AuthorDataAccess();
ADA.saveAuthor(1, author);

Periodical periodical=new Periodical();
periodicalDataAccess PDA =new periodicalDataAccess();
PDA.savePeriodival(1, periodical);
*/
		
	/*	
		Member member=new Member("abdel", "faddaoui", "0664596720", new Address("1000 N", "fairfield", "IOWA", 52557), 0123,
				new CheckOutRecord());	
		member.getCheckoutrecord().addCheckOutEntry(new CheckOutEntry(LocalDate.now(), LocalDate.now(),
				new Copy()));
		
		
		System.out.println(member.getLastname()+"**address**"+member.getAddress().getCity()+"**checkoutrecord**"+
				member.getCheckoutrecord().getCheckoutID()+"**checkout entry date**"+member.getCheckoutrecord().getCheckoutentries().get(0).getM_CheckoutDate()+"book int "+
				member.getCheckoutrecord().getCheckoutentries().get(0).getBookORperiodical().getItemID()); 
		
		MemberDataAccess MDA =new MemberDataAccess();
		MDA.saveMember(1, member);
		
		MemberDataAccess MD =new MemberDataAccess();
		Member membe=new Member();
		membe=MD.readMember(1);
		
		System.out.println(membe.getLastname()+"**address**"+membe.getAddress().getCity()+"**checkoutrecord**"+
				membe.getCheckoutrecord().getCheckoutID()+"**checkout entry date**"+membe.getCheckoutrecord().getCheckoutentries().get(0).getM_CheckoutDate()+"book int "+
				membe.getCheckoutrecord().getCheckoutentries().get(0).getBookORperiodical().getItemID()); 
		
		/*
		 * hard code interface user
		 */
		AccountEntry user1=new AccountEntry(1,"joy","123",true,true);
		AccountEntry user2=new AccountEntry(2,"zhitian","123",true,false);
		AccountEntry user3=new AccountEntry(3,"abdel","123",false,true);
		AccountMap map=new AccountMap();
		map.addEntry(user1);
		map.addEntry(user2);
		map.addEntry(user3);
		AccountMapDataAccess amda=new AccountMapDataAccess();
		amda.saveAccountMap( map);
		System.out.println(map.getEntry("joy").getPassword());
		AccountMapDataAccess ama=new AccountMapDataAccess();
		ama.readAccountMap();
		System.out.println(map.getEntry("joy").getPassword());
		System.out.println(LocalDate.of(1989, 11, 19));
		/*
		 * List book periodical member
		 */
		List<Book> booklist=new ArrayList<Book>();
		List<Periodical> periodicallist =new ArrayList<Periodical>();
		List<Member> memberlist=new ArrayList<Member>();
		
		/*
		 * harde code book
		 */
				/****Book1*****/
		List<Author> authors1=new ArrayList<Author>();
		
		authors1.add(new Author("Michel","hanks","87698876",new Address("1N ","sanDiego","CA",44257),"Physics nobel","born in atlanata in 1962"));
		authors1.add(new Author("halima","messoud","645811292",new Address("10N st ","new york","NY",17761),"classy fan","we cant"));

		Book book1=new Book("9781234567801", LocalDate.of(1989, 11, 19), "Le livre de la mort", authors1,new ArrayList<Copy>()
				, true, 21);
		
		List<Copy> copy1=new ArrayList<Copy>();
		copy1.add(new Copy(true,1,book1.getItemID()));
		copy1.add(new Copy(true,2,book1.getItemID()));
		copy1.add(new Copy(true,3,book1.getItemID()));
		book1.setCopies(copy1);
		booklist.add(book1);
		/****Book2*****/
		List<Author> authors2=new ArrayList<Author>();
		authors2.add(new Author("Michel","hanks","87698876",new Address("2768st ","sanDiego","CA",44257),"Physics nobel","born in atlanata in 1962"));
		authors2.add(new Author("slater","kelly","87999876",new Address("19st ","santa barbara","FL",44347),"chimie","bndkh"));
		authors2.add(new Author("nissrine","laajaj","64596720",new Address("190st ","norhore","CA",14378),"1 million fan","we cant"));

		Book book2=new Book("9781234567802", LocalDate.of(1971, 01, 10), "the art of living", authors2, new ArrayList<Copy>(),
		true, 7);
		
		List<Copy> copy2=new ArrayList<Copy>();
		copy2.add(new Copy(true,1,book2.getItemID()));
		copy2.add(new Copy(true,2,book2.getItemID()));
		copy2.add(new Copy(true,3,book2.getItemID()));
		book2.setCopies(copy2);
		booklist.add(book2);
		/****Book3*****/
		List<Author> authors3=new ArrayList<Author>();
		authors3.add(new Author("amal","hyani","645882782",new Address("1N st ","burnind","OH",14321),"10 awards","we cant"));
		authors3.add(new Author("pupo","miguel","81119876",new Address("1st ","vegas","TX",98767),"soccerplayer","no idea"));
		Book book3=new Book("9781234567803", LocalDate.of(1990, 01, 29), "mastering the cold", authors3, new ArrayList<Copy>(),
		true, 7);
		
		Copy copy3=new Copy(true,1,book3.getItemID());
		book3.addCopy(copy3);
		booklist.add(book3);
		/****Book4*****/
		List<Author> authors4=new ArrayList<Author>();
		authors4.add(new Author("Andy","Irons","879900296",new Address("19st ","north shore","HI",12281),"besteseller","indisponible"));
		Book book4=new Book("9781234567804", LocalDate.of(1971, 01, 10), "the art of living", authors4, new ArrayList<Copy>(),
		true, 21);
		
		Copy copy4=new Copy(true,1,book4.getItemID());
		book4.addCopy(copy4);
		booklist.add(book4);
		System.out.println(book4.getCopies().get(0).isAvailable()+"%%%%%%"+book4.getItemID());
		/*
		 * harde code periodical
		 */
		/****periodical1*****/
				Periodical periodical5=new Periodical("9771234567801", LocalDate.of(1980, 01, 01), "ITmagazine", new ArrayList<Copy>(),
		true, 7);
		
		List<Copy> copy5=new ArrayList<Copy>();
		copy5.add(new Copy(true,1,periodical5.getItemID()));
		copy5.add(new Copy(true,2,periodical5.getItemID()));
		periodical5.setCopies(copy5);
		periodicallist.add(periodical5);
		/****periodical2*****/
		Periodical periodical6=new Periodical("9771234567802", LocalDate.of(2000, 03, 21), "gentelman magazine",  new ArrayList<Copy>(),
		true, 7);
		
		List<Copy> copy6=new ArrayList<Copy>();
		copy6.add(new Copy(true,1,periodical6.getItemID()));
		copy6.add(new Copy(true,2,periodical6.getItemID()));
		periodical6.setCopies(copy6);
		periodicallist.add(periodical6);
		/****periodical3*****/
		Periodical periodical7=new Periodical("9771234567803", LocalDate.of(2010, 9, 21), "fashion magazine", new ArrayList<Copy>(),
		true, 7);
		
		List<Copy> copy7=new ArrayList<Copy>();
		copy7.add(new Copy(true,1,periodical7.getItemID()));
		copy7.add(new Copy(true,2,periodical7.getItemID()));
		periodical7.setCopies(copy7);
		periodicallist.add(periodical7);
		
		/*
		 * harde code member
		 */
		
		/****member1*****/
	
		Member member1=new Member(98001,"abdelillah","faddaoui","6414513125",new Address("1000 N ","fairfield","IA",52557)
				,new CheckOutRecord());
		memberlist.add(member1);
		/****member2*****/
		
		Member member2=new Member(98002,"joy","genious","6412353125",new Address("1000 N ","fairfield","IA",52557)
				,new CheckOutRecord());
		memberlist.add(member2);
		/****member3*****/
		
		Member member3=new Member(98003,"zhitian","robert","6414513120",new Address("1000 N ","fairfield","IA",52557)
				,new CheckOutRecord());
		memberlist.add(member3);
		/****member4*****/
		
		Member member4=new Member(98004,"tina","prof","6414513100",new Address("4th ST ","fairfield","IA",52556)
				,new CheckOutRecord());
		memberlist.add(member4);
	/*	LocalDate m_CheckoutDate, LocalDate m_DueDate, LocalDate datereturned, boolean isreturned,Copy copy
		member1.getCheckoutrecord().addCheckOutEntry();
	*/	DataAccess data=new DataAccess();
		for( Member   a: memberlist){
			data.saveMember(a.getMemberID(), a);
		}
		int i=0;
		for(Book a:booklist){
			i++;
			data.saveBook(a.getItemID(), a);
			System.out.println("**"+i+"**");
		}
		for(Periodical a:periodicallist){
			data.savePeriodical(a.getItemID(), a);
		}
		
		Member member =data.readMember(3);
		System.out.println("**memeber**"+member.getFirstname()+"****"+member.getAddress().getStreet()+"#####");
		Periodical periodical=data.readPeriodical("P9770216790535");
		System.out.println("****"+periodical.getMax_day_borrow()+"****"+"####"+periodical.getCopies().get(0).getItemID()+"####"+"$$$$$$");
		Book book= data.readBook("B9780355358210");
		if(book == null){
			System.out.println("****Book doesnt exist****");
		}
		System.out.println("****"+book.getTitle()+"****"+book.getAuthors().get(0).getFirstname()+"####"+book.getCopies().get(0).getItemID()+"####"+book.getAuthors().get(0).getAddress().getState()+"$$$$$$");
	
		SystemController sys=new SystemController();
		/////////////check out ////////////
		sys.CheckOutBook(member4, book1);
		sys.CheckOutBook(member3, book2);
		sys.CheckOutBook(member2, book1);
		sys.CheckOutBook(member4, book3);
		sys.CheckOutBook(member1, book1);
		
		sys.Checkout(member1, periodical5);
		sys.Checkout(member2, periodical6);
		
		for( Member   a: memberlist){
			data.saveMember(a.getMemberID(), a);
		}
		
		for(Book a:booklist){
			i++;
			data.saveBook(a.getItemID(), a);
			System.out.println("**"+i+"**");
		}
		for(Periodical a:periodicallist){
			data.savePeriodical(a.getItemID(), a);
		}
		
		
		
		member =data.readMember(3);
		System.out.println("**memeber**"+member.getFirstname()+"****"+member.getAddress().getStreet()+"#####"+member.getCheckoutrecord().getCheckoutentries().get(0).getM_CheckoutDate()+"^^^^^^"+member.getCheckoutrecord().getCheckoutentries().get(0).getCopy().getCopiID()+"@@@@@"+member.getCheckoutrecord().getCheckoutentries().get(0).getCopy().isAvailable());
		periodical=data.readPeriodical("P9770531698362");
		System.out.println("****"+periodical.getMax_day_borrow()+"****"+"####"+periodical.getCopies().get(0).getItemID()+"####"+"$$$$$$");
		   
		
		
		System.out.println((data.readMember(memberlist.get(0).getMemberID())).getCheckoutrecord().getCheckoutentries().get(1).getM_DueDate());
	
		System.out.println(data.getNextMemberId());
		
		sys.printMemberCheckout(98002);
	}

}
