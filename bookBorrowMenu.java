import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.text.*;

public class bookBorrowMenu extends Frame implements ActionListener{
	Book[] bookShelf;

	String dateBorrowed;

	Label bookTitleLabel, bookBorrowerLabel, bookBorrowedDate;
	TextField bookTitleTF, bookBorrowerTF, bookBorrowedDateTF;
	Button releaseBookButton, clearAllButton, exitMenuButton;
    Label bookBorrowedDateFormat;
    Button showBooksButton;

    java.awt.List listOfBooks;

	TextField bookStatusTF;



	public bookBorrowMenu(Book[] bookArray){
		bookShelf = new Book[100];
		bookShelf = bookArray;

		setLayout(null);
		setSize(305,450);
		setResizable(false);
		setVisible(false);
		setTitle("Book Borrowing Menu");


		bookTitleLabel = new Label("TITLE");
		bookTitleLabel.setBounds(20, 45, 60, 25);
		add(bookTitleLabel);

		bookTitleTF = new TextField();
		bookTitleTF.setBounds(20, 70, 266, 25);
		add(bookTitleTF);
		bookTitleTF.setText(null);


		bookBorrowerLabel = new Label("Borrower");
		bookBorrowerLabel.setBounds(20, 105, 80, 25);
		add(bookBorrowerLabel);

		bookBorrowerTF = new TextField();
		bookBorrowerTF.setBounds(20, 130, 266, 25);
		add(bookBorrowerTF);
		bookBorrowerTF.setText(null);

		bookBorrowedDate = new Label("Enter Date");
		bookBorrowedDate.setBounds(20, 165, 80, 25);
		add(bookBorrowedDate);

		bookBorrowedDateFormat = new Label("(ex. Dec 14, 2003)");
		bookBorrowedDateFormat.setBounds(105, 165, 160, 25);
		add(bookBorrowedDateFormat);

		bookBorrowedDateTF = new TextField();
		bookBorrowedDateTF.setBounds(20, 190, 266, 25);
		add(bookBorrowedDateTF);
		bookBorrowedDateTF.setText(null);
		bookBorrowedDateTF.addActionListener(this);


		listOfBooks = new java.awt.List();
		listOfBooks.setBounds(20, 225, 266, 150);
		add(listOfBooks);
		listOfBooks.addActionListener(this);


		releaseBookButton = new Button("Release");
		releaseBookButton.setBounds(20, 385, 80, 25);
		add(releaseBookButton);
		releaseBookButton.addActionListener(this);

		clearAllButton = new Button("Clear All");
		clearAllButton.setBounds(112, 385, 80, 25);
		add(clearAllButton);
		clearAllButton.addActionListener(this);

		exitMenuButton = new Button("Exit");
		exitMenuButton.setBounds(206, 385, 80, 25);
		add(exitMenuButton);

		bookStatusTF = new TextField();
		bookStatusTF.setBounds(20, 415, 266, 25);
		bookStatusTF.setEditable(false);
		add(bookStatusTF);

	}






	public void actionPerformed(ActionEvent evt){

		if(evt.getSource() == clearAllButton){
			bookTitleTF.setText(null);
			bookStatusTF.setText(null);
			bookBorrowedDateTF.setText(null);
			bookBorrowerTF.setText(null);
		}

		if(evt.getSource() == listOfBooks){
			bookTitleTF.setText(listOfBooks.getItem(listOfBooks.getSelectedIndex()));
			checkBook(bookTitleTF.getText());
			bookBorrowedDateTF.setText(null);
			bookBorrowerTF.setText(null);
		}

		if(evt.getSource() == releaseBookButton){

			if(checkAllFields()){
				bookStatusTF.setText("You must fill in all entries!");

			}

			else if(correctDateFormat() == false){
				bookStatusTF.setText("Invalid date format!");
			}

			else if(checkAllFields() == false && correctDateFormat() == true ) {
				FormatDate();
				getBook(bookTitleTF.getText());

			}


		}
	}



