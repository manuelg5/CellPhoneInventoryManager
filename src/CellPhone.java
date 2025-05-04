
public class CellPhone {
    private String model;
    private String manufacturer;
    private double retailPrice;

    public CellPhone(String model, String manufacturer, double retailPrice) throws InvalidModelException, InvalidManufacturerException, InvalidRetailPriceException {
        setModel(model);
        setManufacturer(manufacturer);
        setRetailPrice(retailPrice);
    }

    public CellPhone() {
        this.model = "";
        this.manufacturer = "";
        this.retailPrice = 0.0;
    }

    public void setModel(String model) throws InvalidModelException {
        if (model == null || model.isEmpty()) {
            throw new InvalidModelException("Model cannot be empty.");
        }
        this.model = model;
    }

    public void setManufacturer(String manufacturer) throws InvalidManufacturerException {
        if (manufacturer == null || manufacturer.isEmpty()) {
            throw new InvalidManufacturerException("Manufacturer cannot be empty.");
        }
        this.manufacturer = manufacturer;
    }

    public void setRetailPrice(double retailPrice) throws InvalidRetailPriceException {
        if (retailPrice < 0 || retailPrice > 1500) {
            throw new InvalidRetailPriceException("Retail price must be between 0 and 1500.");
        }
        this.retailPrice = retailPrice;
    }

    public String getModel() {
        return model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    @Override
    public String toString() {
        return String.format("Model: %-20s Manufacturer: %-20s Retail Price: %.2f", model, manufacturer, retailPrice);
    }
}
