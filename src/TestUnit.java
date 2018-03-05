

import static org.junit.Assert.*;

import org.junit.Test;

public class TestUnit {

	@Test
	public void test() {
	
		Bank bank = new Bank();
		Person p1 = new Person("Cosmina", 2960109123456L);
		bank.addPerson(p1);
		bank.listPersons();
		SavingsAccount save = new SavingsAccount(12345L, p1, 0.0, "saving");
		bank.addAccount(p1, save);
		SpendingAccount spend = new SpendingAccount(3251L, p1, 0.0,
				"spending");
		bank.addAccount(p1, spend);
		bank.listAccounts(p1);
		bank.depositMoney(save, 900);
		bank.withdrawMoney(save, 10000);
		bank.depositMoney(save, 10001);
		bank.depositMoney(spend, 501);
		bank.depositMoney(spend, 230);
		bank.withdrawMoney(spend, 235);
		bank.withdrawMoney(spend, 200);
		bank.removeAccount(p1, save);
		bank.listAccounts(p1);

		
		bank.saveData();
		
		Bank b = new Bank();
		b=bank.loadData();
		System.out.println("Dupa deserializare: ");
		b.listPersons();
		b.listAccounts(p1);
	}

}
