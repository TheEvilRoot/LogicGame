package com.theevilroot.logically.gui;

import com.theevilroot.logically.common.elements.LogicCircuit;
import com.theevilroot.logically.common.elements.LogicElement;
import com.theevilroot.logically.common.view.drawers.factory.impl.DrawerFactory;
import com.theevilroot.logically.common.view.drawers.impl.LogicCircuitDrawer;
import com.theevilroot.logically.common.view.drawers.impl.LogicElementDrawer;
import com.theevilroot.logically.common.view.drawers.impl.LogicPortDrawer;
import com.theevilroot.logically.common.ports.LogicPort;
import com.theevilroot.logically.common.view.drawers.impl.UITextViewDrawer;
import com.theevilroot.logically.common.ui.UITextView;
import com.theevilroot.logically.gui.platform.PlatformPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GUI extends Application {

    private final PlatformPane platformPane;
    private final VBox root;
    private final Scene mainScene;

    private final Button addButton;

    public GUI() {
        this.root = new VBox();
        DrawerFactory drawerFactory = new DrawerFactory();
        drawerFactory.addDrawer(LogicCircuit.class, new LogicCircuitDrawer());
        drawerFactory.addDrawer(LogicElement.class, new LogicElementDrawer());
        drawerFactory.addDrawer(LogicPort.class, new LogicPortDrawer());

        drawerFactory.addDrawer(UITextView.class, new UITextViewDrawer());

        this.platformPane = new PlatformPane(drawerFactory);
        this.mainScene = new Scene(root);

        this.addButton = new Button("Add");
        this.addButton.setOnMouseClicked(e -> {
        });

        this.root.getChildren().add(platformPane);

    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Logically");
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
