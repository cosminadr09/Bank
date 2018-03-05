import javax.swing.JOptionPane;

public class SavingsAccount extends Account implements java.io.Serializable {

	/**
	 * Dobanda se calculeaza pe perioada in care banii raman in cont. The saving
	 * account allows a single large sum deposit and withdrawal and computes an
	 * interest during the deposit period.
	 */
	private static final long serialVersionUID = 29874630210463L;
	private static final double INTEREST = 0.03;// dobanda
	public boolean deposit = false, withdraw = false;
	private static final int MINSUM = 10000;// suma minima pe care o pot depune
											// si retrage

	public SavingsAccount(long iban, Person holder, double currentSum,
			String type) {
		// TODO Auto-generated constructor stub
		super(iban, holder, currentSum, type);
	}

	public SavingsAccount(Person holder, String type) {
		super(holder, type);
	}

	@Override
	public void depositSum(double sum) {

		if (deposit == false) {// daca nu s-a depus nimic verific suma
			if (sum < MINSUM) {// se pot depune doar sume>MINSUM
				System.out.println("Nu se pot depune sume mai mici decat "
						+ MINSUM);
				JOptionPane.showMessageDialog(null,"Nu se pot depune sume mai mici decat "
						+ MINSUM );
			} else {// daca suma este valida
				double currentSum = getCurrentSum();
				currentSum = currentSum + sum;
				this.setCurrentSum(currentSum);
				deposit = true;
				System.out.println("Ati depus suma " + sum + " Noua suma:"
						+ currentSum);
				JOptionPane.showMessageDialog(null, "Ati depus suma " + sum + " Noua suma:"
						+ currentSum);
				this.setChanged();
				this.notifyObservers(currentSum);

			}
		} else {// daca s-a depus deja
			System.out
					.println("Nu se mai poate efectua nicio depunere in cont! ");
			JOptionPane.showMessageDialog(null,"Nu se mai poate efectua nicio depunere in cont! " );
		}

	}

	@Override
	public void withdrawSum(double sum) {
		// TODO Auto-generated method stub
		if (withdraw == false) {// daca nu s-a depus nimic verific suma
			double currentSum = this.getCurrentSum();
			if ((currentSum < sum) || (sum < 1500)) {// daca suma existenta este
														// mai mica decat suma
														// pe care doresc sa o
														// retrag
				System.out
						.println("Nu se poate retrage aceasta suma! Suma existenta este: "
								+ currentSum);
				JOptionPane.showMessageDialog(null,"Nu se poate retrage aceasta suma! Suma existenta este: "
						+ currentSum );
			} else {// daca suma este valida
				// double currentSum = getCurrentSum();
				currentSum = currentSum - sum;
				this.setCurrentSum(currentSum);
				withdraw = true;
				System.out.println("Ati retras suma " + sum + " Noua suma:"
						+ currentSum);
				JOptionPane.showMessageDialog(null, "Ati retras suma " + sum + " Noua suma:"
						+ currentSum);
				/**
				 * Marks this Observable object as having been changed; the
				 * hasChanged method will now return true.
				 */
				this.setChanged();
				/**
				 * If this object has changed, as indicated by the hasChanged
				 * method, then notify all of its observers and then call the
				 * clearChanged method to indicate that this object has no
				 * longer changed. Each observer has its update method called
				 * with two arguments: this observable object and the currentSum
				 * argument.
				 */
				this.notifyObservers(currentSum);

			}
		} else {// daca s-a depus deja
			System.out
					.println("Nu se mai poate efectua nicio retragere in cont! ");
			JOptionPane.showMessageDialog(null, "Nu se mai poate efectua nicio retragere in cont! ");
		}

	}

	public void calcInterest() {
		// la suma curenta ii adun dobanda
		double currentSum = getCurrentSum();
		double interest = INTEREST * currentSum;
		currentSum = currentSum + interest;
		this.setCurrentSum(currentSum);
		System.out.println("Dobanda: " + interest + " Suma cu dobanda: "
				+ currentSum);
	}
	public String calcDobanda() {
		// la suma curenta ii adun dobanda
		double currentSum = getCurrentSum();
		double interest = INTEREST * currentSum;
		currentSum = currentSum + interest;
		this.setCurrentSum(currentSum);
		System.out.println("Dobanda: " + interest + " Suma cu dobanda: "
				+ currentSum);
		String strInterest = Double.toString(interest);
		return strInterest;
	}

}
