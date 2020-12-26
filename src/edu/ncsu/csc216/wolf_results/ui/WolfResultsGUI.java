package edu.ncsu.csc216.wolf_results.ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;

import edu.ncsu.csc216.wolf_results.manager.WolfResultsManager;
import edu.ncsu.csc216.wolf_results.model.io.WolfResultsReader;
import edu.ncsu.csc216.wolf_results.race_results.RaceList;

/**
 * Wolf Results GUI.
 * 
 * @author Bilal Mohamad
 * @author Marwah Mahate
 */
public class WolfResultsGUI extends JFrame implements ActionListener, Observer {

    /** ID number used for object serialization. */
    private static final long serialVersionUID = 1L;
    /** Title for top of GUI. */
    private static final String APP_TITLE = "Wolf Results";
    /** Text for the File Menu. */
    private static final String FILE_MENU_TITLE = "File";
    /** Text for the New Issue XML menu item. */
    private static final String NEW_FILE_TITLE = "New";
    /** Text for the Load Issue XML menu item. */
    private static final String LOAD_FILE_TITLE = "Load";
    /** Text for the Save menu item. */
    private static final String SAVE_FILE_TITLE = "Save";
    /** Text for the Quit menu item. */
    private static final String QUIT_TITLE = "Quit";
	/** Menu bar for the GUI that contains Menus. */
    private JMenuBar menuBar;
    /** Menu for the GUI. */
    private JMenu menu;
    /** Menu item for creating a new list of Races. */
    private JMenuItem itemNewFile;
    /** Menu item for loading a file. */
    private JMenuItem itemLoadFile;
    /** Menu item for saving the list to a file. */
    private JMenuItem itemSaveFile;
    /** Menu item for quitting the program. */
    private JMenuItem itemQuit;
    
    
    /** Creates the panels for the Race Panel*/
    private JPanel pnlRaces = new JPanel();
    /** Creates the panels for the Race Panel*/
    private JPanel pnlRacesButtons = new JPanel();
    /** Creates the panels for the Race Panel*/
    private JPanel pnlRacesBox = new JPanel();
    
    /** Creates the Race Details Panel */
    private JPanel pnlRaceDetails = new JPanel();
    /** Creates the Filter Race Results Panel */
    private JPanel pnlFilter = new JPanel();
    /** Creates the Results Panel */
    private JPanel pnlResults = new JPanel();
    /** Creates the Add Runner Panel */
    private JPanel pnlAddRunner = new JPanel();
    /** Creates the right side Panel */
    private JPanel pnlLeft = new JPanel();
    /** Creates the left side Panel */
    private JPanel pnlRight = new JPanel();
    
    
    /** Create the buttons for the Race Panel */
    private JButton addRaceButton = new JButton("Add Race");
    /** Create the buttons for the Race Panel */
    private JButton removeRaceButton = new JButton("Remove Race");
    /** Create the buttons for the Race Panel */
    private JButton editRaceButton = new JButton("Edit Race");
    /** Create the buttons for the Race Panel */
    private JButton unselectRaceButton = new JButton("Unselect Race");
    
    /** Creates the buttons for the Filter Panel */
    private JButton filterButton = new JButton("Filter");
    
    /** Creates the buttons for the Add Runner Panel */
    private JButton addRunnerButton = new JButton("Add");
    
    
//    private String[][] resultsInfo = new String[4][4];
    
    /** Creates an array containing the Results panel column headers */
    private String[] columnNames = {"Runner", "Age", "Time", "Pace"};
    /** The data for the JTable */
    private Object[][] data;
    
    /** The table containing the list of Races */
    private JTable racesTable = new JTable();
    /** The table containing the list of Results */
    private JTable resultsTable = new JTable();
//    private JTable resultsTable = new JTable(resultsInfo, COLUMN_NAMES);
    
    /** The list of races */
	@SuppressWarnings("unused")
	private RaceList rl;
    
