import java.awt.*;
import java.awt.event.*;

public class SortMenu extends Frame implements ActionListener{

	Book[] bookShelf;

	TextArea unsortedListTextArea, sortedByTitleTextArea, showPerPassTextArea;
	Label unsortedListLabel, sortedByTitleLabel, showPerPassLabel;
	Button sortGoButton, sortExitButton;

	String[] title;

	public SortMenu(Book[] bookArray){

		bookShelf = new Book[100];
		bookShelf = bookArray;

		title = new String[100];

		setLayout(null);
		setSize(772, 450);
		setResizable(false);
		setVisible(false);
		setTitle("Sort Book By Title");

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

		showPerPassLabel = new Label("List title per pass");
		showPerPassLabel.setBounds(213, 350, 100, 25);
		add(showPerPassLabel);

		sortedByTitleTextArea = new TextArea();
		sortedByTitleTextArea.setBounds(573, 40, 173, 300);
		add(sortedByTitleTextArea);
		sortedByTitleTextArea.setEditable(false);

		sortedByTitleLabel = new Label("Sorted List");
		sortedByTitleLabel.setBounds(579, 350, 100, 25);
		add(sortedByTitleLabel);

		sortGoButton = new Button("Sort List");
		sortGoButton.setBounds(20, 395, 100, 25);
		add(sortGoButton);
		sortGoButton.addActionListener(this);

		sortExitButton = new Button("Exit");
		sortExitButton.setBounds(140, 395, 100, 25);
		add(sortExitButton);

	}


	public void actionPerformed(ActionEvent evt){
		sortByTitle();


	}

	private void sortByTitle(){
		int emptyLoc = getEmptyLoc();
		String temp;

		for(int x = 0; x < emptyLoc; x++){
			title[x] = bookShelf[x].title;
		}


		for(int y = 1; y <= emptyLoc; y++){
			showPerPassTextArea.append("PASS " + y + "\n" + "SEQUENCE" + "\n");
			callPerPassDisplay();
			showPerPassTextArea.append("\n\n" );

			for (int z = 0; z < emptyLoc - y; z++){
				int i = title[z].compareTo(title[z+1]);

				if(i > 0){
					showPerPassTextArea.append("SWAPS" + "\n" + title[z] + "   <---->  " + title[z+1] + "\n");
					temp = title[z];
					title[z] = title[z+1];
					title[z+1] = temp;
				}


			}

			showPerPassTextArea.append("\n\n" );
		}

		for(int y = 0; y < emptyLoc; y++){
			sortedByTitleTextArea.append(title[y] + "\n");
		}

	}


	private void callPerPassDisplay(){

		int lastIndex = getEmptyLoc();
		for(int x = 0; x < lastIndex; x++){
			showPerPassTextArea.append(title[x] + "\n");
		}

	}


	public void populateUnsortedList(){
		int emptyLoc = getEmptyLoc();
		for(int x = 0; x < emptyLoc; x++){
			title[x] = bookShelf[x].title;
		}

		for(int y = 0; y < emptyLoc; y++){
			unsortedListTextArea.append(title[y] + "\n");
		}

	}

	public int getEmptyLoc(){
		int loc=0;
		for(int x=0;x<bookShelf.length;x++){
			if(bookShelf[x]==null){

			   loc = x;
			   break;
			}
	     }

			return loc;


		}






}

















