package com.scrumtrek.simplestore;

class MainClass {

	private MainClass()
	{}

	static void Main() {
		Movie movCinderella = new Movie("Cinderella", PriceCodes.Childrens);
		Movie movStarWars = new Movie("Star Wars", PriceCodes.Regular);
		Movie movGladiator = new Movie("Gladiator", PriceCodes.NewRelease);

		Customer custMickeyMouse = new Customer("Mickey Mouse");

		Rental rental1 = new Rental(movCinderella, 5);
		Rental rental2 = new Rental(movStarWars, 5);
		Rental rental3 = new Rental(movGladiator, 5);

		custMickeyMouse.addRental(rental1);
		custMickeyMouse.addRental(rental2);
		custMickeyMouse.addRental(rental3);

		Statement statementGenerator = new Statement();
		StatementFormat format = new StringStatementFormat();

		String statement = statementGenerator.generateStatement(custMickeyMouse, format);

		System.out.println(statement);
	}
}

