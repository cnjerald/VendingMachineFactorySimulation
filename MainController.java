import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainController {
    private MainView mainView;
    private VendingMachineModel vendingMachineModel;
    private CreateVMView vmView;
    private VMCreationView rcVMView;
    private VMCreationView spVMView;
    private FeaturesView featuresView;
    private CustomerFeaturesView customerFeaturesView;
    private CustomerFeaturesSpecialView customerFeaturesSpecialView;
    private MaintainanceFeaturesView maintainanceFeaturesView;
    private MaintainanceFeaturesSpecialView maintainanceFeaturesSpecialView;
    private ViewItemsInDBView viewItemsInDBView;
    private RegisterItemView registerItemView;
    private ChangeItemPriceCaloriesView changeItemPriceView;
    private ChangeItemPriceCaloriesView changeItemCaloriesView;
    private SetSlotView setSlotView;
    private RestockSlotView restockSlotView;
    private SetMoneyView setMoneyView;
    private SelectItem_Coin_BillView selectItemView;
    private SelectItem_Coin_BillView selectCoinView;
    private SelectItem_Coin_BillView selectBillView;
    private FluidContainersView fluidContainersView;
    private CompoundPriceOverrideView compoundPriceOverrideView;
    private CreateChamporadoView createChamporadoView;
    private ChamporadoSelectionView champoradoSelectionView;
    private AddIngredientsToChamporadoView addIngredientsToChamporadoView;
    private SetItemSellableView setItemSellableView;

    public MainController(MainView mainView, VendingMachineModel vmModel){
        this.mainView = mainView; // VIEW 1
        this.vendingMachineModel = vmModel;

        vmView = new CreateVMView();
        rcVMView = new VMCreationView();

        spVMView = new VMCreationView();
        featuresView = new FeaturesView();
        customerFeaturesView = new CustomerFeaturesView();
        customerFeaturesSpecialView = new CustomerFeaturesSpecialView();
        maintainanceFeaturesView = new MaintainanceFeaturesView();
        maintainanceFeaturesSpecialView = new MaintainanceFeaturesSpecialView();
        viewItemsInDBView = new ViewItemsInDBView();
        registerItemView = new RegisterItemView();
        changeItemPriceView = new ChangeItemPriceCaloriesView();
        changeItemCaloriesView = new ChangeItemPriceCaloriesView();
        setSlotView = new SetSlotView();
        restockSlotView = new RestockSlotView();
        setMoneyView = new SetMoneyView();
        selectItemView = new SelectItem_Coin_BillView();
        selectCoinView = new SelectItem_Coin_BillView();
        selectBillView = new SelectItem_Coin_BillView();
        fluidContainersView = new FluidContainersView();
        compoundPriceOverrideView = new CompoundPriceOverrideView();
        createChamporadoView = new CreateChamporadoView();
        champoradoSelectionView = new ChamporadoSelectionView("Champorado List","Back");
        addIngredientsToChamporadoView = new AddIngredientsToChamporadoView();
        setItemSellableView = new SetItemSellableView();







// MAIN VIEW CONTROLS.
        this.mainView.setCreateVMBtnListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.changeVisibility();
                vmView.changeVisibility();
            }
        });

        this.mainView.setTestVendingMachineButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!vendingMachineModel.isCurrentMachineNull()){
                    mainView.changeVisibility();
                    featuresView.changeVisibility();
                }
                else{
                    mainView.setErrorMsg("Create a Machine First");
                }

            }
        });
        this.mainView.setExitButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainView.exit();
            }
        });

        // VM 2

        this.vmView.setRegularButtonEventListener(new ActionListener() { // access regular..
            @Override
            public void actionPerformed(ActionEvent e) {
                vmView.changeVisibility();
                rcVMView.changeVisibility();
            }
        });

        this.vmView.setBackButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vmView.changeVisibility();
                mainView.changeVisibility();
            }
        });

        this.vmView.setSpecialButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vmView.changeVisibility();
                spVMView.changeVisibility();
            }
        });

        // VM3

        this.rcVMView.setConfirmButtonListener(new ActionListener() {
            String displayText = ""; // Initialize to an empty string
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean result = vendingMachineModel.addrMachine(rcVMView.getNameTextField(), rcVMView.getSlotsTextField(), rcVMView.getCapacityTextField());

                if (result == true) {
                    rcVMView.setErrorMessage("Add success");
                    displayText = ""; // Reset the display text before appending new content
                    for (RegularVM r : vendingMachineModel.getrMachineList()) {
                        displayText += "Machine: " + r.getName() + "\n"; // Add a space after the machine name
                    }
                    rcVMView.setMachineList(displayText);
                }
            }
        });

        this.rcVMView.setBackButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rcVMView.changeVisibility();
                vmView.changeVisibility();
            }
        });


        // VM4.1

        this.spVMView.setBackButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vmView.changeVisibility();
                spVMView.changeVisibility();
            }
        });

        this.spVMView.setConfirmButtonListener(new ActionListener() {
            String displayText = ""; // Initialize to an empty string
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean result = vendingMachineModel.addsMachine(spVMView.getNameTextField(), spVMView.getSlotsTextField(), spVMView.getCapacityTextField());

                if (result == true) {
                    spVMView.setErrorMessage("Add success");
                    displayText = ""; // Reset the display text before appending new content
                    for (SpecialVM r : vendingMachineModel.getsMachineList()) {
                        displayText += "Machine: " + r.getName() + "\n"; // Add a space after the machine name
                    }
                    spVMView.setMachineList(displayText);
                }
            }
        });

        // VIEW 5-----------------------------------------
        this.featuresView.setVmFeaturesButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(vmModel.isChamporadoMachine()){
                    featuresView.changeVisibility();
                    customerFeaturesSpecialView.changeVisibility();
                }
                else{
                    featuresView.changeVisibility();
                    customerFeaturesView.changeVisibility();
                }
            }
        });
        this.featuresView.setBackButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                featuresView.changeVisibility();
                mainView.changeVisibility();
            }
        });
        this.featuresView.setmFeaturesButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                featuresView.changeVisibility();
                if(vmModel.isChamporadoMachine()){
                    maintainanceFeaturesSpecialView.changeVisibility();
                    vmModel.viewSalesReport();
                }
                else{
                    maintainanceFeaturesView.changeVisibility();
                    vmModel.viewSalesReport();
                }
            }
        });

        // View 6
        customerFeaturesView.setSelectItemButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectItemView.setText("Select Item Menu");
                customerFeaturesView.changeVisibility();
                selectItemView.changeVisibility();
                selectItemView.setListModel(vendingMachineModel.getSlotDetailsCustomer());

            }
        });
        customerFeaturesView.setBackButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerFeaturesView.changeVisibility();
                featuresView.changeVisibility();
            }
        });

        customerFeaturesView.setInsertCoinsButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectCoinView.setText("Insert Coin Menu");
                selectCoinView.setListModel(vendingMachineModel.getCoinDetailsCustomer());
                customerFeaturesView.changeVisibility();
                selectCoinView.changeVisibility();

            }
        });
        customerFeaturesView.setInsertBillsButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectBillView.setText("Insert Bills Menu");
                selectBillView.setListModel(vendingMachineModel.getBillDetailsCustomer());
                customerFeaturesView.changeVisibility();
                selectBillView.changeVisibility();
            }
        });

        customerFeaturesView.setDispenseSelectionButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vendingMachineModel.dispenseItem();
            }
        });

        customerFeaturesView.setRefundPaymentButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vendingMachineModel.refundPayment();
            }
        });

        // View.View6_1----------------------------------------------------------------------------

        customerFeaturesSpecialView.setSelectItemButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectItemView.setText("Select Item Menu");
                customerFeaturesSpecialView.changeVisibility();
                selectItemView.changeVisibility();
                selectItemView.setListModel(vendingMachineModel.getSlotDetailsCustomer());

            }
        });
        customerFeaturesSpecialView.setBackButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerFeaturesSpecialView.changeVisibility();
                featuresView.changeVisibility();
            }
        });

        customerFeaturesSpecialView.setInsertCoinsButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectCoinView.setText("Insert Coin Menu");
                selectCoinView.setListModel(vendingMachineModel.getCoinDetailsCustomer());
                customerFeaturesSpecialView.changeVisibility();
                selectCoinView.changeVisibility();

            }
        });
        customerFeaturesSpecialView.setInsertBillsButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectBillView.setText("Insert Bills Menu");
                selectBillView.setListModel(vendingMachineModel.getBillDetailsCustomer());
                customerFeaturesSpecialView.changeVisibility();
                selectBillView.changeVisibility();
            }
        });

        customerFeaturesSpecialView.setDispenseSelectionButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vendingMachineModel.dispenseItem();
            }
        });

        customerFeaturesSpecialView.setRefundPaymentButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vendingMachineModel.refundPayment();
            }
        });

        customerFeaturesSpecialView.setChamporadoButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerFeaturesSpecialView.changeVisibility();
                createChamporadoView.changeVisibility();
                createChamporadoView.setTextArea(vendingMachineModel.getCurrentChamporado());
            }
        });





        // View 7----------------------------------------------------------------------
        this.maintainanceFeaturesView.setViewItemsInDatabaseButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                viewItemsInDBView.setTextBoxList(vendingMachineModel.getItemDetailsMaintenance());;
                maintainanceFeaturesView.changeVisibility();
                viewItemsInDBView.changeVisibility();
            }
        });
        this.maintainanceFeaturesView.setRegisterItemInDatabaseButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maintainanceFeaturesView.changeVisibility();
                registerItemView.changeVisibility();

            }
        });
        this.maintainanceFeaturesView.setChangeItemPriceButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maintainanceFeaturesView.changeVisibility();
                changeItemPriceView.changeVisibility();

                changeItemPriceView.setListModel(vendingMachineModel.getItemDetailsMaintenance());
            }
        });

        this.maintainanceFeaturesView.setChangeItemCaloriesButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maintainanceFeaturesView.changeVisibility();
                changeItemCaloriesView.changeVisibility();
                changeItemCaloriesView.setListModel(vendingMachineModel.getItemDetailsMaintenance());

            }
        });

        this.maintainanceFeaturesView.setSetSlotButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maintainanceFeaturesView.changeVisibility();
                setSlotView.changeVisibility();
                // update list.
                setSlotView.setItemList(vendingMachineModel.getItemDetailsMaintenance());
                setSlotView.setSlotList(vendingMachineModel.getSlotDetails());
            }
        });
        this.maintainanceFeaturesView.setRestockSlotButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maintainanceFeaturesView.changeVisibility();
                restockSlotView.changeVisibility();
                restockSlotView.setSlotList(vendingMachineModel.getSlotDetails());
            }
        });

        this.maintainanceFeaturesView.setSetMoneyButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMoneyView.setBills(vendingMachineModel.getBillDetailsMaintainance());
                setMoneyView.setCoins(vendingMachineModel.getCoinDetailsMaintainance());
                maintainanceFeaturesView.changeVisibility();
                setMoneyView.changeVisibility();
            }
        });

        this.maintainanceFeaturesView.setBackButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vendingMachineModel.createHistory();
                maintainanceFeaturesView.changeVisibility();
                featuresView.changeVisibility();
            }
        });

        // View 7_1 _______________________________________________________
        this.maintainanceFeaturesSpecialView.setViewItemsInDatabaseButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                viewItemsInDBView.setTextBoxList(vendingMachineModel.getItemDetailsMaintenance());;
                maintainanceFeaturesSpecialView.changeVisibility();
                viewItemsInDBView.changeVisibility();
            }
        });
        this.maintainanceFeaturesSpecialView.setRegisterItemInDatabaseButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maintainanceFeaturesSpecialView.changeVisibility();
                registerItemView.setTextBoxList(vendingMachineModel.getItemDetailsMaintenance());
                registerItemView.changeVisibility();

            }
        });
        this.maintainanceFeaturesSpecialView.setChangeItemPriceButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maintainanceFeaturesSpecialView.changeVisibility();
                changeItemPriceView.changeVisibility();

                changeItemPriceView.setListModel(vendingMachineModel.getItemDetailsMaintenance());
            }
        });

        this.maintainanceFeaturesSpecialView.setChangeItemCaloriesButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maintainanceFeaturesSpecialView.changeVisibility();
                changeItemCaloriesView.changeVisibility();
                changeItemCaloriesView.setListModel(vendingMachineModel.getItemDetailsMaintenance());

            }
        });

        this.maintainanceFeaturesSpecialView.setSetSlotButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maintainanceFeaturesSpecialView.changeVisibility();
                setSlotView.changeVisibility();
                // update list.
                setSlotView.setItemList(vendingMachineModel.getItemDetailsMaintenance());
                setSlotView.setSlotList(vendingMachineModel.getSlotDetails());
            }
        });
        this.maintainanceFeaturesSpecialView.setRestockSlotButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maintainanceFeaturesSpecialView.changeVisibility();
                restockSlotView.changeVisibility();
                restockSlotView.setSlotList(vendingMachineModel.getSlotDetails());
            }
        });

        this.maintainanceFeaturesSpecialView.setSetMoneyButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setMoneyView.setBills(vendingMachineModel.getBillDetailsMaintainance());
                setMoneyView.setCoins(vendingMachineModel.getCoinDetailsMaintainance());
                maintainanceFeaturesSpecialView.changeVisibility();
                setMoneyView.changeVisibility();
            }
        });

        this.maintainanceFeaturesSpecialView.setContainersButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fluidContainersView.setMylist(vendingMachineModel.getFluidContainerDetails());
                fluidContainersView.changeVisibility();
                maintainanceFeaturesSpecialView.changeVisibility();
            }
        });

        this.maintainanceFeaturesSpecialView.setCompoundPriceButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                compoundPriceOverrideView.setMylist(vendingMachineModel.getCompoundDetailsMaintenance());
                compoundPriceOverrideView.changeVisibility();
                maintainanceFeaturesSpecialView.changeVisibility();
            }
        });

        this.maintainanceFeaturesSpecialView.setBackButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vendingMachineModel.createHistory();
                maintainanceFeaturesSpecialView.changeVisibility();
                featuresView.changeVisibility();
            }
        });

        this.maintainanceFeaturesSpecialView.setSetItemSellableStatusButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maintainanceFeaturesSpecialView.changeVisibility();
                setItemSellableView.setListModel(vendingMachineModel.getItemDetailsMaintenance());
                setItemSellableView.changeVisibility();
            }
        });

        // View 8
        this.viewItemsInDBView.setBackButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(vmModel.isChamporadoMachine()){
                    maintainanceFeaturesSpecialView.changeVisibility();
                }
                else{
                    maintainanceFeaturesView.changeVisibility();
                }
                viewItemsInDBView.changeVisibility();
            }
        });

        // View 9
        this.registerItemView.addConfirmButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean result = vendingMachineModel.registerItemToDatabase(registerItemView.getItemNameField(), registerItemView.getItemPriceField(), registerItemView.getItemCaloriesField());

                registerItemView.setTextBoxList(vendingMachineModel.getItemDetailsMaintenance());
            }
        });
        this.registerItemView.setBackButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(vmModel.isChamporadoMachine()){
                    maintainanceFeaturesSpecialView.changeVisibility();
                }
                else{
                    maintainanceFeaturesView.changeVisibility();
                }
                registerItemView.changeVisibility();

            }
        });

        //--------- View.View10

        this.changeItemPriceView.setConfirmButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String number = "";
                try{
                    vendingMachineModel.changeItemPrice(changeItemPriceView.getItemOnItemList(), changeItemPriceView.getAmount());
                }
                catch(Exception exception){
                    MessagePromptView errorMessage = new MessagePromptView("Select a slot");
                }


                changeItemPriceView.setListModel(vendingMachineModel.getItemDetailsMaintenance());

            }
        });

        this.changeItemPriceView.setBackButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(vmModel.isChamporadoMachine()){
                    maintainanceFeaturesSpecialView.changeVisibility();
                }
                else{
                    maintainanceFeaturesView.changeVisibility();
                }

                changeItemPriceView.changeVisibility();

            }
        });

        //-- VIEW 11.
        this.changeItemCaloriesView.setConfirmButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String number = "";
                try{
                    vendingMachineModel.changeItemCalories(changeItemCaloriesView.getItemOnItemList(), changeItemCaloriesView.getAmount());
                }
                catch(Exception exception){
                    MessagePromptView errorMessage = new MessagePromptView("Select a slot");
                }


                changeItemCaloriesView.setListModel(vendingMachineModel.getItemDetailsMaintenance());
            }
        });

        this.changeItemCaloriesView.setBackButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(vmModel.isChamporadoMachine()){
                    maintainanceFeaturesSpecialView.changeVisibility();
                }
                else{
                    maintainanceFeaturesView.changeVisibility();
                }
                changeItemCaloriesView.changeVisibility();
            }
        });

        // ---- VIEW 12.

        this.setSlotView.setBackButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(vmModel.isChamporadoMachine()){
                    maintainanceFeaturesSpecialView.changeVisibility();
                }
                else{
                    maintainanceFeaturesView.changeVisibility();
                }
                setSlotView.changeVisibility();
            }
        });

        this.setSlotView.setConfirmButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                vendingMachineModel.setSlot(setSlotView.getItemIndex(), setSlotView.getSlotIndex());
                setSlotView.setItemList(vendingMachineModel.getItemDetailsMaintenance());
                setSlotView.setSlotList(vendingMachineModel.getSlotDetails());
            }
        });

        //------------ VIEW13.

        this.restockSlotView.setBackButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(vmModel.isChamporadoMachine()){
                    maintainanceFeaturesSpecialView.changeVisibility();
                }
                else{
                    maintainanceFeaturesView.changeVisibility();
                }
                restockSlotView.changeVisibility();
            }
        });
        this.restockSlotView.setConfirmButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vendingMachineModel.restockSlot(restockSlotView.getSlotIndex(), restockSlotView.getAmount());
                restockSlotView.setSlotList(vendingMachineModel.getSlotDetails());
            }
        });
        //-------------- VIEW14.

        this.setMoneyView.setBackButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(vmModel.isChamporadoMachine()){
                    maintainanceFeaturesSpecialView.changeVisibility();
                }
                else{
                    maintainanceFeaturesView.changeVisibility();
                }
                setMoneyView.changeVisibility();
            }
        });

        this.setMoneyView.setConfirmButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                vendingMachineModel.setBank(setMoneyView.getIndex(), setMoneyView.getAmount());
                setMoneyView.setBills(vendingMachineModel.getBillDetailsMaintainance());
                setMoneyView.setCoins(vendingMachineModel.getCoinDetailsMaintainance());
            }
        });

        // ------------- VIEW15.

        this.selectItemView.setConfirmButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                vendingMachineModel.setSlotSelectionIndex(selectItemView.getSelection());

                selectItemView.setListModel(vendingMachineModel.getSlotDetailsCustomer());

            }
        });
        this.selectItemView.setBackButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(vmModel.isChamporadoMachine()){
                    customerFeaturesSpecialView.changeVisibility();
                }
                else{
                    customerFeaturesView.changeVisibility();
                }
                selectItemView.changeVisibility();

            }
        });


        //--------------------------------------- VIEW 16.


        this.selectCoinView.setConfirmButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vendingMachineModel.addCoins(selectCoinView.getSelection());
                selectCoinView.setListModel(vendingMachineModel.getCoinDetailsCustomer());
            }
        });



        this.selectCoinView.setBackButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectCoinView.changeVisibility();
                if(vmModel.isChamporadoMachine()){
                    customerFeaturesSpecialView.changeVisibility();
                }
                else{
                    customerFeaturesView.changeVisibility();
                }
            }
        });

        // --------------- VIEW 17.

        this.selectBillView.setConfirmButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vendingMachineModel.addBills(selectBillView.getSelection()); // CHANGE
                selectBillView.setListModel(vendingMachineModel.getBillDetailsCustomer());
            }
        });


        this.selectBillView.setBackButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectBillView.changeVisibility();
                if(vmModel.isChamporadoMachine()){
                    customerFeaturesSpecialView.changeVisibility();
                }
                else{
                    customerFeaturesView.changeVisibility();
                }
            }
        });


        // -------------------- View.View19.

        this.fluidContainersView.setBackButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maintainanceFeaturesSpecialView.changeVisibility();
                fluidContainersView.changeVisibility();
            }
        });

        this.fluidContainersView.setFillAllButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vendingMachineModel.fillAllContainer();
                fluidContainersView.setMylist(vendingMachineModel.getFluidContainerDetails());
            }
        });

        this.fluidContainersView.setConfirmButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vendingMachineModel.setContainerAmount(fluidContainersView.getIndexField(), fluidContainersView.getAmountField());
                fluidContainersView.setMylist(vendingMachineModel.getFluidContainerDetails());
            }
        });

        //------------------------ View.View20.

        this.compoundPriceOverrideView.setBackButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                maintainanceFeaturesSpecialView.changeVisibility();
                compoundPriceOverrideView.changeVisibility();
            }
        });

        this.compoundPriceOverrideView.setResetPriceButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vendingMachineModel.resetCompoundPrice(compoundPriceOverrideView.getIndexField());
                compoundPriceOverrideView.setMylist(vendingMachineModel.getCompoundDetailsMaintenance());
            }
        });

        this.compoundPriceOverrideView.setConfirmButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vendingMachineModel.overrideCompoundPrice(compoundPriceOverrideView.getIndexField(), compoundPriceOverrideView.getAmountField());
                compoundPriceOverrideView.setMylist(vendingMachineModel.getCompoundDetailsMaintenance());
            }
        });

        //------------------- VIEW21

        this.createChamporadoView.clearSelectionButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vendingMachineModel.clearChamporadoSelection();
                createChamporadoView.setTextArea(vendingMachineModel.getCurrentChamporado());
            }
        });

        this.createChamporadoView.setBackButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                customerFeaturesSpecialView.changeVisibility();
                createChamporadoView.changeVisibility();
            }
        });

        this.createChamporadoView.champoradoButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createChamporadoView.changeVisibility();
                champoradoSelectionView.setListModel(vendingMachineModel.getChamporadoDetailsCustomer());
                champoradoSelectionView.changeVisibility();
            }
        });

        this.createChamporadoView.addIngredientsButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(vmModel.hasSelectedChamporado()){
                    createChamporadoView.changeVisibility();
                    AddIngredientsToChamporadoView view24 = new AddIngredientsToChamporadoView("Select an ingredient to add","Back");
                    view24.changeVisibility();
                    view24.setListModel(vendingMachineModel.getItemDetailsCustomer());

                    view24.setBackButtonEventListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            view24.changeVisibility();
                            createChamporadoView.changeVisibility();
                            createChamporadoView.setTextArea(vendingMachineModel.getCurrentChamporado());
                        }
                    });

                    view24.setClearButtonEventListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            vendingMachineModel.clearPaidAdditionList();
                            view24.setListModel(vendingMachineModel.getItemDetailsCustomer());
                        }
                    });

                    view24.setConfirmButtonEventListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            vendingMachineModel.registerPaidItem(view24.getItemSelection());
                            view24.setListModel(vendingMachineModel.getItemDetailsCustomer());
                        }
                    });
                }
                else{
                    MessagePromptView errorMessage = new MessagePromptView("Create a champorado first");
                }

            }
        });

        // --------------------- View.View22

        this.champoradoSelectionView.setConfirmButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(vendingMachineModel.setChamporadoSelection(champoradoSelectionView.getSelection())){
                    if(vendingMachineModel.checkCurrentFreeReservedItems()){
                        addIngredientsToChamporadoView = new AddIngredientsToChamporadoView(vendingMachineModel.getCurrentFreeSelectedIngredients(),"Skip");
                        addIngredientsToChamporadoView.setListModel(vendingMachineModel.getItemDetailsCustomer());
                        addIngredientsToChamporadoView.changeVisibility();

                        //VIEW23------------------
                        addIngredientsToChamporadoView.setBackButtonEventListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                createChamporadoView.setTextArea(vendingMachineModel.getCurrentChamporado());
                                addIngredientsToChamporadoView.changeVisibility();
                                createChamporadoView.changeVisibility();
                            }
                        });

                        addIngredientsToChamporadoView.setClearButtonEventListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                vendingMachineModel.clearFreeAdditionList();
                                addIngredientsToChamporadoView.setListModel(vendingMachineModel.getItemDetailsCustomer());
                                addIngredientsToChamporadoView.setAmountLabel(vendingMachineModel.getCurrentFreeSelectedIngredients());
                            }
                        });

                        addIngredientsToChamporadoView.setConfirmButtonEventListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {

                                if(!vendingMachineModel.checkCurrentFreeReservedItems()){
                                    createChamporadoView.setTextArea(vendingMachineModel.getCurrentChamporado());
                                    createChamporadoView.changeVisibility();
                                    addIngredientsToChamporadoView.changeVisibility();
                                }
                                else{
                                    vendingMachineModel.registerFreeItem(addIngredientsToChamporadoView.getItemSelection());
                                    addIngredientsToChamporadoView.setListModel(vendingMachineModel.getItemDetailsCustomer());
                                    addIngredientsToChamporadoView.setAmountLabel(vendingMachineModel.getCurrentFreeSelectedIngredients());
                                    if(!vendingMachineModel.checkCurrentFreeReservedItems()){
                                        createChamporadoView.setTextArea(vendingMachineModel.getCurrentChamporado());
                                        createChamporadoView.changeVisibility();
                                        addIngredientsToChamporadoView.changeVisibility();
                                    }
                                }
                            }
                        });



                    }
                    else{
                        createChamporadoView.setTextArea(vendingMachineModel.getCurrentChamporado());
                        createChamporadoView.changeVisibility();
                    }


                    champoradoSelectionView.changeVisibility();

                }
            }
        });

        this.champoradoSelectionView.setBackButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                champoradoSelectionView.changeVisibility();
                createChamporadoView.changeVisibility();
            }
        });

        //------------------------- View.View23

        // ----------------- View.View24

        this.setItemSellableView.setBackButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setItemSellableView.changeVisibility();
                maintainanceFeaturesSpecialView.changeVisibility();
            }
        });

        this.setItemSellableView.setConfirmButtonEventListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                vmModel.setItemSellableStatus(setItemSellableView.getSelection());
                setItemSellableView.setListModel(vendingMachineModel.getItemDetailsMaintenance());
            }
        });



    }

}










