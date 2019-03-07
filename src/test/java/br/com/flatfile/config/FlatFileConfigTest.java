package br.com.flatfile.config;

public final class FlatFileConfigTest {

    private FlatFileConfigTest() {
    }

    public static void initializeFlatFileConfig() {
        FlatFileConfig flatFileConfig = new FlatFileConfig();
        flatFileConfig.setDataSeparatorCharacter("\\|");
        flatFileConfig.setDataIdPosition(0);
        flatFileConfig.setSalesmanDataId("001");
        flatFileConfig.setCustomerDataId("002");
        flatFileConfig.setSaleDataId("003");
        flatFileConfig.setSalesmanTaxIdPosition(1);
        flatFileConfig.setSalesmanNamePosition(2);
        flatFileConfig.setSalesmanSalaryPosition(3);
        flatFileConfig.setCustomerTaxIdPosition(1);
        flatFileConfig.setCustomerNamePosition(2);
        flatFileConfig.setCustomerBusinessAreaPosition(3);
        flatFileConfig.setSaleIdPosition(1);
        flatFileConfig.setSaleItemsPosition(2);
        flatFileConfig.setSaleSalesmanPosition(3);
        flatFileConfig.setItemIdPosition(0);
        flatFileConfig.setItemQuantityPosition(1);
        flatFileConfig.setItemPricePosition(2);
        flatFileConfig.setItemSeparatorCharacter(",");
        flatFileConfig.setDataItemSeparatorCharacter("-");
    }
}
