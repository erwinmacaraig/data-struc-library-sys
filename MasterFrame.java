import java.awt.event.*;
import java.awt.*;

public class MasterFrame extends Frame{

	MenuBar mBar;
	Menu libraryFunctions;
	MenuItem addBooks, borrowBooks, returnBooks;
	Menu listBooks;
	MenuItem sortTitle, sortNumTimesBorrowed;

	public MasterFrame(){

		setLayout(null);
		setSize(650,450);
		setTitle("Libbrary System");
		setResizable(false);
		setVisible(true);

		mBar = new MenuBar();
		setMenuBar(mBar);

		libraryFunctions = new Menu("Library Functions");
		mBar.add(libraryFunctions);

		addBooks = new MenuItem("Add Books");
		libraryFunctions.add(addBooks);

		borrowBooks = new MenuItem("Borrow Books");
		libraryFunctions.add(borrowBooks);

		returnBooks = new MenuItem("Return Books");
		libraryFunctions.add(returnBooks);

		listBooks = new Menu("List Books by...");
		mBar.add(listBooks);

		sortTitle = new MenuItem("Title");
		listBooks.add(sortTitle);

		sortNumTimesBorrowed = new MenuItem("Number of Times Borrowed");
		listBooks.add(sortNumTimesBorrowed);
	}


}
