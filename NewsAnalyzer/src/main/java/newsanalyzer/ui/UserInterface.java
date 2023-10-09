package newsanalyzer.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import newsanalyzer.ctrl.*;
import newsapi.*;
import newsapi.enums.*;

public class UserInterface
{

	private final Controller ctrl = new Controller();

	public void getDataFromCtrl1(){
		System.out.println("Aktuelles zu Corona in Österreich");

		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(Controller.APIKEY)
				.setQ("corona")
				.setFrom("2023-10-09")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(Country.at)
				.setSourceCategory(Category.health)
				.createNewsApi();

		try{
			ctrl.process(newsApi);
		}
		catch(NewsApiException e) {
			System.out.println(e.getMessage());
		}
	}


	public void getDataFromCtrl2(){
		System.out.println("Aktuelles zu Sport in Österreich");

		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(Controller.APIKEY)
				.setQ("sport")
				.setFrom("2023-10-09")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCountry(Country.at)
				.setSourceCategory(Category.sports)
				.createNewsApi();

		try{
			ctrl.process(newsApi);
		}
		catch(NewsApiException e) {
			System.out.println(e.getMessage());
		}
	}


	public void getDataFromCtrl3(){
		System.out.println ("Aktuelle Science News aus Österreich" );

		NewsApi newsApi = new NewsApiBuilder()
				.setApiKey(Controller.APIKEY)
				.setQ("news")
				.setFrom("2023-10-09")
				.setEndPoint(Endpoint.TOP_HEADLINES)
				.setSourceCategory(Category.science)
				.setSourceCountry(Country.at)
				.createNewsApi();

		try{
			ctrl.process(newsApi);
		}
		catch(NewsApiException e) {
			System.out.println(e.getMessage());
		}

	}



	public void start() {
		Menu<Runnable> menu = new Menu<>("User Interface");
		menu.setTitel("Wählen Sie aus:");
		menu.insert("a", "Corona News", this::getDataFromCtrl1);
		menu.insert("b", "Aktuelle Science News aus Österreich", this::getDataFromCtrl3);
		menu.insert("c", "Sport News Austria", this::getDataFromCtrl2);
		menu.insert("q", "Quit", null);
		Runnable choice;
		while ((choice = menu.exec()) != null) {
			choice.run();
		}
		System.out.println("Program finished");
	}


	protected String readLine() {
		String value = "\0";
		BufferedReader inReader = new BufferedReader(new InputStreamReader(System.in));
		try {
			value = inReader.readLine();
		} catch (IOException ignored) {
		}
		return value.trim();
	}

	protected Double readDouble(int lowerlimit, int upperlimit) 	{
		Double number = null;
		while (number == null) {
			String str = this.readLine();
			try {
				number = Double.parseDouble(str);
			} catch (NumberFormatException e) {
				number = null;
				System.out.println("Please enter a valid number:");
				continue;
			}
			if (number < lowerlimit) {
				System.out.println("Please enter a higher number:");
				number = null;
			} else if (number > upperlimit) {
				System.out.println("Please enter a lower number:");
				number = null;
			}
		}
		return number;
	}
}
