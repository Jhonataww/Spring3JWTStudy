package com.example.springboot.controllers;

import com.example.springboot.dtos.ProductRecordDto;
import com.example.springboot.models.ProductModel;
import com.example.springboot.repositories.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    public static final UUID ID_PRODUCT = UUID.randomUUID();
    public static final String TESTE = "teste";
    public static final BigDecimal VALUE = BigDecimal.valueOf(10.0);
    @InjectMocks
    ProductController productController;

    @Mock
    ProductRepository productRepository;

    @Mock
    ResponseEntity<ProductModel> responseEntityProductModel;

    @Mock
    ProductRecordDto productRecordDto;

    @Mock
    ProductModel productModel;

    @Mock
    Optional<ProductModel> productO;

    @Mock
    Optional<ProductModel> emptyProductO;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  //inicializa os mocks
        startObjects();                    //inicializa os objetos
    }

    @Test
    void saveProduct() {

    }

    @Test
    void getAllProducts() {
    }

    @Test
    void getOneProductRetunOk() {
                                                                                                        //Mocando a chamada do metodo findById do productRepository
       // Mockito.when(productRepository.findById(Mockito.any(UUID.class))).thenReturn(productO);       //quando chamar o metodo findById, retorne o productO
        when(productRepository.findById(any(UUID.class))).thenReturn(productO);
        ResponseEntity<Object> response = productController.getOneProduct(ID_PRODUCT);                  //chamando o metodo getOneProduct
        assertNotNull(response);                                                                        //verificando se o retorno não é nulo
        Assertions.assertEquals(ResponseEntity.class, response.getClass());                             //verificando se o retorno é um ResponseEntity da mesma classe
        Assertions.assertEquals(200, response.getStatusCodeValue());                           //verificando se o status code é 200
    }

    @Test
    void getOneProductRetunNotFound() {
                                                                                                         // Criando um Optional vazio para simular o comportamento quando o produto não é encontrado
        //Optional<ProductModel> emptyProductO = Optional.empty();
                                                                                                         // Mocando a chamada do método findById do productRepository
        when(productRepository.findById(any(UUID.class))).thenReturn(emptyProductO);
        ResponseEntity<Object> response = productController.getOneProduct(ID_PRODUCT);

        assertNotNull(response);
        assertEquals(ResponseEntity.class, response.getClass());
        assertEquals(HttpStatus.NOT_FOUND.value(), response.getStatusCodeValue());
                                                                                                         // Verificando se o corpo da resposta contém a mensagem esperada
        assertEquals("Produto not found.", response.getBody());
    }

    @Test
    void updateProduct() {
    }

    @Test
    void deleteProduct() {
    }

    private void startObjects(){
        productModel = new ProductModel(ID_PRODUCT, TESTE, VALUE);
        productRecordDto = new ProductRecordDto(TESTE, VALUE);
        responseEntityProductModel = ResponseEntity.ok(new ProductModel(ID_PRODUCT, TESTE, VALUE));
        productO = Optional.of(new ProductModel(ID_PRODUCT, TESTE, VALUE));
        emptyProductO = Optional.empty();
    };

}


//Caso esteja utilizando Intelijj no pacote java, clique com o botão direito do mouse e selecione a opção "More run/debug" > "Run 'All Tests with Coverage'".
//Caso esteja utilizando o Eclipse, clique com o botão direito do mouse no pacote java e selecione a opção "Coverage As" > "JUnit Test".
//Caso esteja utilizando o VSCode, clique com o botão direito do mouse no pacote java e selecione a opção "Run Test" ou "Debug Test".
//Caso esteja utilizando o NetBeans, clique com o botão direito do mouse no pacote java e selecione a opção "Test File" ou "Debug File".
//Caso esteja utilizando o Spring Tool Suite, clique com o botão direito do mouse no pacote java e selecione a opção "Run As" > "JUnit Test" ou "Debug As" > "JUnit Test".
//Caso esteja utilizando o Apache Maven, execute o comando mvn test no terminal.
//Isso tudo serve para rodar o teste no projeto todoo, mas se quiser rodar apenas um teste,
// basta clicar com o botão direito do mouse no teste desejado e selecionar a opção "Run Test" ou "Debug Test".