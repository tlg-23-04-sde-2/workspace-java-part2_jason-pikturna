package gov.irs;

public interface IRS {
    public void collectTaxes();
    public void register(TaxPayer payer);

    public static IRS getInstance() {
        return IRSEnum.INSTANCE;
    }
}