	private void FormatDate(){
		Date correctDateFormat = new Date(bookBorrowedDateTF.getText());
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
		dateBorrowed = df.format(correctDateFormat);
	}



	private boolean correctDateFormat(){
		boolean correctdate = false;
		String inputDate = bookBorrowedDateTF.getText();
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);

		try{
			Date d = df.parse(inputDate);
			correctdate = true;
		}

		catch( ParseException exc){
			correctdate = false;
		}

		catch( Exception e ){
			 correctdate = false;
		}

		return correctdate;

	}



	private boolean checkAllFields(){
		boolean thereIsBlank = false;
		String Blank = "";
		String BorrowerField = bookBorrowerTF.getText();
		String DateField = bookBorrowedDateTF.getText();
		String TitleField = bookTitleTF.getText();


		if((BorrowerField.equals(Blank)) || (DateField.equals(Blank)) || (TitleField.equals(Blank))){
			thereIsBlank = true;
		}

		return thereIsBlank;
	}



	private int getEmptyLoc(){
		int loc=0;
		for(int x=0;x<bookShelf.length;x++){
			if(bookShelf[x]==null){

			   loc = x;
			   break;
			}
	     }

			return loc;


	}


	public void updateBookList(){
		int x = getEmptyLoc();
		for(int y = 0; y < x; y++){
			listOfBooks.addItem(bookShelf[y].title);
		}

	}

	private boolean bookIn(int bookLoc){
		return bookShelf[bookLoc].libraryCard.in;
	}


	private int getBookLoc(String titleBook){
		int index = 0;
		for(int x = 0; x < bookShelf.length; x++){
			if(bookShelf[x].title.equals(titleBook)){
				index = x;
				break;
			}

		}

		return index;

	}

	private boolean bookFound(String titleBook){
		int t = 0;
		boolean found = false;
		while(bookShelf[t] != null){
			int i = bookShelf[t].title.compareTo(titleBook);
			if(i == 0){
				found = true;
				break;
			}
		 t++;
    	 }

	 return found;

     }

     private void checkBook(String titleBook){
		 if(bookFound(titleBook)){
			 if(bookIn(getBookLoc(titleBook))){

				 bookStatusTF.setText("The book is available");
				 bookBorrowedDateTF.setEnabled(true);
				 releaseBookButton.setEnabled(true);
				 bookBorrowerTF.setEnabled(true);

			 }

			 else if(bookIn(getBookLoc(titleBook)) == false){
				 bookStatusTF.setText(titleBook + " is already borrowed!");
			 }

		 }

		 else if(bookFound(titleBook) == false){
			 bookStatusTF.setText("Sorry! We do not have that book");

		 }

	 }




	private void getBook(String titleBook){
		 if(bookFound(titleBook)){
			 if(bookIn(getBookLoc(titleBook))){
				  markBorrowed(getBookLoc(titleBook));
				  bookTitleTF.setText(null);
				  bookBorrowerTF.setText(null);
				  bookBorrowedDateTF.setText(null);
				  bookStatusTF.setText(titleBook + " has been borrowed!");
			 }

			 else if(bookIn(getBookLoc(titleBook)) == false){
				 bookStatusTF.setText(titleBook + " is already out!");
				 bookTitleTF.setText(null);
				 bookStatusTF.setText(null);
				 bookBorrowedDateTF.setText(null);
				 bookBorrowerTF.setText(null);

			 }

		 }

		 else if(bookFound(titleBook) == false){
			 bookStatusTF.setText("Sorry! We do not have that book");
			 bookTitleTF.setText(null);
			 bookBorrowedDateTF.setText(null);
			 bookBorrowerTF.setText(null);
		 }

	 }

	 private void markBorrowed(int bookLoc){

		 bookShelf[bookLoc].libraryCard.entryPtr++;
		 bookShelf[bookLoc].libraryCard.in = false;
		 bookShelf[bookLoc].libraryCard.dateBorrowed = dateBorrowed;
		 bookShelf[bookLoc].libraryCard.borrower = bookBorrowerTF.getText();

	 }







}

