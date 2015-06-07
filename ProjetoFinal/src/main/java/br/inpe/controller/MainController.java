package br.inpe.controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainController extends Application {
	private Hyperlink linkTwitter; 
	private ListView<String> lvTweets;

	@Override
	public void start(Stage stage) throws Exception {
		AnchorPane pane = new AnchorPane();
		pane.setPrefSize(400, 300);
		TextField txLogin = new TextField();
		txLogin.setPromptText("Digite aqui seu login");
		PasswordField txSenha = new PasswordField();
		txSenha.setPromptText("Digite aqui sua senha");
		Button btEntrar = new Button("Entrar");
		Button btSair = new Button("Sair");
		linkTwitter = new Hyperlink("Clique aqui para ver nossos tweets..."); 
		lvTweets = new ListView<String>(); lvTweets.setVisible(false); // Tornando a lista invisível 
//		pane.getChildren().addAll(txPesquisa, tbVitrine, linkTwitter, lvTweets);
	

		pane.getChildren().addAll(txLogin, txSenha, btEntrar, btSair, linkTwitter, lvTweets);
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);
	}

}
