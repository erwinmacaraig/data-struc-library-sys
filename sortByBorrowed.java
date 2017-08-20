import java.awt.*;
import java.awt.event.*;

public class sortByBorrowed extends Frame implements ActionListener{

	Book[] bookShelf;

	TextArea unsortedListTextArea, sortedByBorrowedTextArea, showPerPassTextArea;
	Label unsortedListLabel, sortedByBorrowedLabel, showPerPassLabel;
	Button sortGoButton, sortExitButton;

	String[] titleOfBook;
	int[] timesBorrowed;


	public sortByBorrowed(Book[] bookArray){

		bookShelf = new Book[100];
		bookShelf = bookArray;

		titleOfBook = new String[100];
		timesBorrowed = new int[100];


		setLayout(null);
		setSize(772, 450);
		setResizable(false);
		setVisible(false);
		setTitle("Sort Book By Number of Times Borrowed");

		unsortedListTextArea = new TextArea();
		unsortedListTextArea.setBounds(20,40,173,300);
		add(unsortedListTextArea);
		unsortedListTextArea.setEditable(false);

		unsortedListLabel = new Label("Unsorted List");
		unsortedListLabel.setBounds(20, 350, 100, 25);
		add(unsortedListLabel);

		showPerPassTextArea = new TextArea();
		showPerPassTextArea.setBounds(213, 40, 346, 300);
		add(showPerPassTextArea);
		showPerPassTextArea.setEditable(false);

		showPerPassLabel = new Label("List per pass");
		showPerPassLabel.setBounds(213, 350, 100, 25);
		add(showPerPassLabel);

		sortedByBorrowedTextArea = new TextArea();
		sortedByBorrowedTextArea.setBounds(579, 40, 173, 300);
		add(sortedByBorrowedTextArea);
		sortedByBorrowedTextArea.setEditable(false);

		sortedByBorrowedLabel = new Label("Sorted List");
		sortedByBorrowedLabel.setBounds(579, 350, 100, 25);
		add(sortedByBorrowedLabel);

		sortGoButton = new Button("Sort List");
		sortGoButton.setBounds(20, 395, 100, 25);
		add(sortGoButton);
		sortGoButton.addActionListener(this);

		sortExitButton = new Button("Exit");
		sortExitButton.setBounds(140, 395, 100, 25);
		add(sortExitButton);

	}

	public void actionPerformed(ActionEvent evt){
		sortNumberOfTimesBorrowed();


	}

	public void populateUnsortedByBorrowedList(){
		int emptyLoc = getEmptyLoc();

		for(int x = 0; x < emptyLoc; x++){
			titleOfBook[x] = bookShelf[x].title;
			timesBorrowed[x] = bookShelf[x].libraryCard.entryPtr;




		}

		for(int y = 0; y < emptyLoc; y++){
			unsortedListTextArea.append(titleOfBook[y] + "\t" + " --> " + timesBorrowed[y] + "\n");

		}

	}



	private int getEmptyLoc(){
		int loc = 0;
		for(int x = 0; x < bookShelf.length; x++){
			if(bookShelf[x] == null){
				loc = x;
				break;
			}

		}
		return loc;
	}


	private void sortNumberOfTimesBorrowed(){
		String temp; //for title
		int buffer; //for entryPtr

		int index = getEmptyLoc();



		for(int y = 1; y <= index; y++){
			showPerPassTextArea.append("PASS " + y + "\n" + "SEQUENCE" + "\n");
			callPerPassDisplay();
			showPerPassTextArea.append("\n\n" );

			for(int z = 0; z < index - y; z++){
				if(timesBorrowed[z] < timesBorrowed[z+1]){
					showPerPassTextArea.append("SWAPS" + "\n" + titleOfBook[z] + " <----> " + titleOfBook[z+1] + "\n");
					temp = titleOfBook[z];
					buffer = timesBorrowed[z];

					titleOfBook[z] = titleOfBook[z+1];
					timesBorrowed[z] = timesBorrowed[z+1];

					titleOfBook[z+1] = temp;
					timesBorrowed[z+1] = buffer;
				}

			}
			showPerPassTextArea.append("\n\n" );

		}

		for(int y = 0; y < index; y++){
			sortedByBorrowedTextArea.append(titleOfBook[y] + "\t" + " --> " + timesBorrowed[y] + "\n");
	    }




	}

	private void callPerPassDisplay(){
		int lastElement = getEmptyLoc();
	    for(int x = 0; x < lastElement; x++){
			showPerPassTextArea.append(titleOfBook[x] + "\n" );
		}

	}








}
