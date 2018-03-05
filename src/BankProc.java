

public interface BankProc {
	// add/rm persoana in lista de clienti din Bank
	// add/rm cont din setul de conturi din Bank
	/**
	 * @Pre:p!=null, hashmap.containsKey(p)==true
	 * 
	 * @Post:hashmap.containsValue(accounts)==true;
	 */
	public void addPerson(Person p);

	/**
	 * @Pre:p!=null;
	 * 
	 * @Post:hashmap.containsKey(p)==false;
	 */
	public void removePerson(Person p);

	/**
	 * @Pre:holder!=null, a!=null
	 * 
	 * @Post:accounts.isEmpty()==false
	 */

	public void addAccount(Person holder, Account a);

	/**
	 * @Pre:holder!=null, a!=null
	 * 
	 * @Post:accounts.contains(a)==false
	 */

	public void removeAccount(Person holder, Account a);
	/**
	 * @Pre:d>0
	 * 
	 * @Post:a.getCurrentSum()>0
	 */
	public void depositMoney(Account a, double d);
	/**
	 * @Pre:d>0
	 * 
	 * @Post:(a.getCurrentSum()>0) || (a.getCurrentSum()==0)
	 */
	public void withdrawMoney(Account a, double d);
	/**
	 * @Pre:holder!=null, a!=null
	 * 
	 * @Post:accounts.contains(a)==false
	 */
	
	public void removeAccount(Person holder, long iban);

}
