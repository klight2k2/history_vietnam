package view;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JOptionPane;

import crawl.CrawlController;
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
import javafx.scene.image.Image;
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
	private List<String> historicalFigureLinkFieldName = Arrays.asList("Triều đại", "Sự kiện liên quan");
	private List<String> historicalFigureLinkFieldProperty = Arrays.asList("relatedEraId", "relatedEventId");
	private List<String> historicSiteTableFieldName = Arrays.asList("STT", "Tên", "Địa điểm", "Đối tượng thờ", "Loại hình xếp hạng", "Loại xếp hạng");
	private List<String> historicSiteTableFieldProperty = Arrays.asList("id", "name", "location", "objectWorship", "loaiHinhXepHang", "loaiXepHang");
	private List<String> historicSiteLinkFieldName = Arrays.asList();
	private List<String> historicSiteLinkFieldProperty = Arrays.asList();
	private List<String> eventTableFieldName = Arrays.asList("STT", "Tên", "Bắt đầu", "Kết thúc");
	private List<String> eventTableFieldProperty = Arrays.asList("id", "name", "startDate", "endDate");
	private List<String> eventLinkFieldName = Arrays.asList("Triều đại");
	private List<String> eventLinkFieldProperty = Arrays.asList("relatedEraId");
	private List<String> festivalTableFieldName = Arrays.asList("STT", "Tên", "Địa điểm", "Suy tôn", "Thời gian");
	private List<String> festivalTableFieldProperty = Arrays.asList("id", "name", "location", "doiTuongSuyTon", "holdTime");
	private List<String> festivalLinkFieldName = Arrays.asList();
	private List<String> festivalLinkFieldProperty = Arrays.asList();
	private List<String> eraTableFieldName = Arrays.asList("STT", "Tên", "Từ năm", "Đến năm");
	private List<String> eraTableFieldProperty = Arrays.asList("id", "name", "fromYear", "toYear");
	private List<String> eraLinkFieldName = Arrays.asList("Thời đại trước", "Thời đại sau");
	private List<String> eraLinkFieldProperty = Arrays.asList("isPrecededBy", "isSuccessedBy");
	
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
	
	// crawl button
	public void Crawl(ActionEvent event) {
//		new CrawlController();
		JOptionPane.showMessageDialog(null, "Tải dữ liệu thành công", "Success", JOptionPane.INFORMATION_MESSAGE);
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
		            	try {
		            		if(rowData instanceof HistoricalFigure) {
								popupData((HistoricalFigure) rowData, 
										historicalFigureTableFieldName, 
										historicalFigureTableFieldProperty,
										historicalFigureLinkFieldName,
										historicalFigureLinkFieldProperty,
										"Nhân vật lịch sử");
			            		}
		            		if(rowData instanceof HistoricalSite) {
		            			popupData((HistoricalSite) rowData, 
		            					historicSiteTableFieldName, 
										historicSiteTableFieldProperty,
										historicSiteLinkFieldName,
										historicSiteLinkFieldProperty,
										"Di tích lịch sử");
		            		}
		            		if(rowData instanceof HistoricalEvent) {
		            			popupData((HistoricalEvent) rowData, 
		            					eventTableFieldName, 
										eventTableFieldProperty,
										eventLinkFieldName,
										eventLinkFieldProperty,
										"Sự kiện lịch sử");
		            		}
		            		if(rowData instanceof Festival) {
		            			popupData((Festival) rowData, 
		            					festivalTableFieldName, 
		            					festivalTableFieldProperty,
		            					festivalLinkFieldName,
		            					festivalLinkFieldProperty,
										"Lễ hội văn hóa");
		            		}
		            		if(rowData instanceof Era) {
		            			popupData((Era) rowData, 
		            					eraTableFieldName, 
		            					eraTableFieldProperty,
		            					eraLinkFieldName,
		            					eraLinkFieldProperty,
										"Triều đại lịch sử");
		            		}
						} catch (IOException e) {
							e.printStackTrace();
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
		
		// set title
		VBox vBoxTop = (VBox) root.getTop();
		Text titleEle = (Text) vBoxTop.getChildren().get(1);
		titleEle.setText(title);
		
		// set avatar
		ImageView avatar = (ImageView) vBoxTop.getChildren().get(0);
		if(data instanceof HistoricalFigure) {
			String url = ((HistoricalFigure) data).getImageLink();
			if(url.equals("")) {
				File file = new File("src/resource/nhanvatlichsu.png");
				avatar.setImage(new Image(file.toURI().toString()));
				System.out.println("hehe");
			}else {
				Image i = new Image(url);
				avatar.setImage(i);				
			}
		}
		if(data instanceof HistoricalSite) {
			String url = ((HistoricalSite) data).getImageLink();
			if(url.equals("")) {
				File file = new File("src/resource/ditich.png");
				avatar.setImage(new Image(file.toURI().toString()));
			}else {
				Image i = new Image(url);
				avatar.setImage(i);				
			}
		}
		if(data instanceof Festival) {
			String url = ((Festival) data).getImageLink();
			if(url.equals("")) {
				File file = new File("src/resource/lehoi.png");
				avatar.setImage(new Image(file.toURI().toString()));
			}else {
				Image i = new Image(url);
				avatar.setImage(i);				
			}
		}
		if(data instanceof Era) {
			File file = new File("src/resource/trieudai.png");
			avatar.setImage(new Image(file.toURI().toString()));
		}
		if(data instanceof HistoricalEvent) {
			File file = new File("src/resource/sukien.png");
			avatar.setImage(new Image(file.toURI().toString()));
		}
		
		
		
		// get field element
		VBox vBoxCenter = (VBox) root.getCenter();
		VBox vBoxField = (VBox) vBoxCenter.getChildren().get(0);
		VBox vBoxLink = (VBox) vBoxCenter.getChildren().get(1);
		
		vBoxCenter.getChildren().clear();
		// set field element
		vBoxCenter.getChildren().add(createPopupElement("Tên", Arrays.asList(((Historical) data).getName()), "ProfileItemField"));
		for(int i = 0; i < fieldName.size(); ++i) {
			try {
				Class<T> clazz = (Class<T>) data.getClass();
				Field field = clazz.getDeclaredField(property.get(i));
				field.setAccessible(true);
				Object propertyValue = field.get(data);
				vBoxCenter.getChildren().add(createPopupElement(fieldName.get(i), Arrays.asList(propertyValue.toString()), "ProfileItemField"));
			} catch (NoSuchFieldException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		// set link element
		for(int i = 0; i < linkField.size(); ++i) {
			try {
				Class<T> clazz = (Class<T>) data.getClass();
				Field field = clazz.getDeclaredField(linkProperty.get(i));
				field.setAccessible(true);
				Object propertyValue = field.get(data);
				// get all name of link object
				List<String> nameLink = new ArrayList<String>();
				if(data instanceof HistoricalFigure) {
					for(int j : (ArrayList<Integer>) propertyValue) {
						if(linkProperty.get(i) == "relatedEraId") {
							Era era = eraList.stream().filter(obj -> obj.getId() == j).findFirst().orElse(null);
							nameLink.add(era.getName());
						}else if(linkProperty.get(i) == "relatedEventId") {
							HistoricalEvent event = eventList.stream().filter(obj -> obj.getId() == j).findFirst().orElse(null);
							nameLink.add(event.getName());
						}
					}				
				}
				if(data instanceof HistoricalEvent || data instanceof Era) {
					Era era = eraList.stream().filter(obj -> obj.getId() == (Integer) propertyValue).findFirst().orElse(null);
					nameLink.add(era.getName());
				}
				
				vBoxCenter.getChildren().add(createPopupElement(linkField.get(i), nameLink, "ProfileItemLink"));
			} catch (NoSuchFieldException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		stage.setScene(new Scene(root));
		stage.show();
	}
	
	private VBox createPopupElement(String field, List<String> content, String type) {
		VBox newVbox = new VBox();
		try {
			newVbox = (VBox) FXMLLoader.load(getClass().getResource(type + ".fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ImageView image = (ImageView)(((HBox)(newVbox.getChildren().get(0))).getChildren().get(0));	
		if(Arrays.asList("Địa điểm", "Nơi sinh").contains(field)) {
			File file = new File("src/resource/location.png");
			image.setImage(new Image(file.toURI().toString()));
		}
		if(Arrays.asList("Đối tượng thờ", "Suy tôn").contains(field)) {
			File file = new File("src/resource/person.png");
			image.setImage(new Image(file.toURI().toString()));
		}
		if(Arrays.asList("Triều đại", "Thời đại trước", "Thời đại sau").contains(field)) {
			File file = new File("src/resource/trieudai.png");
			image.setImage(new Image(file.toURI().toString()));
		}
		if(Arrays.asList("Năm sinh", "Năm mất", "Từ năm", "Đến năm", "Thời gian", "Bắt đầu", "Kết thúc").contains(field)) {
			File file = new File("src/resource/time.png");
			image.setImage(new Image(file.toURI().toString()));
		}
		if(Arrays.asList("Loại hình xếp hạng", "Loại xếp hạng").contains(field)) {
			File file = new File("src/resource/trophy.png");
			image.setImage(new Image(file.toURI().toString()));
		}
		if(Arrays.asList("Sự kiện liên quan").contains(field)) {
			File file = new File("src/resource/sukien.png");
			image.setImage(new Image(file.toURI().toString()));
		}
		Label label = (Label)(((HBox)(newVbox.getChildren().get(0))).getChildren().get(1));
		if(type == "ProfileItemField") {
			Text text = (Text)(((HBox)(newVbox.getChildren().get(0))).getChildren().get(2));
			label.setText(field);
			text.setText(content.get(0));
		}else if(type == "ProfileItemLink") {
			label.setText(field);
			VBox textBox = (VBox)(((HBox)(newVbox.getChildren().get(0))).getChildren().get(2));
			Text text = (Text) textBox.getChildren().get(0); 
			textBox.getChildren().clear();
			for(String t : content) {
				Text newText = new Text(t);
				newText.setWrappingWidth(380);
				textBox.getChildren().add(newText);
			}
		}
		return newVbox;	
	}
}
