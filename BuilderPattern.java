// make sure you have lombok

// if using intellij and lombok throws a "cannot find symbol" error or something similar you may want to go to:]
// settings> build> compiler >annotation processors > tick : enable annotation processors 

// why is this useful: think you have have to provide constructors for a class with 9 parameters
// you also have to remember the order of the constructors
// in the past, telescopic constructors were used ( where one is set, and the next one is set based on the previous using super())
// the number of constructors to provide all the possible variations of how a class can be constructed using these 9 parameters
// 1!+2!+3!+4!+5!+6!+7!+8!+9! ... someone in the group is more qualified than me to do this
//

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@Getter
@ToString
class BuilderPattern {
   private final String name1;
   private final int age;
   private final String height;
   private final int weight;
   private final String hobby;

}

class Something {
   public static void main(String[] args) {
      BuilderPattern scratch = BuilderPattern.builder().name1("ana").build();

      BuilderPattern scratch2 = BuilderPattern.builder().name1("ana").age(21).build();
      BuilderPattern scratch3 = BuilderPattern.builder().name1("ana").age(21).height("150cm").weight(60).hobby("fishing").build();

      System.out.println(scratch.getName1());
   }
}


// vanilla java - this works without lombok
class BankAccount {
   private long accountNumber; //This is important, so we'll pass it to the constructor.
   private String owner;
   private String branch;
   private double balance;
   private double interestRate;

   //setter and getter
   public void setAccountNumber(long accountNumber) {
      this.accountNumber = accountNumber;
   }

   public long getAccountNumber() {
      return accountNumber;
   }

   public void setOwner(String owner) {
      this.owner = owner;
   }

   public String getOwner() {
      return owner;
   }

   public static AccountBuilder builder() {
      return new AccountBuilder();
   }

   public void setBranch(String branch) {
      this.branch = branch;
   }

   public String getBranch() {
      return branch;
   }

   public void setBalance(double balance) {
      this.balance = balance;
   }

   public double getBalance() {
      return balance;
   }

   public void setInterestRate(double interestRate) {
      this.interestRate = interestRate;
   }

   public double getInterestRate() {
      return interestRate;
   }
}


class AccountBuilder {
   private long accountNumber; //This is important, so we'll pass it to the constructor.
   private String owner;
   private String branch;
   private double balance;
   private double interestRate;


   public AccountBuilder() {
   }

   public AccountBuilder(long accountNumber) {
      this.accountNumber = accountNumber;
   }

   public AccountBuilder withOwner(String owner) {
      this.owner = owner;
      return this;  //By returning the builder each time, we can create a fluent interface.
   }

   public AccountBuilder atBranch(String branch) {
      this.branch = branch;
      return this;
   }

   public AccountBuilder openingBalance(double balance) {
      this.balance = balance;
      return this;
   }

   public AccountBuilder atRate(double interestRate) {
      this.interestRate = interestRate;
      return this;
   }

   public BankAccount build() {
      //Here we create the actual bank account object, which is always in a fully initialised state when it's returned.
      BankAccount account = new BankAccount();  //Since the builder is in the BankAccount class, we can invoke its private constructor.
      account.setAccountNumber(accountNumber);
      account.setOwner(owner);
      account.setBranch(branch);
      account.setBalance(balance);
      account.setInterestRate(interestRate);
      return account;
   }
}

class someOther {
   public static void main(String[] args) {
      BankAccount acount = BankAccount.builder().atBranch("branch").withOwner("yes").atRate(2.5).build();
   }
}
