import java.awt.*;
import java.awt.event.*;
import java.text.*;
import java.util.*;

public class returnBookMenu extends Frame implements ActionListener{

	Book[] bookShelf;

	Label returnBookTitle, returnBookBorrower, returnDateLabel;
	Label returnPenaltyLabel, penaltyLabel, numOfWeeksLabel;
	Label equalsLabel, totalPenaltyLabel;
	Label returnDateBookBorrowed, returnBookDateFormat;
	Label penaltyPerWeekLabel;
	TextField returnBookTitleTF, returnBookBorrowerTF, returnDateBookBorrowedTF;
	TextField returnDateTF, penaltyPerWeek, returnMenuStatusTF;
	TextField numberOfWeeksTF, totalPenalatyTF;

	java.awt.List returnListBox;

	Label returnListBoxLabel;

	Button returnCalculateButton, returnClearAllButton, returnExitButton, returnAcceptBookButton;




	public returnBookMenu(Book[] bookArray){

		bookShelf = new Book[100];
		bookShelf = bookArray;

        setLayout(null);
		setSize(572,470);
		setVisible(false);
		setResizable(false);
		setTitle("Return Book Menu");


		returnBookTitle = new Label("TITLE:");
		returnBookTitle.setBounds(286, 40, 80, 25);
		add(returnBookTitle);

		returnBookTitleTF = new TextField();
		returnBookTitleTF.setBounds(286, 70, 266, 25);
		add(returnBookTitleTF);
		returnBookTitleTF.setText(null);
		returnBookTitleTF.setEditable(false);

		returnListBoxLabel = new Label("List Of Books That Are Out");
		returnListBoxLabel.setBounds(20, 40, 270, 25);
		add(returnListBoxLabel);

		returnListBox = new java.awt.List();
		returnListBox.setBounds(20, 70, 235, 385);
		add(returnListBox);
		returnListBox.addActionListener(this);

		returnBookBorrower = new Label("Borrower:");
		returnBookBorrower.setBounds(286, 100, 80, 25);
		add(returnBookBorrower);

		returnBookBorrowerTF = new TextField();
		returnBookBorrowerTF.setBounds(286, 130, 266, 25);
		add(returnBookBorrowerTF);
		returnBookBorrowerTF.setEditable(false);

		returnDateBookBorrowed = new Label("Date Borrowed:");
		returnDateBookBorrowed.setBounds(286, 160, 200, 25);
		add(returnDateBookBorrowed);

		returnDateBookBorrowedTF = new TextField();
		returnDateBookBorrowedTF.setBounds(286, 190, 266, 25);
		add(returnDateBookBorrowedTF);
		returnDateBookBorrowedTF.setEditable(false);

		returnDateLabel = new Label("Date Returned:");
		returnDateLabel.setBounds(286, 220, 200, 25);
		add(returnDateLabel);

		returnDateTF = new TextField();
		returnDateTF.setBounds(286, 250, 266, 25);
		returnDateTF.setEnabled(false);
		returnDateTF.addActionListener(this);
		add(returnDateTF);


		penaltyPerWeekLabel = new Label("Fine/Week");
		penaltyPerWeekLabel.setBounds(286, 280, 100, 25);
		add(penaltyPerWeekLabel);

		penaltyPerWeek = new TextField();
		penaltyPerWeek.setBounds(392, 280, 160, 25);
		add(penaltyPerWeek);
		penaltyPerWeek.setText("0.00");

		numOfWeeksLabel = new Label("Weeks Late");
		numOfWeeksLabel.setBounds(286, 310, 100, 25);
		add(numOfWeeksLabel);

		numberOfWeeksTF = new TextField();
		numberOfWeeksTF.setBounds(392, 310, 160, 25);
		add(numberOfWeeksTF);
		numberOfWeeksTF.setEditable(false);


		penaltyLabel = new Label("Penalty");
		penaltyLabel.setBounds(286, 340, 100, 25);
		add(penaltyLabel);

		totalPenalatyTF = new TextField();
		totalPenalatyTF.setBounds(392, 340, 160, 25);
		add(totalPenalatyTF);
		totalPenalatyTF.setEditable(false);


		returnCalculateButton  = new Button("Calculate");
		returnCalculateButton.setBounds(286,370,80,25);
		add(returnCalculateButton);
		returnCalculateButton.setEnabled(false);
		returnCalculateButton.addActionListener(this);

		returnClearAllButton = new Button("Clear All");
		returnClearAllButton.setBounds(378, 370, 80, 25);
		add(returnClearAllButton);
		returnClearAllButton.addActionListener(this);

		returnExitButton = new Button("Exit");
		returnExitButton.setBounds(472, 370, 80,25);
		add(returnExitButton);
		returnClearAllButton.addActionListener(this);


		returnAcceptBookButton = new Button("Accept Book");
		returnAcceptBookButton.setBounds(286, 400, 266, 25);
		add(returnAcceptBookButton);
		returnAcceptBookButton.setEnabled(false);
		returnAcceptBookButton.addActionListener(this);

		returnMenuStatusTF = new TextField();
	 	returnMenuStatusTF.setBounds(286, 435, 266, 25);
		returnMenuStatusTF.setEditable(false);
		add(returnMenuStatusTF);


	}

