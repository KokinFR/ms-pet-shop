package com.example.ms_pet_shop.contracts;

public class ProductConstracts {

    private ProductConstracts() {};

    public static final String PRODUCT_200_DEACTIVATED_MESSAGE = "Produto desativado com sucesso!";
    public static final String PRODUCT_201_CREATED_MESSAGE = "Produto castrado com sucesso!";
    public static final String PRODUCT_200_UPDATED_MESSAGE = "Produto atualizado com sucesso!";
    public static final String PRODUCT_200_DELETED_MESSAGE = "Produto deletado do sistema!";

    public static final String PRODUCT_404_MESSAGE = "Esse produto não foi encontrado!";
    public static final String PRODUCT_DUPLICATE_MESSAGE = "Há um produto que corresponde a essa descrição.";

    public static final int PRODUCT_200_STATUS = 200;
    public static final int PRODUCT_201_STATUS = 201;
}
