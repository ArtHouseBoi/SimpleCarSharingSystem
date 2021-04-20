
import java.util.List;
import java.util.Map;

public interface Menu {


    Map<Integer, String> getCompanyList();
    void addCompany();
    void addCar(int id);
    List<String> getCarList(int id);
    void addCustomer();
    Map<Integer, String> getCustomerList();

    List<String> getAvailableCarList(int id);
    void rent(String name, String nameCust);
    boolean isRented(String name);
    List<String> myCar(String name);
    void returnCar(String name);


}
