package view;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.Era;
import model.Festival;
import model.Historical;
import model.HistoricalEvent;
import model.HistoricalFigure;
import model.HistoricalSite;
import model.MainModel;

public class ViewController{

	public TextField searchInfo;

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
	
	private List<String> historicalFigureTableFieldName = Arrays.asList("STT", "Tên", "Năm sinh", "Năm mất", "Nơi sinh");
	private List<String> historicalFigureTableFieldProperty = Arrays.asList("id", "name", "born", "died", "bornIn");
	private List<String> historicalFigureLinkFieldName = Arrays.asList("Trieu dai", "Su kien lien quan");
	private List<String> historicalFigureLinkFieldProperty = Arrays.asList("relatedEraId", "relatedEventId");
	private List<String> historicSiteTableFieldName = Arrays.asList("STT", "Tên", "Địa điểm", "Đối tượng thờ", "Loại hình xếp hạng", "Loại xếp hạng");
	private List<String> historicSiteTableFieldProperty = Arrays.asList("id", "name", "location", "objectWorship", "loaiHinhXepHang", "loaiXepHang");
	private List<String> eventTableFieldName = Arrays.asList("STT", "Tên", "Bắt đầu", "Kết thúc");
	private List<String> eventTableFieldProperty = Arrays.asList("id", "name", "startDate", "endDate");
	private List<String> festivalTableFieldName = Arrays.asList("STT", "Tên", "Địa điểm", "Đối tượng suy tôn", "Thời gian");
	private List<String> festivalTableFieldProperty = Arrays.asList("id", "name", "location", "doiTuongSuyTon", "holdTime");
	private List<String> eraTableFieldName = Arrays.asList("STT", "Tên", "Từ năm", "Đến năm");
	private List<String> eraTableFieldProperty = Arrays.asList("id", "name", "fromYear", "toYear");
			
	@SuppressWarnings("unchecked")
	@FXML
	private void initialize() {
		// Data
		MainModel model = new MainModel();
		historicalFigureList = FXCollections.observableArrayList(model.getHistoricalFigures());
		historicSiteList = FXCollections.observableArrayList(model.getHistoricSites());
		eventList = FXCollections.observableArrayList(model.getEvents());
		festivalList = FXCollections.observableArrayList(model.getFestivals());
		eraList = FXCollections.observableArrayList(model.getEras());

		tableData.getColumns().clear();
		settingTable(historicalFigureTable, historicalFigureList, historicalFigureTableFieldName, historicalFigureTableFieldProperty);
		settingTable(historicSiteTable, historicSiteList, historicSiteTableFieldName, historicSiteTableFieldProperty);
		settingTable(eventTable, eventList, eventTableFieldName, eventTableFieldProperty);
		settingTable(festivalTable, festivalList, festivalTableFieldName, festivalTableFieldProperty);
		settingTable(eraTable, eraList, eraTableFieldName, eraTableFieldProperty);

		copyTable(historicalFigureTable, (TableView<HistoricalFigure>) tableData);

		// Sidebar
		items = new HBox[] { historicalFigureItem, eraItem, eventItem, historicSiteItem, festivalItem };
		selectedItem = historicalFigureItem;
		for (HBox item : items) {
			item.setStyle("-fx-cursor: hand");
		}
		selectedItem.setStyle("-fx-background-color: #ccc");

		// Pop up
		setEventClickOnRow((TableView<HistoricalFigure>) tableData);
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
		switch (labelText) {
		case "Nhân vật lịch sử":
			copyTable(historicalFigureTable, (TableView<HistoricalFigure>) tableData);
			break;
		case "Di tích lịch sử":
			copyTable(historicSiteTable, (TableView<HistoricalSite>) tableData);
			break;
		case "Sự kiện lịch sử":
			copyTable(eventTable, (TableView<HistoricalEvent>) tableData);
			break;
		case "Lễ hội văn hóa":
			copyTable(festivalTable, (TableView<Festival>) tableData);
			break;
		case "Triều đại lịch sử":
			copyTable(eraTable, (TableView<Era>) tableData);
			break;
		default:
			System.out.println("Loi");
		}
	}

	// search controller
	public void SearchByName(ActionEvent event) {

		String searchName = searchInfo.getText();
		String type = selectedItem.getId();

		tableData.getColumns().clear();
		switch (type) {

		case "historicalFigureItem":
			searchingTable(historicalFigureList, historicalFigureTableFieldName, historicalFigureTableFieldProperty, searchName);
			break;
		case "eraItem":
			searchingTable(eraList, eraTableFieldName, eraTableFieldProperty, searchName);
			break;
		case "festivalItem":
			searchingTable(festivalList, festivalTableFieldName, festivalTableFieldProperty , searchName);
			break;
		case "eventItem":
			searchingTable(eventList, eventTableFieldName, eventTableFieldProperty, searchName);
			break;
		case "historicSiteItem":
			searchingTable(historicSiteList, historicSiteTableFieldName, historicSiteTableFieldProperty, searchName);
			break;
		default:
			System.out.println("Loi");
		}
		// searchInfo.setText("");
	}

