package view;

import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import model.Era;
import model.Festival;
import model.Historical;
import model.HistoricalEvent;
import model.HistoricalFigure;
import model.HistoricalSite;
import model.MainModel;

public class ViewController {
    @FXML
	  private HBox historicalFigureItem;

	  @FXML
	  private HBox eraItem;

	  @FXML
	  private HBox eventItem;

	  @FXML
	  private HBox historicSiteItem;

	  @FXML
	  private HBox festivalItem;
	  
	  @FXML
	  private TableView<?> tableData;

	  private HBox[] items;
	  private HBox selectedItem;
	  private ObservableList<HistoricalFigure> historicalFigureList;
	  private ObservableList<HistoricalSite> historicSiteList;
	  private ObservableList<HistoricalEvent> eventList;
	  private ObservableList<Festival> festivalList;
	  private ObservableList<Era> eraList;
	  
	  private TableView<HistoricalFigure> historicalFigureTable = new TableView<>();
	  private TableView<HistoricalSite> historicSiteTable = new TableView<>();
	  private TableView<HistoricalEvent> eventTable = new TableView<>();
	  private TableView<Festival> festivalTable = new TableView<>();
	  private TableView<Era> eraTable = new TableView<>();

	  @SuppressWarnings("unchecked")
	@FXML
	  private void initialize() {
		// Data
//		MainModel model = new MainModel();
//		historicalFigureList = FXCollections.observableArrayList(model.getHistoricalFigures());
//		eventList = FXCollections.observableArrayList(model.getEvents());
//		festivalList = FXCollections.observableArrayList(model.getFestivals());
//		eraList = FXCollections.observableArrayList(model.getEras());
		  
//		historicalFigureList = FXCollections.observableArrayList(
//			new HistoricalFigure("Tran Hung Dao", Arrays.asList("Tran Quoc Tuan"), 900, 1000),
//			new HistoricalFigure("Tran Hung Dao", Arrays.asList("Tran Quoc Tuan"), 900, 1000)
//		);

		tableData.getColumns().clear();
		settingTable(historicalFigureTable, historicalFigureList, Arrays.asList("Tên", "Năm sinh", "Năm mất"), Arrays.asList("name", "born", "died"));
		copyTable(historicalFigureTable,(TableView<HistoricalFigure>) tableData);
		
		// Sidebar
	    items = new HBox[] {historicalFigureItem, eraItem, eventItem, historicSiteItem, festivalItem};
	    selectedItem = historicalFigureItem;
	    for (HBox item : items) {
	    	item.setStyle("-fx-cursor: hand");
	      }
	    selectedItem.setStyle("-fx-background-color: #ccc");
	  }

	  @FXML
	  private void handleItemClicked(MouseEvent event) {
	    HBox clickedItem = (HBox) event.getSource();
	    selectedItem.setStyle("-fx-cursor: hand");
	    selectedItem = clickedItem;
	    selectedItem.setStyle("-fx-background-color: #ccc");
	    
	    // change table
	    tableData.getColumns().clear();
	    String labelText = "";
	    for (Node node : selectedItem.getChildren()) {
	        if (node instanceof Label) {
	            Label label = (Label) node;
	            labelText = label.getText();
	        }
	    }
	    switch(labelText) {
	    	case "Nhân vật lịch sử":
	    		copyTable(historicalFigureTable,(TableView<HistoricalFigure>) tableData);
	    		break;
	    	case "Di tích lịch sử":
	    		copyTable(historicSiteTable,(TableView<HistoricalSite>) tableData);
	    		break;
	    	case "Sự kiện lịch sử":
	    		copyTable(eventTable,(TableView<HistoricalEvent>) tableData);
	    		break;
	    	case "Lễ hội văn hóa":
	    		copyTable(festivalTable,(TableView<Festival>) tableData);
	    		break;
	    	case "Triều đại lịch sử":
	    		copyTable(eraTable,(TableView<Era>) tableData);
	    		break;
	    	default:
                System.out.println("Loi");
	    }
	  }
	  
	  private <T> void settingTable(TableView<T> table, ObservableList<T> data, List<String> columnName, List<String> columnProperty) {
		  table.setItems(data);
		  for(int i = 0; i < columnName.size(); ++i) {
			  TableColumn<T, ?> column = new TableColumn<>(columnName.get(i));
			  column.setMinWidth(100);
			  column.setCellValueFactory(new PropertyValueFactory<>(columnProperty.get(i)));
			  table.getColumns().add(column);
		  }
	  }
	  
	  private <T> void copyTable(TableView<T> originalTable, TableView<T> newTable) {
		  newTable.setItems((ObservableList<T>) originalTable.getItems());
		  for (TableColumn<T, ?> column : originalTable.getColumns()) {
			    newTable.getColumns().add(column);
			}
	  }
}
