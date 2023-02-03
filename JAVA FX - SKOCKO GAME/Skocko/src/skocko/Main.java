package skocko;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class Main extends Application {

    private static int brojac = 0;
    private static boolean regulatorKraja = true;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        Logika log = new Logika();

//        izbaci mi kombinaciju na standardni izlaz - zgodno za uporedjivanje dok programiras!
        for (Znakovi znak : log.tacnaKomb)
            System.out.print(znak + " ");
        System.out.println();

        VBox root = new VBox(16);
        root.setPadding(new Insets(20));
        root.setStyle("-fx-background-color: linear-gradient(#3374FF, #33A5FF);");

        Image image = null;
        try {
            image = new Image(new FileInputStream("horse.jpg"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        //Setting the image view
        ImageView imageView = new ImageView(image);

        //Setting the position of the image
        imageView.setX(50);
        imageView.setY(25);

        //setting the fit height and width of the image view
        imageView.setFitHeight(600);
        imageView.setFitWidth(300);

        //Setting the preserve ratio of the image view
        imageView.setPreserveRatio(true);

        imageView.setBlendMode(BlendMode.DARKEN);

        HBox root1 = new HBox(20);
        HBox root2 = new HBox(10);
        HBox root3 = new HBox(30);

        root.getChildren().addAll(root1, root2, root3);

        //Radim prvo root1
        VBox VBoxLeft1 = new VBox(10);
        VBox VBoxRight1 = new VBox(10);

        //Radim VBoxLeft1
        Label[] labeleLeft = getLabelsLeft(24); //24 labele
        HBox[] hBoxOfVBoxLeft1 = gethBoxes(labeleLeft); // na svaki vertikalni box kacim po 4 labele


        VBoxLeft1.getChildren().addAll(hBoxOfVBoxLeft1);
        VBoxLeft1.setStyle(" -fx-border-color:white; -fx-background-color: linear-gradient(#3374FF, #33A5FF);");
        VBoxLeft1.setSpacing(10);
        VBoxLeft1.setPadding(new Insets(10, 10, 10, 10));

        //zavrsio VBoxLeft1
        //VboxRight1

        Circle[] circleRight = getCirclesRight();
        HBox[] hBoxOfVBoxRight1 = gethBoxes(circleRight);

        VBoxRight1.getChildren().addAll(hBoxOfVBoxRight1);
        VBoxRight1.setStyle(" -fx-border-color:white; -fx-background-color: linear-gradient(#3374FF, #33A5FF);");
        VBoxRight1.setSpacing(10);
        VBoxRight1.setPadding(new Insets(10, 10, 10, 10));

        //zavrsioVboxRight1

        root1.getChildren().addAll(VBoxLeft1, VBoxRight1, imageView);
        root1.setSpacing(20);


        //zavrsio root1
        //root2

        root2.setSpacing(20);
        HBox tacnaKombinacijaBox = new HBox(10);
        HBox boxZaDugmice = new HBox(10);
        boxZaDugmice.setPadding(new Insets(10));
        Label[] tacnaKombinacija = getLabelsLeft(4);
        Button[] dugmici = getButtons();


        tacnaKombinacijaBox.setPadding(new Insets(10));
        tacnaKombinacijaBox.getChildren().addAll(tacnaKombinacija);
        tacnaKombinacijaBox.setStyle(" -fx-border-color:white; -fx-background-color: linear-gradient(#3374FF, #33A5FF);");
        boxZaDugmice.getChildren().addAll(dugmici);
        boxZaDugmice.setStyle(" -fx-border-color:white; -fx-background-color: linear-gradient(#3374FF, #33A5FF);");
        root2.getChildren().addAll(tacnaKombinacijaBox, boxZaDugmice);
        root2.setPadding(new Insets(10, 10, 10, 0));
        root2.setStyle(" -fx-border-color:white; -fx-background-color: linear-gradient(#3374FF, #33A5FF);");

        //zavrsio root2
        //root3
        Label rezultat = new Label("");
        rezultat.setStyle(" -fx-border-color:white; -fx-background-color: linear-gradient(#EEE9E9, #33A5FF);");
        rezultat.setFont(Font.font(19));
        rezultat.setMinSize(60, 60);
        rezultat.setAlignment(Pos.CENTER);

        Button izlaz = new Button("Izlaz");
        izlaz.setStyle(" -fx-border-color:white; -fx-background-color: linear-gradient(#EEE9E9, #33A5FF);");
        izlaz.setFont(Font.font(23));

        Button novaIgra = new Button("Nova igra");
        novaIgra.setStyle(" -fx-border-color:white; -fx-background-color: linear-gradient(#EEE9E9, #33A5FF);");
        novaIgra.setFont(Font.font(23));

        Button undo = new Button("\u21b6  Korak unazad");
        undo.setStyle(" -fx-border-color:white; -fx-background-color: linear-gradient(#EEE9E9, #33A5FF);");
        undo.setFont(Font.font(25));
        undo.setMinSize(30, 30);
        undo.setAlignment(Pos.CENTER);

        root3.getChildren().addAll(rezultat, izlaz, novaIgra, undo);
        root3.setPadding(new Insets(20));
        root3.setAlignment(Pos.CENTER);


        //zavrsio root3
        //RADIMO DUGMICE I OSTALO

        dugmici[0].setOnAction(e ->
                buttonHandler(log, labeleLeft, circleRight, tacnaKombinacija, rezultat, Znakovi.KONJ, "000000"));

        dugmici[1].setOnAction(e ->
                buttonHandler(log, labeleLeft, circleRight, tacnaKombinacija, rezultat, Znakovi.TREF, "000000"));

        dugmici[2].setOnAction(e ->
                buttonHandler(log, labeleLeft, circleRight, tacnaKombinacija, rezultat, Znakovi.PIK, "000000"));

        dugmici[3].setOnAction(e ->
                buttonHandler(log, labeleLeft, circleRight, tacnaKombinacija, rezultat, Znakovi.HERC, "FF0000"));

        dugmici[4].setOnAction(e ->
                buttonHandler(log, labeleLeft, circleRight, tacnaKombinacija, rezultat, Znakovi.KARO, "FF0000"));

        dugmici[5].setOnAction(e ->
                buttonHandler(log, labeleLeft, circleRight, tacnaKombinacija, rezultat, Znakovi.ZVEZDA, "D7DA01"));

        izlaz.setOnAction(e -> Platform.exit());

        novaIgra.setOnAction(e -> {
            System.out.println("Restarting app!");

            stage.close();
            Platform.runLater(() -> new Main().start(new Stage()));
            brojac = 0;
            regulatorKraja = true;
        });

        undo.setOnAction(e -> {
            if (brojac <= 24 && brojac != 0 && regulatorKraja) {
                if (!(brojac % 4 == 0)) {
                    labeleLeft[brojac - 1].setText("");
                    brojac--;
                }
                if (brojac == 24)
                    regulatorKraja = false;
            }
        });


//        Scene scene = new Scene(root, 980, 700);
        Scene scene = new Scene(root, 980, 800);
        stage.setScene(scene);
        stage.setTitle("Skocko");
        stage.show();

    }

    private void buttonHandler(Logika log, Label[] labeleLeft, Circle[] circleRight, Label[] tacnaKombinacija, Label rezultat, Znakovi nesto, String s) {
        if (brojac < 24) {
            labeleLeft[brojac].setText(Character.toString(nesto.vrednost()));
            labeleLeft[brojac].setTextFill(Paint.valueOf(s));
            brojac++;
        }
        if (brojac % 4 == 0 && regulatorKraja) {
            log.poeni -= 5;
            int[] pomocniNiz = log.stanjeKombinacije(labeleLeft, brojac - 4);
            log.upisiUKruzice(circleRight, brojac - 4, pomocniNiz);

            boolean check = log.nizOdSvih0(pomocniNiz);
            if (check || brojac >= 24) {
                if (!check) log.poeni -= 5;
                rezultat.setText(Integer.toString(log.poeni));
                regulatorKraja = false;
                for (int i = 0; i < 4; i++) {
                    tacnaKombinacija[i].setText(log.unicode[i]);
                    if (log.tacnaKomb[i] == Znakovi.HERC || log.tacnaKomb[i] == Znakovi.KARO) {
                        tacnaKombinacija[i].setTextFill(Paint.valueOf("FF0000"));
                    } else if (log.tacnaKomb[i] == Znakovi.ZVEZDA)
                        tacnaKombinacija[i].setTextFill(Paint.valueOf("D7DA01"));
                }
                brojac = 24;
            }
        }
    }

    private HBox[] gethBoxes(Node[] labele) {
        int j = 0;
        HBox[] boxes = new HBox[6];
        for (int i = 0; i < 6; i++) {
            boxes[i] = new HBox(10);
            boxes[i].getChildren().addAll(new ArrayList<>(Arrays.asList(
                    labele[j], labele[j + 1], labele[j + 2], labele[j + 3])));
            j = j + 4;
            boxes[i].setSpacing(12);
        }
        return boxes;
    }

    private Label[] getLabelsLeft(int n) {
        Label[] labele = new Label[n];
        for (int i = 0; i < n; i++) {
            labele[i] = new Label();
            labele[i].setStyle("-fx-border-color: #bbbbbb; -fx-background-color: #CFE4E3;");
            labele[i].setAlignment(Pos.CENTER);
            labele[i].setMinSize(60, 50);
            labele[i].setFont(new Font(40));


        }
        return labele;
    }

    private Button[] getButtons() {
        Znakovi znak;

        Button[] dugmici = new Button[6];
        for (int i = 0; i < 6; i++) {
            znak = Znakovi.f(i);
            dugmici[i] = new Button();
            dugmici[i].setText(Character.toString(znak.vrednost()));
            dugmici[i].setStyle(" -fx-background-color: linear-gradient(#EEE9E9, #F4DCDC);");
            dugmici[i].setAlignment(Pos.CENTER);
            dugmici[i].setMinSize(60, 60);
            dugmici[i].setFont(Font.font(26));
        }

        dugmici[3].setTextFill(Paint.valueOf("FF0000"));
        dugmici[4].setTextFill(Paint.valueOf("FF0000"));
        dugmici[5].setTextFill(Paint.valueOf("D7DA01"));

        return dugmici;
    }

    private Circle[] getCirclesRight() {
        Circle[] circle = new Circle[24];
        for (int i = 0; i < 24; i++) {
            circle[i] = new Circle(30);
            circle[i].setFill(Paint.valueOf("314271"));


        }
        return circle;
    }
}