	// Clear Button Action
	public void ClearSearch(ActionEvent event) {
		searchInfo.setText("");
		String type = selectedItem.getId();
		tableData.getColumns().clear();
		switch (type) {

		case "historicalFigureItem":
			copyTable(historicalFigureTable, (TableView<HistoricalFigure>) tableData);
			break;
		case "eraItem":
			copyTable(eraTable, (TableView<Era>) tableData);
			break;
		case "festivalItem":
			copyTable(festivalTable, (TableView<Festival>) tableData);

			break;
		case "eventItem":
			copyTable(eventTable, (TableView<HistoricalEvent>) tableData);
			break;
		case "historicSiteItem":
			copyTable(historicSiteTable, (TableView<HistoricalSite>) tableData);
			break;
		default:
			System.out.println("Loi");
		}
	}

	// Setting data2 with searchName
	protected <T> void searchingTable(ObservableList<T> data, List<String> columnName, List<String> columnProperty,
			String searchName) {

		ObservableList<T> data2 = FXCollections.observableArrayList();
		TableView<T> view2 = new TableView<>();

		//
		if (!data.isEmpty()) {
			for (int i = 0; i < data.size(); i++) {
				if (data.get(i) instanceof Historical) {
					Historical a = (Historical) data.get(i);
					if (a.checking(searchName)) {
						data2.add(data.get(i));
					}
				}

			}

		}

		if (!data2.isEmpty()) {
			if (data2.get(0) instanceof Historical) {
				Historical a = (Historical) data2.get(0);
				searchInfo.setText(a.getName());
			}
		}
		settingTable(view2, data2, columnName, columnProperty);
		tableData.getColumns().clear();
		copyTable(view2, (TableView<T>) tableData);

	}

	private <T> void settingTable(TableView<T> table, ObservableList<T> data, List<String> columnName,
			List<String> columnProperty) {
		table.setItems(data);
		for (int i = 0; i < columnName.size(); ++i) {
			TableColumn<T, ?> column = new TableColumn<>(columnName.get(i));
			if (columnName.get(i) == "STT") {
				column.setMinWidth(50);
			} else
				column.prefWidthProperty()
						.bind(tableData.widthProperty().multiply((1 - 0.1) / (columnName.size() - 1)));
			column.setCellValueFactory(new PropertyValueFactory<>(columnProperty.get(i)));
			table.getColumns().add(column);
		}
	}

	private <T> void copyTable(TableView<T> originalTable, TableView<T> newTable) {
		newTable.setItems((ObservableList<T>) originalTable.getItems());
		for (TableColumn<T, ?> column : originalTable.getColumns()) {
			newTable.getColumns().add(column);
		}
		setEventClickOnRow((TableView<T>) tableData);
	}
	
	private <T> void setEventClickOnRow(TableView<T> mainTable) {
		mainTable.setRowFactory(tv -> {
		    TableRow<T> row = new TableRow<>();
		    row.setOnMouseClicked(event -> {
		        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
		            T rowData = (T) row.getItem();
		            if(rowData instanceof HistoricalFigure) {
		            	try {
							popupData((HistoricalFigure) rowData, 
									historicalFigureTableFieldName, 
									historicalFigureTableFieldProperty,
									historicalFigureLinkFieldName,
									historicalFigureLinkFieldProperty,
									"Nhan vat lich su");
						} catch (IOException e) {
							e.printStackTrace();
						}
		            }
		        }
		    });
		    return row;
		});
	}
	
	private <T> void popupData(T data, List<String> fieldName, List<String> property, List<String> linkField, List<String> linkProperty, String title) throws IOException{
		BorderPane root = FXMLLoader.load(getClass().getResource("Profile.fxml"));
		Stage stage = new Stage();
		stage.setTitle("Thong tin chi tiet");
		VBox vBoxTop = (VBox) root.getTop();
		Text k = (Text) vBoxTop.getChildren().get(1);
		k.setText(title);
//		for (Node node : vBoxTop.getChildren()) {
//			if (node instanceof TextField) {
//				((TextField) node).setText(title);
//			}
//			if (node instanceof ImageView) {
//				// anh
//			}
//		}
//		VBox vBoxCenter = (VBox) root.getTop();
//		VBox vBoxChild = new VBox();
//		for (Node node : vBoxCenter.getChildren()) {
//		    if (node instanceof VBox) {
//		    	vBoxChild = (VBox) node;
//		        break;
//		    }
//		}
//		HBox hBox = new HBox();
//		for (Node node : vBoxChild.getChildren()) {
//		    if (node instanceof HBox) {
//		    	hBox = (HBox) node;
//		        break;
//		    }
//		}
//		
//		for (Node node : hBox.getChildren()) {
//		    if (node instanceof Label) {
//		    	((Label) node).setText("Hehe");
//		    }
//		    if (node instanceof Text) {
//		    	((Label) node).setText("Hehe");
//		    }
//		}
//		
		stage.setScene(new Scene(root));
		stage.show();
	}
}
