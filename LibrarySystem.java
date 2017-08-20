import java.awt.event.*;
import java.text.*;
import java.awt.*;

public class LibrarySystem{

	static Book[] bookShelf;
	static addBookMenu AddBook;
	static returnBookMenu ReturnBook;
	static bookBorrowMenu BorrowBook;
	static SortMenu sortMenu;
	static sortByBorrowed SortByBorrowed;




	public LibrarySystem(){
		bookShelf = new Book[100];
		AddBook = new addBookMenu(bookShelf);
		ReturnBook = new returnBookMenu(bookShelf);
		BorrowBook = new bookBorrowMenu(bookShelf);
		sortMenu = new SortMenu(bookShelf);
		SortByBorrowed = new sortByBorrowed(bookShelf);



	}


	public static void main(String[] arg){
		new LibrarySystem();

		final MasterFrame frame = new MasterFrame();

		frame.addBooks.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				AddBook.setVisible(true);
				ReturnBook.setVisible(false);
				BorrowBook.setVisible(false);
				sortMenu.setVisible(false);
				frame.setVisible(false);
			}

		}

		);

		frame.borrowBooks.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				AddBook.setVisible(false);
				ReturnBook.setVisible(false);
				BorrowBook.setVisible(true);
				sortMenu.setVisible(false);
				frame.setVisible(false);

				BorrowBook.listOfBooks.clear();
			    BorrowBook.updateBookList();
			}

		}

		);

		frame.returnBooks.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				AddBook.setVisible(false);
				ReturnBook.setVisible(true);
				BorrowBook.setVisible(false);
				sortMenu.setVisible(false);
				frame.setVisible(false);

				ReturnBook.returnListBox.clear();
				ReturnBook.updateBookOutList();
			}
		}

		);


		frame.sortTitle.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				AddBook.setVisible(false);
				ReturnBook.setVisible(false);
				BorrowBook.setVisible(false);
				sortMenu.setVisible(true);
				frame.setVisible(false);

				sortMenu.sortedByTitleTextArea.setText("");
				sortMenu.unsortedListTextArea.setText("");
				sortMenu.populateUnsortedList();
			}

		}

		);

		frame.sortNumTimesBorrowed.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				AddBook.setVisible(false);
				ReturnBook.setVisible(false);
				BorrowBook.setVisible(false);
				sortMenu.setVisible(false);
				SortByBorrowed.setVisible(true);
				frame.setVisible(false);

				SortByBorrowed.populateUnsortedByBorrowedList();
			}

		}

		);




		AddBook.addExitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				AddBook.setVisible(false);
				ReturnBook.setVisible(false);
				BorrowBook.setVisible(false);
				sortMenu.setVisible(false);
				frame.setVisible(true);
				AddBook.addTitleTF.setText(null);
				AddBook.addStatusTF.setText(null);
			}

		}

		);

		BorrowBook.exitMenuButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				AddBook.setVisible(false);
				ReturnBook.setVisible(false);
				BorrowBook.setVisible(false);
				sortMenu.setVisible(false);
				frame.setVisible(true);

				BorrowBook.bookTitleTF.setText(null);
				BorrowBook.bookBorrowerTF.setText(null);
				BorrowBook.bookBorrowedDateTF.setText(null);
				BorrowBook.bookStatusTF.setText(null);

			}

		}

		);

		ReturnBook.returnExitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				AddBook.setVisible(false);
				ReturnBook.setVisible(false);
				BorrowBook.setVisible(false);
				sortMenu.setVisible(false);
				frame.setVisible(true);

				ReturnBook.returnBookTitleTF.setText(null);
				ReturnBook.returnBookBorrowerTF.setText(null);
				ReturnBook.returnDateBookBorrowedTF.setText(null);
				ReturnBook.returnDateTF.setText(null);
				ReturnBook.returnDateTF.setEnabled(false);
				ReturnBook.penaltyPerWeek.setText("0.00");
				ReturnBook.penaltyPerWeek.setEnabled(false);
				ReturnBook.numberOfWeeksTF.setEditable(false);
				ReturnBook.numberOfWeeksTF.setText(null);
				ReturnBook.totalPenalatyTF.setText(null);
				ReturnBook.returnCalculateButton.setEnabled(false);
				ReturnBook.returnAcceptBookButton.setEnabled(false);
				ReturnBook.returnMenuStatusTF.setText(null);


			}

		}

		);


		sortMenu.sortExitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				AddBook.setVisible(false);
				ReturnBook.setVisible(false);
				BorrowBook.setVisible(false);
				sortMenu.setVisible(false);
				frame.setVisible(true);

				sortMenu.showPerPassTextArea.setText(null);

			}

		}

		);

		SortByBorrowed.sortExitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent evt){
				SortByBorrowed.unsortedListTextArea.setText(null);
				SortByBorrowed.sortedByBorrowedTextArea.setText(null);
				SortByBorrowed.showPerPassTextArea.setText(null);

				AddBook.setVisible(false);
				ReturnBook.setVisible(false);
				BorrowBook.setVisible(false);
				sortMenu.setVisible(false);
				SortByBorrowed.setVisible(false);
				frame.setVisible(true);

			}

		}

		);




		frame.addWindowListener(new WindowAdapter(){
				public void windowClosing(WindowEvent e){
					System.exit(0);
				}

			}

			);


	}


}


