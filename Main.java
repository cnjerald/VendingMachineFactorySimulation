import View.MainView;

public class Main {

    public static void main(String[] args) {
        MainView view1 = new MainView();
        VendingMachineModel mod3 = new VendingMachineModel();
        MainController mainController = new MainController(view1,mod3);
    }

}