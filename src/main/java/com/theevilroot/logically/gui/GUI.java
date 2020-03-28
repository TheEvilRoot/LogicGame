package com.theevilroot.logically.gui;

import com.theevilroot.logically.common.elements.LogicCircuit;
import com.theevilroot.logically.common.elements.LogicElement;
import com.theevilroot.logically.common.gui.DrawerFactory;
import com.theevilroot.logically.common.gui.IDrawerFactory;
import com.theevilroot.logically.common.gui.drawers.LogicCircuitDrawer;
import com.theevilroot.logically.common.gui.drawers.LogicElementDrawer;
import com.theevilroot.logically.common.gui.drawers.LogicOutputPortDrawer;
import com.theevilroot.logically.common.gui.drawers.LogicPortDrawer;
import com.theevilroot.logically.common.ports.LogicOutputPort;
import com.theevilroot.logically.common.ports.LogicPort;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GUI extends Application {

    private final PlatformPane platformPane;
    private final VBox root;
    private final Scene mainScene;

    private final DrawerFactory drawerFactory = new DrawerFactory();

    public GUI() {
        this.root = new VBox();
        this.drawerFactory.addDrawer(LogicCircuit.class, new LogicCircuitDrawer());
        this.drawerFactory.addDrawer(LogicElement.class, new LogicElementDrawer());
        this.drawerFactory.addDrawer(LogicPort.class, new LogicPortDrawer());
        this.drawerFactory.addDrawer(LogicOutputPort.class, new LogicOutputPortDrawer());
        this.platformPane = new PlatformPane(this.drawerFactory);
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
