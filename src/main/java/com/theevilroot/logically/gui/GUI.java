package com.theevilroot.logically.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GUI extends Application {

    private final PlatformPane platformPane;
    private final VBox root;
    private final Scene mainScene;

    public GUI() {
        this.root = new VBox();
        this.platformPane = new PlatformPane();
        this.mainScene = new Scene(root);

        this.root.getChildren().add(platformPane);
    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Logically");
        stage.initStyle(StageStyle.UTILITY);
        stage.setMinHeight(600);
        stage.setMinWidth(900);
        stage.setScene(mainScene);

        this.root.minHeightProperty().bind(stage.heightProperty());
        this.root.minWidthProperty().bind(stage.widthProperty());

        this.root.maxHeightProperty().bind(stage.heightProperty());
        this.root.maxWidthProperty().bind(stage.widthProperty());

        platformPane.minHeightProperty().bind(this.root.heightProperty());
        platformPane.minWidthProperty().bind(this.root.widthProperty());

        platformPane.maxHeightProperty().bind(this.root.heightProperty());
        platformPane.maxWidthProperty().bind(this.root.widthProperty());

        stage.show();
    }

}
