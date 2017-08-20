import java.awt.*;
import java.awt.event.*;

public class addBookMenu extends Frame implements ActionListener{
	Book[] bookShelf;


    Label addBooksTitleLabel;
	Button addBooksButton, addCancelButton, addExitButton;
	TextField addStatusTF, addTitleTF;
	TextArea addBooksListBox;


	public addBookMenu(Book[] bookArray){

		bookShelf = new Book[100];
		bookShelf = bookArray;

        setLayout(null);
		setSize(306,450);
		setVisible(false);
		setResizable(false);
		setTitle("Add Books Menu");


		addBooksTitleLabel = new Label("TITLE:");
		addBooksTitleLabel.setBounds(20, 45, 60, 25);
		add(addBooksTitleLabel);

		addTitleTF = new TextField();
		addTitleTF.setBounds(20, 75, 266, 25);
		add(addTitleTF);
		addTitleTF.addActionListener(this);
		addTitleTF.setText(null);

		addBooksButton = new Button("Add Books");
		addBooksButton.setBounds(20, 365, 80, 25);
		addBooksButton.addActionListener(this);
		add(addBooksButton);

		addCancelButton = new Button("Cancel");
		addCancelButton.setBounds(112, 365, 80, 25);
		addCancelButton.addActionListener(this);
		add(addCancelButton);

		addExitButton = new Button("Exit");
		addExitButton.setBounds(206, 365, 80,25);
		add(addExitButton);

		addBooksListBox = new TextArea();
		addBooksListBox.setBounds(20,110,266,250);
		addBooksListBox.setEditable(false);
		add(addBooksListBox);

		addStatusTF = new TextField();
		addStatusTF.setBounds(20, 395, 266, 25);
		addStatusTF.setEditable(false);
		add(addStatusTF);



	}

	public void actionPerformed(ActionEvent evt){

		if(evt.getSource() == addTitleTF){
			String blank = "";
			String TitleField = addTitleTF.getText();

			if((checkEntry(addTitleTF.getText())) || (TitleField.equals(blank))){
				addStatusTF.setText("The book already has an entry or invalid entry");
			}

			else{
				addToShelf(new Book(addTitleTF.getText()));
				addBooksListBox.append(addTitleTF.getText()+ "\n");
				addStatusTF.setText(addTitleTF.getText() + " has been added");
				addTitleTF.setText("");
			}

		}




		if(evt.getSource() == addBooksButton){
			String blank = "";
			String TitleField = addTitleTF.getText();

			if((checkEntry(addTitleTF.getText())) || (TitleField.equals(blank))){
				addStatusTF.setText("The book already has an entry or invalid entry");
			}

			else{
				addToShelf(new Book(addTitleTF.getText()));
				addBooksListBox.append(addTitleTF.getText() + "\n");
				addStatusTF.setText(addTitleTF.getText() + " has been added");
				addTitleTF.setText("");
			}
		}

		if(evt.getSource() == addCancelButton){
			addTitleTF.setText(null);
			addStatusTF.setText(null);
		}



	}



	private void addToShelf(Book b){

			 bookShelf[getEmptyLoc()]=b;

	}


	 /*Return the int location of an empty bookshelf */
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

		private boolean checkEntry(String s){
			boolean entryDuplicated = false;
			int limit = 0;
			while(bookShelf[limit] != null){
				int e = bookShelf[limit].title.compareTo(s);
				if(e == 0){
					entryDuplicated = true;
					break;
				}

				limit++;
			}

			return entryDuplicated;

		}





}