    /**
     * Constructs the GUI.
     */
    public WolfResultsGUI() {
        super();

        // Observe Manager
        WolfResultsManager.getInstance().addObserver(this);

        // Set up general GUI info
        setSize(1500, 500);
        setLocation(50, 50);
        setTitle(APP_TITLE);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setUpMenuBar();

        addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
                doExit();
            }

        });
        
        initializeGUI();

        // Set the GUI visible
        setVisible(true);
    }

    /**
     * Initializes GUI
     */
    private void initializeGUI() {
    	
    	setUpPanels();
    	disableButtons();
    	
    	Container c = getContentPane();
    	
    	JPanel panel = new JPanel();
    	
    	panel.setLayout(new GridLayout());
    	panel.add(pnlLeft);
    	panel.add(pnlRight);
    	
    	c.add(panel);
    }
    
    /**
     * Disables the buttons before the any file has been loaded in
     */
    private void disableButtons() {
    	
    	addRaceButton.setEnabled(false);
        addRunnerButton.setEnabled(false);
        editRaceButton.setEnabled(false);
        filterButton.setEnabled(false);
        pnlRacesButtons.setEnabled(false);
        removeRaceButton.setEnabled(false);
        unselectRaceButton.setEnabled(false);
    }
    
    
    /**
     * Enables the buttons after a file has been loaded in
     */
    private void enableButtons() {
    	
        addRaceButton.setEnabled(true);
        addRunnerButton.setEnabled(true);
        editRaceButton.setEnabled(true);
        filterButton.setEnabled(true);
        pnlRacesButtons.setEnabled(true);
        removeRaceButton.setEnabled(true);
        unselectRaceButton.setEnabled(true);
    	
    }
    
    
    /**
     * Sets the panel layout of the GUI
     */
    private void setUpPanels() {
    	
    	//Races Panel
    	TitledBorder tb1 = (TitledBorder) BorderFactory.createTitledBorder("Races");
    	pnlRaces.setBorder(tb1);
    	
    	pnlRacesBox.setLayout(new GridLayout(1, 1));
    	pnlRacesBox.add(racesTable);
    	
    	RacesTableModel raceModel = new RacesTableModel(data);
    	
    	racesTable.setModel(raceModel);
    	
//    	pnlRacesBox.add(racesTable.getTableHeader(), BorderLayout.NORTH);
    	pnlRacesBox.add(racesTable, BorderLayout.CENTER);
    	
    	pnlRacesButtons.setLayout(new GridLayout(4, 1));
    	pnlRacesButtons.add(this.addRaceButton);
    	pnlRacesButtons.add(this.removeRaceButton);
    	pnlRacesButtons.add(this.editRaceButton);
    	pnlRacesButtons.add(this.unselectRaceButton);
    	
    	pnlRaces.setLayout(new GridLayout(1, 2));
    	pnlRaces.add(pnlRacesBox);
    	pnlRaces.add(pnlRacesButtons);
    	
    	
    	
    	//Race Details Panel
    	TitledBorder tb2 = (TitledBorder) BorderFactory.createTitledBorder("Races Details");
    	pnlRaceDetails.setBorder(tb2);
    	pnlRaceDetails.setLayout(new GridLayout(4, 2));
    	
    	addAndAlign(pnlRaceDetails, new JLabel("Race Name"));
    	pnlRaceDetails.add(new JTextField());
    	
    	addAndAlign(pnlRaceDetails, new JLabel("Race Distance"));
    	pnlRaceDetails.add(new JTextField());
    	
    	addAndAlign(pnlRaceDetails, new JLabel("Race Date"));
    	pnlRaceDetails.add(new JTextField());
    	
    	addAndAlign(pnlRaceDetails, new JLabel("Race Location"));
    	pnlRaceDetails.add(new JTextField());
    	

    	
    	//Filtered Race Results Panel
    	TitledBorder tb3 = (TitledBorder) BorderFactory.createTitledBorder("Filter Race Results");
    	pnlFilter.setBorder(tb3);
    	pnlFilter.setLayout(new GridLayout(5, 2));
    	
    	addAndAlign(pnlFilter, new JLabel("Age Min"));
    	pnlFilter.add(new JTextField());
    	
    	addAndAlign(pnlFilter, new JLabel("Age Max"));
    	pnlFilter.add(new JTextField());
    	
    	addAndAlign(pnlFilter, new JLabel("Pace Min"));
    	pnlFilter.add(new JTextField());
    	
    	addAndAlign(pnlFilter, new JLabel("Pace Max"));
    	pnlFilter.add(new JTextField());
    	
    	pnlFilter.add(this.filterButton);
    	
    	
    	
    	//All the left panels are added to the left side
    	pnlLeft.setLayout(new BoxLayout(pnlLeft, BoxLayout.PAGE_AXIS));
    	
    	pnlLeft.add(pnlRaces);
    	pnlLeft.add(pnlRaceDetails);
    	pnlLeft.add(pnlFilter);
    	
    	
    	
    	//Race Results Panel
    	TitledBorder tb4 = (TitledBorder) BorderFactory.createTitledBorder("Race Results");
    	
    	pnlResults.setBorder(tb4);
    	pnlResults.setLayout(new BorderLayout());
    	
    	//Prevents the cells in the table from being editable
    	ResultsTableModel tableModel = new ResultsTableModel(data, columnNames);
    	
		resultsTable.setModel(tableModel);
    	
    	pnlResults.add(resultsTable.getTableHeader(), BorderLayout.NORTH);
    	
//    	JScrollPane scrollResults = new JScrollPane(resultsTable);
    	
    	pnlResults.add(resultsTable, BorderLayout.CENTER);
    	
    	
    	
    	//Add Runner Panel
    	TitledBorder tb5 = (TitledBorder) BorderFactory.createTitledBorder("Add Runner");
    	pnlAddRunner.setBorder(tb5);
    	pnlAddRunner.setLayout(new GridLayout(4, 2));
    	
    	addAndAlign(pnlAddRunner, new JLabel("Runner Name"));
    	pnlAddRunner.add(new JTextField());
    	
    	addAndAlign(pnlAddRunner, new JLabel("Runner Age"));
    	pnlAddRunner.add(new JTextField());
    	
    	addAndAlign(pnlAddRunner, new JLabel("Runner Time"));
    	pnlAddRunner.add(new JTextField());
    	
    	pnlAddRunner.add(this.addRunnerButton);
    	
    	
    	
    	//All the right panels are added to the right side
    	pnlRight.setLayout(new BoxLayout(pnlRight, BoxLayout.PAGE_AXIS));
    	
    	pnlRight.add(pnlResults);
    	pnlRight.add(pnlAddRunner);
    }	
    
    
    /**
     * Adds the panel with the specified name and aligns them to the left
     * 
     * @param panel	the panel changes are being made to
     * @param label	the name of the label for the panel
     */
    private void addAndAlign(JPanel panel, JLabel label) {
    	label.setHorizontalAlignment(JLabel.LEFT);
    	panel.add(label);
    }

    /**
     * Makes the GUI Menu bar that contains options for loading a file
     * containing issues or for quitting the application.
     */
    private void setUpMenuBar() {
        // Construct Menu items
        menuBar = new JMenuBar();
        menu = new JMenu(FILE_MENU_TITLE);
        itemNewFile = new JMenuItem(NEW_FILE_TITLE);
        itemLoadFile = new JMenuItem(LOAD_FILE_TITLE);
        itemSaveFile = new JMenuItem(SAVE_FILE_TITLE);
        itemQuit = new JMenuItem(QUIT_TITLE);
        itemNewFile.addActionListener(this);
        itemLoadFile.addActionListener(this);
        itemSaveFile.addActionListener(this);
        itemQuit.addActionListener(this);

        // Start with save button disabled
        itemSaveFile.setEnabled(false);

        // Build Menu and add to GUI
        menu.add(itemNewFile);
        menu.add(itemLoadFile);
        menu.add(itemSaveFile);
        menu.add(itemQuit);
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
    }

    /**
     * Exits the GUI
     */
    private void doExit() {
        if (WolfResultsManager.getInstance().isChanged()) {
            doSaveFile();
        }

        if (!WolfResultsManager.getInstance().isChanged()) {
            System.exit(NORMAL);
        } else { // Did NOT save when prompted to save
            JOptionPane.showMessageDialog(this,
                    "Race Results changes have not been saved. "
                            + "Your changes will not be saved.",
                    "Saving Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Saves GUI to file
     */
    private void doSaveFile() {
        try {
            WolfResultsManager instance = WolfResultsManager.getInstance();
            JFileChooser chooser = new JFileChooser("./");
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Race Results files (md)", "md");
            chooser.setFileFilter(filter);
            chooser.setMultiSelectionEnabled(false);
            if (instance.getFilename() != null) {
                chooser.setSelectedFile(new File(instance.getFilename()));
            }
            int returnVal = chooser.showSaveDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                String filename = chooser.getSelectedFile().getAbsolutePath();
                if (chooser.getSelectedFile().getName().trim().equals("")
                        || !chooser.getSelectedFile().getName()
                                .endsWith(".md")) {
                    throw new IllegalArgumentException();
                }
                instance.setFilename(filename);
                instance.saveFile(filename);
            }
            itemLoadFile.setEnabled(true);
            itemNewFile.setEnabled(true);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "File not saved.",
                    "Saving Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Loads race results from file
     */
    private void doLoadFile() {
        try {
            WolfResultsManager instance = WolfResultsManager.getInstance();
            JFileChooser chooser = new JFileChooser("./");
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Race Results files (md)", "md");
            chooser.setFileFilter(filter);
            chooser.setMultiSelectionEnabled(false);
            int returnVal = chooser.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                instance.loadFile(chooser.getSelectedFile().getAbsolutePath());
                rl = WolfResultsReader.readRaceListFile(chooser.getSelectedFile().getAbsolutePath());
            }
            itemLoadFile.setEnabled(false);
            itemNewFile.setEnabled(false);
            
            
            
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, "Error opening file.",
                    "Opening Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    /**
     * Updates the GUI
     * 
     * @param o		the current instance being observed
     * @param arg	the object that changed
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof WolfResultsManager) {
            itemSaveFile.setEnabled(true);
            // TODO Call methods to update the contents of the GUI
            repaint();
            validate();
        }
    }

    
    /**
     * Performs the action corresponding the action made such as clicking a button, opening a menu, etc.
     * 
     * @param e	the action made
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        WolfResultsManager instance = WolfResultsManager.getInstance();

        if (e.getSource() == itemNewFile) {
            doSaveFile();
            instance.newList();
        } else if (e.getSource() == itemLoadFile) {
            doLoadFile();
            enableButtons();
            
            //Needs to happen after a race is selected (everything below till else if)
            data = instance.getRaceList().getRace(0).getResults().getResultsAsArray();
            
            for (int row = 0; row < instance.getRaceList().getRace(0).getResults().size(); row++) {
            	for (int col = 0; col < 4; col++) {
            		System.out.println("DATA TESTING: " + instance.getRaceList().getRace(0).getResults().getResultsAsArray()[row][col]);
            	}
            }
            
            resultsTable = new JTable(data, columnNames);
            resultsTable.updateUI();
            pnlResults.updateUI();
            
            
            
        } else if (e.getSource() == itemSaveFile) {
            doSaveFile();
        } else if (e.getSource() == itemQuit) {
            doExit();
        }
    }
    
    
    /**
     * Custom table for the table found in the Results panel
     * 
     * @author Bilal Mohamad
     * @author Marwah Mahate
     */
    private class ResultsTableModel extends AbstractTableModel {
    	
    	/** ID number used for object serialization. */
    	private static final long serialVersionUID = 1L;
    	
    	/** Data stored in the table */
    	private Object[][] data;
    	/** Column names for the table */
		private String[] columnNames = {"Runner", "Age", "Time", "Pace"};
		
		
		/**
		 * Constructs the ResultsTableModel with column headings and the retreived data
		 * 
		 * @param data			the data for the table
		 * @param columnNames	the column heading names
		 */
    	public ResultsTableModel(Object[][] data, String[] columnNames) {
    		this.data = data;
    		this.columnNames = columnNames;
    		updateResults();
		}
    	
    	/**
		 * Returns the number of columns in the table.
		 * @return the number of columns in the table.
		 */
    	@Override
		public int getColumnCount() {
			return columnNames.length;
		}
		
    	/**
		 * Returns the number of rows in the table.
		 * @return the number of rows in the table.
		 */
		@Override
		public int getRowCount() {
			if (data == null) {
				return 0;
			}
			return data.length;
		}
		
		/**
		 * Returns the column name at the given index.
		 * @return the column name at the given column.
		 */
		@Override
		public String getColumnName(int col) {
			return columnNames[col];
		}
		
		
		/**
		 * Returns the data at the given {row, col} index.
		 * @return the data at the given location.
		 */
		@Override
		public Object getValueAt(int row, int col) {
			if (data == null) {
				return null;
			}
			
			return data[row][col];
		}
    	
		
		/**
		 * Sets the given value to the given {row, col} location.
		 * @param value Object to modify in the data.
		 * @param row location to modify the data.
		 * @param column location to modify the data.
		 */
		@Override
		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}
		
		
		/**
		 * Makes the cells of the table uneditable
		 * 
		 * @param row	the row of the 2D array
		 * @param col	the column of the 2D array
		 * 
		 * @return	false - indicating that the cell is not editable
		 */
		@Override
	    public boolean isCellEditable(int row, int col) {
	       return false;
	    }
		
		
		/**
		 * Updates the table
		 */
		private void updateResults() {
//			data = WolfResultsManager.getInstance().getRaceList().getRace(0).getResults().getResultsAsArray();
		}
    }
    
    
    
    /**
     * Custom table for the table found in the Races panel
     * 
     * @author Bilal Mohamad
     * @author Marwah Mahate
     */
    private class RacesTableModel extends AbstractTableModel {
    	
    	/** ID number used for object serialization. */
    	private static final long serialVersionUID = 1L;

    	/** Data stored in the table */
    	Object[][] data;
    	
    	
		/**
		 * Constructs the RacesTableModel with column headings and the retreived data
		 * 
		 * @param data			the data for the table
		 */
    	public RacesTableModel(Object[][] data) {
    		updateRaces();
    	}
    	
    	
    	/**
		 * Returns the number of columns in the table.
		 * @return the number of columns in the table.
		 */
		@Override
		public int getColumnCount() {
			return 1;
		}

		
		/**
		 * Returns the number of rows in the table.
		 * @return the number of rows in the table.
		 */
		@Override
		public int getRowCount() {
			if (data == null) {
				return 0;
			}
			return data.length;
		}

		
		/**
		 * Returns the data at the given {row, col} index.
		 * @return the data at the given location.
		 */
		@Override
		public Object getValueAt(int row, int col) {
			if (data == null) {
				return null;
			}
			return data[row][col];
		}
		
		
		/**
		 * Sets the given value to the given {row, col} location.
		 * @param value Object to modify in the data.
		 * @param row location to modify the data.
		 * @param column location to modify the data.
		 */
		public void setValueAt(Object value, int row, int col) {
//			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}
		
		
		/**
		 * Updates the table
		 */
		private void updateRaces() {
			
			for (int row = 0; row < WolfResultsManager.getInstance().getRaceList().size(); row++) {
//				data[row][0] = WolfResultsManager.getInstance().getRaceList().toString();
			}
		}
    }

    /**
     * Starts the application
     * 
     * @param args command line args
     */
    public static void main(String[] args) {
        new WolfResultsGUI();
    }

}