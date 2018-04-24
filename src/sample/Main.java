package sample;

import javafx.application.Application;
import javafx.concurrent.Worker;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class Main extends Application {

    private Scene scene;

    @Override
    public void start(Stage stage) {

        stage.setTitle("交易数据展示系统");
        Browser browser = new Browser();
        scene = new Scene(browser, 1800, 1200, Color.web("#666970"));
        stage.setScene(scene);
        stage.show();
        browser.webEngine.getLoadWorker().stateProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue == Worker.State.SUCCEEDED) {
                System.out.println("finished loading");
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }

    class Browser extends Region {

        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();

        public Browser() {
            getStyleClass().add("browser");
            webEngine.load("http://localhost:5000");
            webEngine.setJavaScriptEnabled(true);
            getChildren().add(browser);

        }

        @Override
        protected void layoutChildren() {
            double w = getWidth();
            double h = getHeight();
            layoutInArea(browser, 0, 0, w, h, 0, HPos.CENTER, VPos.CENTER);
        }

        @Override
        protected double computePrefWidth(double height) {
            return 1800;
        }

        @Override
        protected double computePrefHeight(double width) {
            return 1200;
        }
    }
}