	public void actionPerformed(ActionEvent evt){


		if(evt.getSource() == returnClearAllButton){
			returnBookTitleTF.setText(null);
			returnBookBorrowerTF.setText(null);
			returnDateTF.setText(null);
			penaltyPerWeek.setText("0.00");
			numberOfWeeksTF.setText(null);
			totalPenalatyTF.setText(null);
			returnMenuStatusTF.setText(null);
			returnDateBookBorrowedTF.setText(null);

		}


		if(evt.getSource() == returnListBox){

			returnBookTitleTF.setText(returnListBox.getItem(returnListBox.getSelectedIndex()));
			int location = getBookLoc(returnBookTitleTF.getText());

			returnDateBookBorrowedTF.setText(bookShelf[location].libraryCard.dateBorrowed);
			returnBookBorrowerTF.setText(bookShelf[location].libraryCard.borrower);
			returnMenuStatusTF.setText(null);
			returnDateTF.setEnabled(true);
			returnDateTF.setEditable(true);
			returnAcceptBookButton.setEnabled(true);
			penaltyPerWeek.setEnabled(true);
		}


		if(evt.getSource() == returnDateTF){

			if(correctDateFormat()){
				String titleBook = returnBookTitleTF.getText();
				int locatedAt = getBookLoc(titleBook);
				FormatDate();
				numberOfWeeksLate(locatedAt);
				returnCalculateButton.setEnabled(true);
				returnDateTF.setEnabled(false);
				returnDateTF.setEditable(false);
				returnMenuStatusTF.setText(null);
			}

			else{
				returnDateTF.setText(null);
				returnMenuStatusTF.setText("Invalid date entry");
			}

		}


		if(evt.getSource() == returnCalculateButton){
			showPenalty();
		}

		if(evt.getSource() == returnAcceptBookButton){
			if(checkAllFields() == false ){
				int location = getBookLoc(returnBookTitleTF.getText());
				ClearBook(location);
				returnBookTitleTF.setText(null);
				returnBookBorrowerTF.setText(null);
				returnDateBookBorrowedTF.setText(null);
				returnDateTF.setText(null);
				returnDateTF.setEnabled(false);
				penaltyPerWeek.setText("0.00");
				numberOfWeeksTF.setText(null);
				totalPenalatyTF.setText(null);
				returnCalculateButton.setEnabled(false);
				returnAcceptBookButton.setEnabled(false);
				penaltyPerWeek.setEnabled(false);
				returnMenuStatusTF.setText(null);
				updateBookOutList();
				returnDateTF.setEnabled(true);

			}

			else if (checkAllFields()){
				returnMenuStatusTF.setText("Please fill in all entries!");
			}



		}


	}

	private void FormatDate(){
		Date correctDateFormat = new Date(returnDateTF.getText());
		DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.US);
		String dateReturned = df.format(correctDateFormat);
		returnDateTF.setText(dateReturned);
	}



	private boolean correctDateFormat(){
		boolean correctdate = false;
		String inputDate = returnDateTF.getText();
		DateFormat df = DateFormat.getDateInstance();
		try{
			Date d = df.parse(inputDate);
			correctdate = true;
		}

		catch( ParseException exc){
			correctdate = false;
		}

		catch( IllegalArgumentException iae){
			 correctdate = false;
		}

		return correctdate;

	}

	private boolean checkAllFields(){
		boolean thereIsBlank = false;
		String blank = "";
		String BookTitle = returnBookTitleTF.getText();
		String returnDate = returnDateTF.getText();

		if((BookTitle.equals(blank)) || (returnDate.equals(blank))){
			thereIsBlank = true;
		}

		return thereIsBlank;

	}


	private void ClearBook(int bookLoc){
		bookShelf[bookLoc].libraryCard.in = true;
		bookShelf[bookLoc].libraryCard.dateBorrowed = " ";
		bookShelf[bookLoc].libraryCard.borrower = " ";
	}


	private void showPenalty(){


		double weeks;
		double fine;
		double total;

		String NumberOfWeeks = numberOfWeeksTF.getText();
		String FinePerWeek = penaltyPerWeek.getText();

		try{
			fine = Double.parseDouble(FinePerWeek);
			weeks =Double.parseDouble(NumberOfWeeks);
			total = fine * weeks;
			totalPenalatyTF.setText(" " + total);
			returnMenuStatusTF.setText(" ");
		}

		catch( NumberFormatException nfe){
			returnMenuStatusTF.setText("Invalid Number Format");
		}


	}

	private boolean BookIn(int bookLocation){
		return bookShelf[bookLocation].libraryCard.in;
	}


	private void numberOfWeeksLate(int index){
		final long msPerDay = 86400000;
		Date dateBorrowed = new Date(bookShelf[index].libraryCard.dateBorrowed);
		Date dateReturned = new Date(returnDateTF.getText());
		long returnLong = dateReturned.getTime();
		long borrowLong = dateBorrowed.getTime();
		long d = (returnLong - borrowLong) / msPerDay;
		long numOfWeeks = d / 7;

		numberOfWeeksTF.setText(" " + numOfWeeks);


		if(numOfWeeks < 1){
			numberOfWeeksTF.setText("00");
		}

		else{

			if((d % 7) == 0){
				numberOfWeeksTF.setText(" " + numOfWeeks);
			}

			else{
				numOfWeeks = numOfWeeks + 1;
				numberOfWeeksTF.setText( " " + numOfWeeks);
			}

		}

	}


	public void updateBookOutList(){
		int ptrEmpty = getEmptyLoc();
		returnListBox.clear();
		for(int x = 0; x < ptrEmpty; x++){
			if(bookShelf[x].libraryCard.in == false){
				returnListBox.addItem(bookShelf[x].title);
			}

		}

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


	public void showBooks(){
		int x = getEmptyLoc();
		for(int y = 0; y < x; y++){
			returnListBox.addItem(bookShelf[y].title);
		}

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






}
