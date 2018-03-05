import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class Bank implements BankProc, java.io.Serializable {
	/**
	 * A serializable class can declare its own serialVersionUID explicitly by
	 * declaring a field named "serialVersionUID" that must be static, final,
	 * and of type long:
	 * 
	 */
	private static final long serialVersionUID = 2824536194251L;
	private Map<Person, HashSet<Account>> hashmap;
;


	public Map<Person, HashSet<Account>> getHashmap() {
		return hashmap;
	}

	public void setHashmap(Map<Person, HashSet<Account>> hashmap) {
		this.hashmap = hashmap;
	}

	public Bank() {
		// TODO Auto-generated constructor stub
		hashmap = new HashMap<Person, HashSet<Account>>();
	}

	@Override
	public void addPerson(Person p) {
		// fiecare client al bancii are o lista de conturi
		assert p != null;
		assert hashmap.containsKey(p) == true;
		Random r = new Random();
		HashSet<Account> accounts = new HashSet<Account>();
		if (!hashmap.containsKey(p)) {// daca nu se afla pe lista de clienti
			//p.setCnp(r.nextInt((100000000-1000000)+1000000));
			hashmap.put(p, accounts);// adaug clientul
			//clients.add(p);

			System.out.println("Person added!");

		} else {
			System.out.println("Persoana este deja client!");
		}
		
		assert isWellFormed() == true;

		assert hashmap.containsValue(accounts) == true;
	}

	@Override
	public void removePerson(Person p) {
		// TODO Auto-generated method stub

		assert p != null;
		assert isWellFormed() == true;

		
		if (hashmap.containsKey(p)) {
			hashmap.remove(p);
			//clients.remove(p);
			System.out.println("Person removed!");
		} else {
			System.out.println("Persoana nu este client al bancii!");
		}
		assert hashmap.containsKey(p) == false;

		

	}

	public void listPersons() {

		assert isWellFormed() == true;
		System.out.println("List of clients:");
		 Set<Entry<Person, HashSet<Account>>> set = hashmap.entrySet();
		 Iterator<Entry<Person, HashSet<Account>>> i = set.iterator();
		 while (i.hasNext()) {
		 Map.Entry<Person, HashSet<Account>> entry = (Map.Entry<Person,
		 HashSet<Account>>) i.next();

		 Person p = entry.getKey();
		 System.out.println(p);
		 }
		
	}
	public boolean isClient(Person p){
		Person ps = new Person();
		Set<Entry<Person, HashSet<Account>>> set = hashmap.entrySet();
		 Iterator<Entry<Person, HashSet<Account>>> i = set.iterator();
		 while (i.hasNext()) {
		 Map.Entry<Person, HashSet<Account>> entry = (Map.Entry<Person,
		 HashSet<Account>>) i.next();
		 ps = entry.getKey();
		 if(ps.equals(p)){
			 return true;

		 }
		 }
		 return false;
		 
	}

	@Override
	public void addAccount(Person holder, Account a) {
		// TODO Auto-generated method stub

		assert holder != null;
		assert a != null;
		assert isWellFormed() == true;

		a.addObserver(holder);
		HashSet<Account> accounts = new HashSet<Account>();
		Random r = new Random();
		a.setIban(r.nextInt(10000-1000+1)+1000);
		
		accounts = hashmap.get(holder);
		if (a.getType() == "spending") {
			SpendingAccount spend = new SpendingAccount(a.getIban(),
					a.getHolder(), a.getCurrentSum(), a.getType());
			accounts.add(spend);// adaug contul la lista
		} else if (a.getType() == "saving") {
			SavingsAccount save = new SavingsAccount(a.getIban(),
					a.getHolder(), a.getCurrentSum(), a.getType());
			accounts.add(save);
		} else {
			System.out.println("Contul nu se poate crea!");
		}
		assert accounts.isEmpty() == false;
		assert hashmap.containsValue(a) == true;

	}

	@Override
	public void removeAccount(Person holder, Account a) {//sterg contul a dupa iban
		// TODO Auto-generated method stub

		assert holder != null;
		assert a != null;
		assert isWellFormed() == true;

		a.deleteObserver(holder);
		HashSet<Account> accounts = new HashSet<Account>();
		accounts = hashmap.get(holder);// obtin lista de conturi
		Account acc;
		Iterator<Account> i = accounts.iterator();//o parcurg cu un iterator
		while (i.hasNext()) {
			acc = i.next();
		//	if (acc.getClass() == SpendingAccount.class) {
			if(acc instanceof SpendingAccount){
				if (((SpendingAccount) acc).getIban() == a.getIban()) {
					i.remove();
					System.out.println("Account " + a + " removed");
				}

			}
			if (acc.getClass() == SavingsAccount.class) {
				if (((SavingsAccount) acc).getIban() == a.getIban()) {
					i.remove();
					System.out.println("Account " + a + " removed");

				}
			}
			
		}

		
		assert accounts.contains(a) == false;

	}

	public void listAccounts(Person holder) {
		assert isWellFormed() == true;

		if (holder != null) {
			HashSet<Account> accounts = new HashSet<Account>();
			accounts = hashmap.get(holder);
			if (!accounts.isEmpty()) {
				System.out.println("The account list of holder is: ");
				for (Account a : accounts) {
					System.out.println(a.toString());
				}
			} else {
				System.out.println("The client doesn't own an account");
			}

		}

	}

	@Override
	public void depositMoney(Account a, double d) {
		// TODO Auto-generated method stub

		assert d > 0;
		assert isWellFormed() == true;

		a.depositSum(d);
		if (a instanceof SavingsAccount) {
			((SavingsAccount) a).calcInterest();
		}
		assert a.getCurrentSum() > 0;
	}

	@Override
	public void withdrawMoney(Account a, double d) {
		// TODO Auto-generated method stub

		assert d > 0;
		assert isWellFormed() == true;

		a.withdrawSum(d);
		if (a instanceof SavingsAccount) {
			((SavingsAccount) a).calcInterest();
		}
		
		assert (a.getCurrentSum() > 0) || (a.getCurrentSum() == 0);
	}

	public boolean isWellFormed() {
		if (hashmap.isEmpty()) {
			return false;

		}
		Set<Person> clients = hashmap.keySet();
		Iterator<Person> i = clients.iterator();
		while(i.hasNext()){
			Person p = i.next();
			HashSet<Account> accounts = hashmap.get(p);
			if(accounts.isEmpty())
				return false;
		}
		return true;

	}
	/**
	 * Serialize
	 */
	public void saveData() {
		try {
			FileOutputStream fileOut = new FileOutputStream("Bank.ser");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(this);
			out.close();
			fileOut.close();
		} catch (IOException i) {
			i.printStackTrace();
		}
	}

	/**
	 * Deserialize 
	 */
	public Bank loadData() {
		Bank b = null;
		try {
			FileInputStream fileIn = new FileInputStream("Bank.ser");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			b = (Bank) in.readObject();
			in.close();
			fileIn.close();
		} catch (IOException i) {
			i.printStackTrace();

		} catch (ClassNotFoundException c) {
			System.out.println("Bank class not found");
			c.printStackTrace();

		}
		return b;
	}

	@Override
	public void removeAccount(Person holder, long iban) {
		// TODO Auto-generated method stub
		assert holder != null;
		assert isWellFormed() == true;

		HashSet<Account> accounts = new HashSet<Account>();
		accounts = hashmap.get(holder);// obtin lista de conturi
		Account acc;
		Iterator<Account> i = accounts.iterator();
		while (i.hasNext()) {
			acc = i.next();
			if (acc.getClass() == SpendingAccount.class) {
				if (((SpendingAccount) acc).getIban() == iban) {
					i.remove();
					System.out.println("Account with IBAN " + iban +" removed");
				}

			}
			if (acc.getClass() == SavingsAccount.class) {
				if (((SavingsAccount) acc).getIban() == iban) {
					i.remove();
					System.out.println("Account with IBAN" + iban + " removed");

				}
			}
			
	}
	}
}
