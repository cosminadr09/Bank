import java.util.HashSet;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JOptionPane;

public class Person implements Observer, java.io.Serializable {
	private static final long serialVersionUID = 123000784698513L;
	private String name;
	private long cnp;
	//private HashSet<CAccount> accounts;

	public Person(String name, long id) {
		super();
		this.name = name;		
		this.cnp = id;
		//setAccounts(new HashSet<CAccount>());//un empty set 
	}

	public Person() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

	public long getCnp() {
		return cnp;
	}

	public void setCnp(long cnp) {
		this.cnp = cnp;
	}

//	public HashSet<CAccount> getAccounts() {
//		return accounts;
//	}
//
//	public void setAccounts(HashSet<CAccount> accounts) {
//		this.accounts = accounts;
//	}

	@Override
	public String toString() {
		return "CPerson [name=" + name + ", cnp=" + cnp + "]";
	}

	/**
	 * If two objects are equal according to the equals(Object) method, then
	 * calling the hashCode method on each of the two objects must produce the
	 * same integer result. 
	 * 
	 * It is not required that if two objects are unequal
	 * according to the equals(java.lang.Object) method, then calling the
	 * hashCode method on each of the two objects must produce distinct integer
	 * results. However, the programmer should be aware that producing distinct
	 * integer results for unequal objects may improve the performance of hash
	 * tables.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (cnp ^ (cnp >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())/**Returns the runtime class of this Object*/
			return false;
		Person other = (Person) obj;
		if (cnp != other.cnp)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}


	@Override
	public void update(Observable account, Object sum) {
		// ii trimit suma
		System.out.println("Account " + account.toString()
				+ "has been updated! Current sum: " + sum);
		JOptionPane.showMessageDialog(null, "Account has been updated! Current sum: " + sum);
	}
	

	

}
